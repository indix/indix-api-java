package com.indix.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * Forked off from https://github.com/micw/ArduinoProjekte/blob/b7e308533d20c9d23fda5e08899c22afd1dc1303/java/ArduinoHomeServer/src/main/java/tools/SSLTrustCa.java
 * <p/>
 * Helps to add LetsEncrypt to current JVM instance's keystore so we can access api.indix.com. This change has no effect
 * if the host JVM is >= JDK8u101, since this is already part of them.
 * <p/>
 * References
 * - http://stackoverflow.com/questions/3508050/how-can-i-get-a-list-of-trusted-root-certificates-in-java/3508175#3508175
 * - http://stackoverflow.com/questions/34110426/does-java-support-lets-encrypt-certificates
 * - https://community.letsencrypt.org/t/will-the-cross-root-cover-trust-by-the-default-list-in-the-jdk-jre/134/37
 */
public final class SSLTrustCA {

    public static SSLContext trustLetsEncryptRootCA() {
        return trustCa(SSLTrustCA.class.getResource("/ca/DSTRootCAX3.der"));
    }

    private static KeyStore keyStore;
    private final static Logger LOG = LoggerFactory.getLogger(SSLTrustCA.class);

    private synchronized static KeyStore initialize(boolean loadJavaDefaultKeytore)
            throws GeneralSecurityException, IOException {

        if (SSLTrustCA.keyStore == null) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            if (loadJavaDefaultKeytore) {
                // Load the java default keystore
                Path ksPath = Paths.get(System.getProperty("java.home"), "lib", "security", "cacerts");
                try (InputStream in = Files.newInputStream(ksPath)) {
                    keyStore.load(in, "changeit".toCharArray());
                }
            }

            SSLTrustCA.keyStore = keyStore;
        }

        return SSLTrustCA.keyStore;
    }

    private synchronized static SSLContext trustCa(URL caFile) {
        try {
            LOG.debug("Trusting CAFile: " + caFile.toExternalForm());
            Certificate crt;
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            try (InputStream caInput = new BufferedInputStream(caFile.openStream())) {
                crt = cf.generateCertificate(caInput);
            }

            String certName = ((X509Certificate) crt).getSubjectDN().getName();
            KeyStore keyStore = initialize(true);
            keyStore.setCertificateEntry(certName, crt);

            // Set this as the default keystore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
            return sslContext;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
