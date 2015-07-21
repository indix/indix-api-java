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

    public static BulkSearchQuery newBulkQuery() {
        return new BulkSearchQuery();
    }
    public static ProductHistoryQuery newProductHistoryQuery() {
        return new ProductHistoryQuery();
    }

//    public static BulkQuery newBulkQuery(){
//        return newBulkQuery();
//    }

    public static BulkLookupQuery newBulkLookupQuery() { return new BulkLookupQuery(); }

    public static JobStatusQuery newJobStatusQuery() {
        return new JobStatusQuery();
    }
}
