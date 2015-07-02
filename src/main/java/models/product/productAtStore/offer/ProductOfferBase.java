package models.product.productAtStore.offer;

import java.util.List;
import java.util.Map;

public class ProductOfferBase {

    // Offer basic fields
    //
    private String pid;
    private String title;
    private int storeId;
    private String storeName;
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

    public ProductOfferBase() {
    }

    public String getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
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
