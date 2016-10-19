package com.indix.models.offer;

import java.util.List;

public class Offer {

    private double minSalePrice;
    private double minListPrice;
    private String availability;
    private String fulfilledBy;
    private String pid;
    private String productUrl;
    private String imageUrl;
    private List<String> additionalImageUrls;
    private String seller;
    private List<String> mpns;
    private List<String> upcs;
    private String sku;
    private long lastRecordedAt;

    public String getPid() {
        return pid;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getAdditionalImageUrls() {
        return additionalImageUrls;
    }

    public String getSeller() {
        return seller;
    }

    public List<String> getMpns() {
        return mpns;
    }

    public List<String> getUpcs() {
        return upcs;
    }

    public String getSku() {
        return sku;
    }

    public long getLastRecordedAt() {
        return lastRecordedAt;
    }

    public double getMinSalePrice() {
       return minSalePrice;
   }

    public double getMinListPrice() {
       return minListPrice;
   }

    public String getAvailability() {
       return availability;
   }

    public String getFulfilledBy() {
       return fulfilledBy;
   }
}
