package exception;

import org.apache.http.HttpStatus;

/*
 * Indix exception corresponding to HTTP 402 error code
 */
public class PaymentRequiredException extends IndixApiException {

    public PaymentRequiredException() {
        super();
    }

    public PaymentRequiredException(String message) {
        super(HttpStatus.SC_BAD_REQUEST, message);
    }
}
