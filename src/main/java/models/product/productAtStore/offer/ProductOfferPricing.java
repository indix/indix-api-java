package models.product.productAtStore.offer;

public class ProductOfferPricing extends ProductOfferBase {

    // Offer pricing fields
    //
    private double salePrice;
    private double listPrice;
    private String availability;
    private boolean priceHistoryAvailable;
    private boolean cartPrice;
    private boolean buyBoxWinner;
    private String fulfilledBy;
    private boolean addOnItem;
    private int newOffers;
    private int usedOffers;
    private int refurbishedOffers;
    private int userRatings;
    private double avgRating;
    private String shippingText;
    private int maxRating;
    private int salesRank;


    public double getSalePrice() {
        return salePrice;
    }

    public double getListPrice() {
        return listPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public boolean getPriceHistoryAvailable() {
        return priceHistoryAvailable;
    }

    public boolean getCartPrice() {
        return cartPrice;
    }

    public boolean getBuyBoxWinner() {
        return buyBoxWinner;
    }

    public String getFulfilledBy() {
        return fulfilledBy;
    }

    public boolean getAddOnItem() {
        return addOnItem;
    }

    public int getNewOffers() {
        return newOffers;
    }

    public int getUsedOffers() {
        return usedOffers;
    }

    public int getRefurbishedOffers() {
        return refurbishedOffers;
    }

    public int getUserRatings() {
        return userRatings;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public String getShippingText() {
        return shippingText;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public int getSalesRank() {
        return salesRank;
    }
}
