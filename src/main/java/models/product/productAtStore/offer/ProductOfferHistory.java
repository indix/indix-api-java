package models.product.productAtStore.offer;

import java.util.List;

public class ProductOfferHistory {

    private List<Long> timestampHistory;
    private List<Double> salePriceHistory;
    private List<Double> listPriceHistory;
    private String pid;
    private String seller;

    public String getPid(){
        return pid;
    }

    /**
     * @return The timestamp for when the product's data was crawled on the store's website
     * including the historic prices
     */
    public List<Long> getTimestampHistory(){
        return timestampHistory;
    }

    /**
     * @return The array of sale prices of the product at this store afetr promotions including historic prices
     */
    public List<Double> getSalePriceHistory(){
        return salePriceHistory;
    }

    /**
     * @return The array of list prices of the product at this store including historic prices
     */
    public List<Double> getListPriceHistory(){
        return listPriceHistory;
    }

    public String getSeller(){
        return seller;
    }
}
