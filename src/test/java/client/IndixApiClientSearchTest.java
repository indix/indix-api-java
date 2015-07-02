package client;

import client.impl.IndixApiClientFactory;
import common.ResourceUtils;
import exception.IndixApiException;
import exception.InternalServerException;
import httpClient.HttpClient;
import models.searchResponse.searchResult.*;
import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Test;
import query.Query;
import query.QueryFactory;
import query.SearchQuery;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class IndixApiClientSearchTest {

    @Test
    public void getProductsUniversal() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/universalSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            UniversalSearchResult sr = indixApiClient.getProductsUniversal(searchQuery);
            Assert.assertEquals(18672, sr.getCount());
            Assert.assertEquals(10, sr.getProducts().size());
            Assert.assertEquals(0, sr.getFacets().size());
            Assert.assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersPremium() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            Assert.assertEquals(18672, sr.getCount());
            Assert.assertEquals(10, sr.getProducts().size());
            Assert.assertEquals(0, sr.getFacets().size());
            Assert.assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsOffersStandard() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withStoreId(Arrays.asList(270))
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            Assert.assertEquals(11624, sr.getCount());
            Assert.assertEquals(10, sr.getProducts().size());
            Assert.assertEquals(0, sr.getFacets().size());
            Assert.assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogStandard() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            Assert.assertEquals(18672, sr.getCount());
            Assert.assertEquals(10, sr.getProducts().size());
            Assert.assertEquals(0, sr.getFacets().size());
            Assert.assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsCatalogPremium() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            SearchQuery searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            Assert.assertEquals(18672, sr.getCount());
            Assert.assertEquals(10, sr.getProducts().size());
            Assert.assertEquals(0, sr.getFacets().size());
            Assert.assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test
    public void getProductsSummary() throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/summarySearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            Assert.assertEquals(18672, sr.getCount());
            Assert.assertEquals(10, sr.getProducts().size());
            Assert.assertEquals(0, sr.getFacets().size());
            Assert.assertEquals("31c2300c8e33393cbe62762dbca01751", sr.getProducts().get(0).getMpid());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected= InternalServerException.class)
    public void getProductsCatalogPremiumFailsIfInputResponseIsMalformed0()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/universalSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsCatalogPremiumFailsIfInputResponseIsMalformed1()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsCatalogPremiumFailsIfInputResponseIsMalformed2()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogPremiumSearchResult sr = indixApiClient.getProductsCatalogPremium(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed0()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/universalSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed1()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed2()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsCatalogStandardFailsIfInputResponseIsMalformed3()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            CatalogStandardSearchResult sr = indixApiClient.getProductsCatalogStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsOffersPremiumFailsIfInputResponseIsMalformed0()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/universalSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsOffersPremiumFailsIfInputResponseIsMalformed1()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsOffersPremiumFailsIfInputResponseIsMalformed2()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersPremium(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsOffersStandardFailsIfInputResponseIsMalformed0()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/universalSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsOffersStandardFailsIfInputResponseIsMalformed1()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsOffersStandardFailsIfInputResponseIsMalformed2()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            OffersSearchResult sr = indixApiClient.getProductsOffersStandard(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed0()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/universalSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed1()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed2()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/catalogStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed3()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersPremiumSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }

    @Test(expected=InternalServerException.class)
    public void getProductsSummaryFailsIfInputResponseIsMalformed4()
            throws URISyntaxException, ClientProtocolException, IOException, IndixApiException {
        HttpClient mockHttpClient = new HttpClient() {
            public String GET(URI uri) throws ClientProtocolException, IOException, IndixApiException {
                return ResourceUtils.getTestResource(getClass().getClassLoader(), "search-json-responses0/offersStandardSearchResponse.json");
            }

            public void close() throws IOException { }
        };

        IndixApiClient indixApiClient = IndixApiClientFactory.newIndixApiClient(mockHttpClient);

        try {
            Query searchQuery = QueryFactory.newSearchQuery()
                    .withQ("nike")
                    .withCountryCode("US")
                    .withStoresCount(3)
                    .withAppId("123")
                    .withAppKey("123");

            SummarySearchResult sr = indixApiClient.getProductsSummary(searchQuery);
            System.out.println(sr.getCount());
        } finally {
            indixApiClient.close();
        }
    }
}
