package query;

public class QueryFactory {

    public static SearchQuery newSearchQuery() {
        return new SearchQuery();
    }

    public static ProductDetailsQuery newProductDetailsQuery() {
        return new ProductDetailsQuery();
    }

    public static MetadataQuery newMetadataQuery() {
        return new MetadataQuery();
    }

    public static SuggestionsQuery newSuggestionsQuery() {
        return new SuggestionsQuery();
    }

    public static BulkProductsQuery newBulkQuery() {
        return new SearchQuery();
    }

    public static ProductHistoryQuery newProductHistoryQuery() {
        return new ProductHistoryQuery();
    }

    public static BulkLookupQuery newBulkLookupQuery() {
        return new BulkLookupQuery();
    }

    public static JobStatusQuery newJobStatusQuery() {
        return new JobStatusQuery();
    }
}
