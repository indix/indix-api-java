package client;

import exception.IndixApiException;
import models.productDetailsResponse.productDetailsResult.*;
import query.ProductDetailsQuery;

public interface ProductDetailsApi {

    /*
     * Retrieve a product's summary response
     */
    SummaryProductDetailsResult getProductDetailsSummary(ProductDetailsQuery query) throws IndixApiException;

    /*
     * Retrieve a product's offers standard response
     */
    OffersProductDetailsResult getProductDetailsOffersStandard(ProductDetailsQuery query) throws IndixApiException;

    /*
     * Retrieve a product's offers premium response
     */
    OffersProductDetailsResult getProductDetailsOffersPremium(ProductDetailsQuery query) throws IndixApiException;

    /*
     * Retrieve a product's catalog standard response
     */
    CatalogStandardProductDetailsResult getProductDetailsCatalogStandard(ProductDetailsQuery query) throws IndixApiException;

    /*
     * Retrieve a product's catalog premium response
     */
    CatalogPremiumProductDetailsResult getProductDetailsCatalogPremium(ProductDetailsQuery query) throws IndixApiException;

    /*
     * Retrieve a product's universal response
     */
    UniversalProductDetailsResult getProductDetailsUniversal(ProductDetailsQuery query) throws IndixApiException;
}
