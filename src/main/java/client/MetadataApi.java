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
    StoresResult searchStores(Query query) throws IndixApiException;

    /*
     * Search for brands
     */
    BrandsResult searchBrands(Query query) throws IndixApiException;

    /*
     * Export categories dump
     */
    CategoriesResult exportCategories(Query query) throws IndixApiException;
}
