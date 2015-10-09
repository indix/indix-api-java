package com.indix.models.product.productAtStore.offer;

import java.util.List;
import java.util.Map;

public class ProductOfferBase {

    // Offers basic fields
    //
    private String pid;
    private String productUrl;
    private String imageUrl;
    private List<String> additionalImageUrls;
    private String seller;
    private List<String> mpns;
    private List<String> upcs;
    private String sku;
    private long lastRecordedAt;

    // Secured fields
    //
    private Map<String, List<String>> privateAttributes;

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

    public Map<String, List<String>> getPrivateAttributes() {
        return privateAttributes;
    }
}
