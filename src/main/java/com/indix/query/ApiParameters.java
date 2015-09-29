package com.indix.query;

/**
 * Defines a set of standard predefined values to be used for some query parameters
 */
public interface ApiParameters extends Query {

    /**
     * Used to specify in-stock or out-of-stock items.
     */
    enum Availability {
        IN_STOCK, OUT_OF_STOCK
    }

    /**
     * Used to depict products whose prices have been increased or decreased.
     */
    enum PriceChange {
        PRICE_INCREASED, PRICE_DECREASED, EITHER
    }

    /**
     * Allowed Values-
     * storeId (apply filters to only stores in storeId), alsoSoldAt (apply filters to only stores in alsoSoldAt),
     * storeIdOrAlsoSoldAt (apply filters to stores present in both storeId and alsoSoldAt),
     * any (apply filters across all offers)
     */
    enum ApplyFiltersTo {
        storeId, alsoSoldAt, storeIdOrAlsoSoldAt, any
    }

    /**
     *  Allowed Values-
     *  storeId (select offers matching filters from storeId), alsoSoldAt (select offers matching filters
     *  from alsoSoldAt), storeIdAndAlsoSoldAt (select offers matching filters from stores in both storeId
     *  and alsoSoldAt), all (select all offers irrespective of matching or storeId or alsoSoldAt data)
     */
    enum SelectOffersFrom {
        storeId, alsoSoldAt, storeIdAndAlsoSoldAt, all
    }

    /**
     * Specifies the sorting strategy of the results
     */
    enum SortBy {
        PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, MOST_RECENT
    }
}
