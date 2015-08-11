package query;

import java.util.List;

public interface BulkProductsQuery extends ApiParameters {

    public SearchQuery withAppId(String appId);

    public SearchQuery withAppKey(String appKey);

    public SearchQuery withStoreId(List<Integer> storeId);

    public SearchQuery withAlsoSoldAt(List<Integer> alsoSoldAt);

    public SearchQuery withBrandId(List<Integer> brandId);

    public SearchQuery withCategoryId(List<Integer> categoryId);

    public SearchQuery withStartPrice(double startPrice);

    public SearchQuery withEndPrice(double endPrice);

    public SearchQuery withAvailability(Availability availability);

    public SearchQuery withPriceHistoryAvailable(boolean priceHistoryAvailable);

    public SearchQuery withOnPromotion(boolean onPromotion);

    public SearchQuery withLastRecordedIn(int lastRecordedIn);

    public SearchQuery withStoresCount(int storesCount);

    public SearchQuery withPriceChange(PriceChange priceChange);

    public SearchQuery withApplyFilterTo(ApplyFiltersTo applyFiltersTo);

    public SearchQuery withSelectOffersFrom(SelectOffersFrom selectOffersFrom);

    public SearchQuery withCountryCode(String countryCode);
}
