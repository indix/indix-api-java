package com.indix.client;

/**
 * Defines all the views that the API supports. It defines the output view of the responses obtained from the queries
 */
public enum ProductsViewType {
    SUMMARY("summary"),
    OFFERS_STANDARD("offersStandard"),
    OFFERS_PREMIUM("offersPremium"),
    CATALOG_STANDARD("catalogStandard"),
    CATALOG_PREMIUM("catalogPremium"),
    UNIVERSAL ("universal");

    private final String resource;

    private ProductsViewType(final String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return resource;
    }
}