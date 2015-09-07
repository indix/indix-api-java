package client;

import exception.IndixApiException;
import models.productDetailsResponse.productDetailsResult.*;
import query.ProductDetailsQuery;

public interface ProductDetailsApi {

    /**
     * Product Details - Returns summary information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link SummaryProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    SummaryProductDetailsResult getProductDetailsSummary(ProductDetailsQuery query) throws IndixApiException;

    /**
     * Product Details - Returns offers standard information for a product from a single storeId
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link OffersProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    OffersProductDetailsResult getProductDetailsOffersStandard(ProductDetailsQuery query) throws IndixApiException;

    /**
     * Product Details - Returns offers premium information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link OffersProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    OffersProductDetailsResult getProductDetailsOffersPremium(ProductDetailsQuery query) throws IndixApiException;

    /**
     * Product Details - Returns catalog standard information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link CatalogPremiumProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    CatalogStandardProductDetailsResult getProductDetailsCatalogStandard(ProductDetailsQuery query) throws IndixApiException;

    /**
     * Product Details - Returns catalog premium information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link CatalogPremiumProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    CatalogPremiumProductDetailsResult getProductDetailsCatalogPremium(ProductDetailsQuery query) throws IndixApiException;

    /**
     * Product Details - Returns complete information for a product
     * @param query Instance of {@link ProductDetailsQuery} with appropriate parameters
     * @return {@link UniversalProductDetailsResult}
     * @throws {@link IndixApiException}
     */
    UniversalProductDetailsResult getProductDetailsUniversal(ProductDetailsQuery query) throws IndixApiException;
}
