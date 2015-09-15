package query;

public class QueryFactory {

    /**
     * @return {@link SearchQuery}
     */
    public static SearchQuery newSearchQuery() {
        return new SearchQuery();
    }

    /**
     * @return {@link ProductDetailsQuery}
     */
    public static ProductDetailsQuery newProductDetailsQuery() {
        return new ProductDetailsQuery();
    }

    /**
     * @return {@link MetadataQuery}
     */
    public static MetadataQuery newMetadataQuery() {
        return new MetadataQuery();
    }

    /**
     * @return {@link SuggestionsQuery}
     */
    public static SuggestionsQuery newSuggestionsQuery() {
        return new SuggestionsQuery();
    }

    /**
     * @return {@link BulkProductsQuery}
     */
    public static BulkProductsQuery newBulkQuery() {
        return new SearchQuery();
    }

    /**
     * @return {@link ProductHistoryQuery}
     */
    public static ProductHistoryQuery newProductHistoryQuery() {
        return new ProductHistoryQuery();
    }

    /**
     * @return {@link BulkLookupQuery}
     */
    public static BulkLookupQuery newBulkLookupQuery() {
        return new BulkLookupQuery();
    }

    /**
     * @return {@link JobQuery}
     */
    public static JobQuery newJobQuery() {
        return new JobQuery();
    }
}
