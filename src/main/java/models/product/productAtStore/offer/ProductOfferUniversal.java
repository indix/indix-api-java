package models.product.productAtStore.offer;

import java.util.List;
import java.util.Map;

public class ProductOfferUniversal extends ProductOfferPricing {
    private Map<String, List<String>> attributes;
    private List<String> tags;
    private Map<String, List<String>> facetAttributes;

    public ProductOfferUniversal() {
    }

    public Map<String, List<String>> getAttributes() {
        return attributes;
    }

    public List<String> getTags() {
        return tags;
    }

    public Map<String, List<String>> getFacetAttributes() {
        return facetAttributes;
    }

}
