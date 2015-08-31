# scarlet-client
Indix API Java client

Usage
==============

```
    // search for products.

    String appId = "__app_id__";
    String appKey = "__app_key__";
    IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(appId, appKey);

    try {
        Query searchQuery = QueryFactory.newSearchQuery()
                .withQ("nike")
                .withCountryCode("US");

        UniversalSearchResult sr = indixApiClient.getProductsUniversal(searchQuery);
        System.out.println(sr.getCount());
        System.out.println(sr.getProducts().size());
        System.out.println(sr.getFacets().size());
        System.out.println(sr.getProducts().get(0).getMpid());
    } finally {
        indixApiClient.close();
    }
```
