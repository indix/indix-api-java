# Apiv2-Java-Client [![Build Status](https://travis-ci.org/ind9/apiv2-java-client.svg?branch=master)](https://travis-ci.org/ind9/apiv2-java-client)
Indix API Java client

##Usage :

The client needs to be first instantiated with the appropriate application id and key to be able to use
the different api endpoints. It can be done as follows:
```java
    String appId = "__app_id__";
    String appKey = "__app_key__";
    IndixApiClient indixApiClient = IndixApiClientFactory
                                    .newIndixApiClient(appId, appKey);
```

This instance can then used to query the different endpoints and obtain responses. Different types
of queries are available for different scenarios. Illustrations of each are specified below:

### Metadata Query

The following example shows how to list all stores, along with their IDs, matching the query term

```java
    try {
        MetadataQuery metadataQuery = QueryFactory.newMetadataQuery()
                                      .withQ("nike");

        StoresResult sr = indixApiClient.getStores(metadataQuery);
        System.out.println(sr.getStores().get(0).getCountryCode());
        System.out.println(sr.getStores().get(0).getId());
        System.out.println(sr.getStores().get(0).getName());
    } finally {
        indixApiClient.close();
    }
```

### Suggestions Query

The following example shows how to list all product search suggestions matching the query term.

```java
    try {
        SuggestionsQuery suggestionsQuery = QueryFactory.newSuggestionsQuery()
                .withCountryCode("US")
                .withQ("ni");

        SuggestionsResult sr = indixApiClient.getSuggestions(suggestionsQuery);
        System.out.println(sr.getSuggestions().size());
        System.out.pritnln(sr.getSuggestions().get(0).getSuggestion());
    } finally {
        indixApiClient.close();
    }
```
### Search Query

The following example shows how to search for products. It retrieves a list of products matching a variety of
query parameters with their offers and catalog info across stores

```java
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

### Search Query

The following example shows how to search for product details of a particular product with given mpid.
It returns summary information for a product.

```java
    try {
        ProductDetailsQuery productDetailsQuery =
         QueryFactory.newProductDetailsQuery()
                .withCountryCode("US")
                .withMpid(mpid);

        SummaryProductDetailsResult pr = indixApiClient
                                        .getProductDetailsUniversal(productDetailsQuery);
        System.out.println(pr.getUniversalProduct().getMpid());
        System.out.println(pr.getUniversalProduct().getTitle());

    } finally {
        indixApiClient.close();
    }
```
### Bulk Products Query

The following example shows how to request for a bulk search query which finds products against a list
of storeIds. It submits a job against the query and returns corresponding job id and status.

```java
    try {
        BulkProductsQuery bulkQuery = QueryFactory.newBulkQuery()
                .withCountryCode("US")
                .withStoreId(storeIdList);

        JobInfo ji = indixApiClient.postBulkJob(resource, bulkQuery);
        System.out.println(ji.getId());
        System.out.println(ji.getStatus());
    } finally {
        indixApiClient.close();
    }
```
### Bulk Lookup Query

The following example shows how to request for a bulk lookup query which finds products against a list
of attributes. A list of lookup queries, each with specific attributes as a json object, may be submitted as
a jsonlines file. It submits a job against the query and returns corresponding job id and status.

```java
    try {    
        File file = new File("filename.jsonl");
        BulkLookupQuery bulkLookupQuery = QueryFactory.newBulkLookupQuery()
                .withCountryCode("US")
                .withInputFile(file);
        JobInfo ji = indixApiClient.postBulkJob(resource, bulkLookupQuery);

        System.out.println(ji.getStatus());
        System.out.println(ji.getId());
    } finally {
        indixApiClient.close();
    }
```

### Job Status Query

The following example shows how to check the status of a job submitted by a bulk query.

```java
    try {
        JobQuery jobQuery = QueryFactory.newJobQuery()
                .withJobId(jobId);
        JobInfo ji = indixApiClient.getBulkJobStatus(jobQuery);

        System.out.println(ji.getId());
        System.out.println(ji.getStatus());
        System.out.println(ji.getCount());
    } finally {
        indixApiClient.close();
    }
```

The following example shows how to obtain the output of a bulk job, as requested against a job id.

```java
    try {
        JobSQuery jobQuery = QueryFactory.newJobQuery()
                            .withJobId(jobId);
        InputStream stream = indixApiClient.getBulkJobOutput(jobQuery);
        //convert inputStream to file, or use as required
    } finally {
        indixApiClient.close();
    }
```