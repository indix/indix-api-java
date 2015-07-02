package models.product;

import java.util.List;
import java.util.Map;

public class CatalogStandardProduct extends SummaryProduct {
    private Map<String, List<String>> attributes;
    private List<String> tags;
    private Map<String, List<String>> facetAttributes;

    public CatalogStandardProduct() {
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
