package models.product.productAtStore.offer;

import java.util.List;

public class ProductOfferHistory {

    private List<Long> timestampHistory;
    private List<Double> salePriceHistory;
    private List<Double> listPriceHistory;
    private String pid;

    public String getPid(){
        return pid;
    }

    public List<Long> getTimestampHistory(){
        return timestampHistory;
    }

    public List<Double> getSalePriceHistory(){
        return salePriceHistory;
    }

    public List<Double> getListPriceHistory(){
        return listPriceHistory;
    }
}
