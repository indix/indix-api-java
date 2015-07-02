# scarlet-client
Indix API Java client

Usage
==============

```
    // search for products.

    IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient();

    try {
        Query searchQuery = QueryFactory.newSearchQuery()
                .withQ("nike")
                .withCountryCode("US")
                .withAppId("123")
                .withAppKey("123");

        UniversalSearchResult sr = indixApiClient.getProductsUniversal(searchQuery);
        System.out.println(sr.getCount());
        System.out.println(sr.getProducts().size());
        System.out.println(sr.getFacets().size());
        System.out.println(sr.getProducts().get(0).getMpid());
    } finally {
        indixApiClient.close();
    }
```
