package client;

import exception.IndixApiException;
import models.metadataResponse.metadataResult.BrandsResult;
import models.metadataResponse.metadataResult.CategoriesResult;
import models.metadataResponse.metadataResult.StoresResult;
import query.MetadataQuery;
import query.Query;

public interface MetadataApi {

    /**
     * Search Stores - Lists all stores along with their IDs
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link StoresResult}
     * @throws {@link IndixApiException}
     */
    StoresResult getStores(Query query) throws IndixApiException;

    /**
     * Search Brands - Lists all brands along with their IDs
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link BrandsResult}
     * @throws {@link IndixApiException}
     */
    BrandsResult getBrands(Query query) throws IndixApiException;

    /**
     * Export Categories - Lists all categories along with their IDs and path
     * @param query Instance of {@link MetadataQuery} with appropriate parameters
     * @return {@link CategoriesResult}
     * @throws {@link IndixApiException}
     */
    CategoriesResult getCategories(Query query) throws IndixApiException;
}
