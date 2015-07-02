package models.product.productAtStore.offer;

import java.util.List;
import java.util.Map;

public class ProductOfferCatalog extends ProductOfferBase {
    private Map<String, List<String>> attributes;
    private List<String> tags;
    private Map<String, List<String>> facetAttributes;

    public ProductOfferCatalog() {
    }

    public Map<String, List<String>> getAttributes() {
        return null;
    }

    public List<String> getTags() {
        return null;
    }

    public Map<String, List<String>> getFacetAttributes() {
        return null;
    }
}
