package client.impl;

public class IndixApiConstants {

    static final String SCHEME = "https";
    static final String HOST = "api.indix.com";
    static final String VERSION = "v2";
    static final String BULK = "bulk";

    static final String SUMMARY_VIEW = "summary";
    static final String OFFERS_STANDARD_VIEW = "offersStandard";
    static final String OFFERS_PREMIUM_VIEW = "offersPremium";
    static final String CATALOG_STANDARD_VIEW = "catalogStandard";
    static final String CATALOG_PREMIUM_VIEW = "catalogPremium";
    static final String UNIVERSAL_VIEW = "universal";
    static final String JOB_VIEW = "jobs";
    static final String DOWNLOAD_PATH = "download";
    static final String LOOKUP_VIEW = "lookup";
    static final String HISTORY_VIEW = "history";

    static final String PRODUCTS_RESOURCE = "products";
    static final String STORES_RESOURCE = buildPath(VERSION, "stores");
    static final String BRANDS_RESOURCE = buildPath(VERSION, "brands");
    static final String CATEGORIES_RESOURCE = buildPath(VERSION, "categories");
    static final String SUGGESTIONS_RESOURCE = buildPath(VERSION, PRODUCTS_RESOURCE, "suggestions");

    public static final String SUMMARY_PRODUCTS_RESOURCE = buildPath(VERSION, SUMMARY_VIEW, PRODUCTS_RESOURCE);
    public static final String OFFERS_STANDARD_PRODUCTS_RESOURCE = buildPath(VERSION, OFFERS_STANDARD_VIEW, PRODUCTS_RESOURCE);
    public static final String OFFERS_PREMIUM_PRODUCTS_RESOURCE = buildPath(VERSION, OFFERS_PREMIUM_VIEW, PRODUCTS_RESOURCE);
    public static final String CATALOG_STANDARD_PRODUCTS_RESOURCE = buildPath(VERSION, CATALOG_STANDARD_VIEW, PRODUCTS_RESOURCE);
    public static final String CATALOG_PREMIUM_PRODUCTS_RESOURCE = buildPath(VERSION, CATALOG_PREMIUM_VIEW, PRODUCTS_RESOURCE);
    public static final String UNIVERSAL_PRODUCTS_RESOURCE = buildPath(VERSION, UNIVERSAL_VIEW, PRODUCTS_RESOURCE);

    public static final String SUMMARY_PRODUCT_DETAILS_RESOURCE = buildPath(VERSION, SUMMARY_VIEW, PRODUCTS_RESOURCE);
    public static final String OFFERS_STANDARD_PRODUCT_DETAILS_RESOURCE = buildPath(VERSION, OFFERS_STANDARD_VIEW, PRODUCTS_RESOURCE);
    public static final String OFFERS_PREMIUM_PRODUCT_DETAILS_RESOURCE = buildPath(VERSION, OFFERS_PREMIUM_VIEW, PRODUCTS_RESOURCE);
    public static final String CATALOG_STANDARD_PRODUCT_DETAILS_RESOURCE = buildPath(VERSION, CATALOG_STANDARD_VIEW, PRODUCTS_RESOURCE);
    public static final String CATALOG_PREMIUM_PRODUCT_DETAILS_RESOURCE = buildPath(VERSION, CATALOG_PREMIUM_VIEW, PRODUCTS_RESOURCE);
    public static final String UNIVERSAL_PRODUCT_DETAILS_RESOURCE = buildPath(VERSION, UNIVERSAL_VIEW, PRODUCTS_RESOURCE);

    public static final String PRODUCT_HISTORY_RESOURCE = buildPath(VERSION, HISTORY_VIEW, PRODUCTS_RESOURCE);

    public static final String BULK_PRODUCT_RESOURCE = buildPath(VERSION, SUMMARY_VIEW, BULK, PRODUCTS_RESOURCE);
    public static final String BULK_OFFERS_STANDARD_PRODUCTS_RESOURCE = buildPath(VERSION, OFFERS_STANDARD_VIEW, BULK, PRODUCTS_RESOURCE);
    public static final String BULK_OFFERS_PREMIUM_PRODUCTS_RESOURCE = buildPath(VERSION, OFFERS_PREMIUM_VIEW, BULK, PRODUCTS_RESOURCE);
    public static final String BULK_CATALOG_STANDARD_PRODUCTS_RESOURCE = buildPath(VERSION, CATALOG_STANDARD_VIEW, BULK, PRODUCTS_RESOURCE);
    public static final String BULK_CATALOG_PREMIUM_PRODUCTS_RESOURCE = buildPath(VERSION, CATALOG_PREMIUM_VIEW, BULK, PRODUCTS_RESOURCE);
    public static final String BULK_UNIVERSAL_PRODUCTS_RESOURCE = buildPath(VERSION, UNIVERSAL_VIEW, BULK, PRODUCTS_RESOURCE);

    public static final String BULK_LOOKUP_RESOURCE = buildPath(VERSION, SUMMARY_VIEW, BULK, LOOKUP_VIEW);
    public static final String BULK_LOOKUP_OFFERS_STANDARD_RESOURCE = buildPath(VERSION, OFFERS_STANDARD_VIEW, BULK, LOOKUP_VIEW);
    public static final String BULK_LOOKUP_OFFERS_PREMIUM_RESOURCE = buildPath(VERSION, OFFERS_PREMIUM_VIEW, BULK, LOOKUP_VIEW);
    public static final String BULK_LOOKUP_CATALOG_STANDARD_RESOURCE = buildPath(VERSION, CATALOG_STANDARD_VIEW, BULK, LOOKUP_VIEW);
    public static final String BULK_LOOKUP_CATALOG_PREMIUM_RESOURCE = buildPath(VERSION, CATALOG_PREMIUM_VIEW, BULK, LOOKUP_VIEW);
    public static final String BULK_LOOKUP_UNIVERSAL_RESOURCE = buildPath(VERSION, UNIVERSAL_VIEW, BULK, LOOKUP_VIEW);

    public static final String BULK_JOB_RESOURCE = buildPath(VERSION, BULK, JOB_VIEW);

    private static String buildPath(String... pathFragments) {
        StringBuilder sb = new StringBuilder();

        for (String fragment : pathFragments) {
            sb.append("/");
            sb.append(fragment);
        }
        return sb.toString();
    }

    public static String buildProductDetailsPath(String resource, String mpid) {
        return buildPath(resource, mpid);
    }

    public static String buildBulkJobStatusPath(String resource, String jobid) {
        return buildPath(resource, jobid);
    }

    public static String buildBulkJobDownloadPath(String resource, String jobid) {
        return buildPath(resource, jobid, DOWNLOAD_PATH);
    }
}
