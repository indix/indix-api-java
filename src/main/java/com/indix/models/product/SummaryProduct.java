package com.indix.models.product;

import java.util.List;

public class SummaryProduct {
    private String mpid;
    private String title;
    private int brandId;
    private String brandName;
    private int categoryId;
    private String categoryName;
    private String categoryIdPath;
    private String categoryNamePath;
    private String imageUrl;
    private List<String> urls;
    private List<String> upcs;
    private List<String> mpns;
    private String countryCode;
    private String currency;
    private long lastRecordedAt;
    private int storesCount;
    private int offersCount;
    private double minSalePrice;
    private double maxSalePrice;


    public String getMpid() {
        return mpid;
    }

    public String getTitle() {
        return title;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryIdPath() {
        return categoryIdPath;
    }

    public String getCategoryNamePath() {
        return categoryNamePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getUrls() {
        return urls;
    }

    public List<String> getUpcs() {
        return upcs;
    }

    public List<String> getMpns() {
        return mpns;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public long getLastRecordedAt() {
        return lastRecordedAt;
    }

    public int getStoresCount() {
        return storesCount;
    }

    public int getOffersCount() {
        return offersCount;
    }

    public double getMinSalePrice() {
        return minSalePrice;
    }

    public double getMaxSalePrice() {
        return maxSalePrice;
    }
}
