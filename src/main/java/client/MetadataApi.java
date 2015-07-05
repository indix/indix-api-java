package client;

import exception.IndixApiException;
import models.metadataResponse.metadataResult.BrandsResult;
import models.metadataResponse.metadataResult.CategoriesResult;
import models.metadataResponse.metadataResult.StoresResult;
import query.Query;

public interface MetadataApi {

    /*
     * Search for stores
     */
    StoresResult getStores(Query query) throws IndixApiException;

    /*
     * Search for brands
     */
    BrandsResult getBrands(Query query) throws IndixApiException;

    /*
     * Export categories dump
     */
    CategoriesResult getCategories(Query query) throws IndixApiException;
}
