package query;

public interface ApiParameters extends Query {

    enum Availability {
        IN_STOCK, OUT_OF_STOCK
    }

    enum PriceChange {
        PRICE_INCREASED, PRICE_DECREASED, EITHER
    }

    enum ApplyFiltersTo {
        storeId, alsoSoldAt, storeIdOrAlsoSoldAt, any
    }

    enum SelectOffersFrom {
        storeId, alsoSoldAt, storeIdAndAlsoSoldAt, all
    }

    enum SortBy {
        PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, MOST_RECENT
    }
}
