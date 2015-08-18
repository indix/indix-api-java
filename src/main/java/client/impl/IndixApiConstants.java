package client.impl;

class IndixApiConstants {

    static final String SCHEME = "https";
    static final String HOST = "api.indix.com";
    static final String VERSION = "v2";
    static final String BULK = "bulk";

    static final String JOB_VIEW = "jobs";
    static final String DOWNLOAD_PATH = "download";
    static final String LOOKUP_VIEW = "lookup";
    static final String HISTORY_VIEW = "history";

    static final String PRODUCTS_RESOURCE = "products";
    static final String STORES_RESOURCE = buildPath(VERSION, "stores");
    static final String BRANDS_RESOURCE = buildPath(VERSION, "brands");
    static final String CATEGORIES_RESOURCE = buildPath(VERSION, "categories");
    static final String SUGGESTIONS_RESOURCE = buildPath(VERSION, PRODUCTS_RESOURCE, "suggestions");

    public static final String PRODUCT_HISTORY_RESOURCE = buildPath(VERSION, HISTORY_VIEW, PRODUCTS_RESOURCE);

    public static final String BULK_JOB_RESOURCE = buildPath(VERSION, BULK, JOB_VIEW);

    private static String buildPath(String... pathFragments) {
        StringBuilder sb = new StringBuilder();

        for (String fragment : pathFragments) {
            sb.append("/");
            sb.append(fragment);
        }
        return sb.toString();
    }

    public static String buildSearchResourcePath(ResourceType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), PRODUCTS_RESOURCE);
    }

    public static String buildProductDetailsPath(ResourceType resourceView, String mpid) {
        String resource = buildSearchResourcePath(resourceView);
        return buildPath(resource, mpid);
    }

    public static String buildProductHistoryPath(String mpid) {
        return buildPath(PRODUCT_HISTORY_RESOURCE, mpid);
    }

    public static String buildBulkSearchResourcePath(ResourceType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), BULK, PRODUCTS_RESOURCE);
    }

    public static String buildBulkLookupResourcePath(ResourceType resourceView) {
        return buildPath(VERSION, String.valueOf(resourceView), BULK, LOOKUP_VIEW);
    }

    public static String buildBulkJobStatusPath(String resource, String jobid) {
        return buildPath(resource, jobid);
    }

    public static String buildBulkJobDownloadPath(String resource, String jobid) {
        return buildPath(resource, jobid, DOWNLOAD_PATH);
    }

}
