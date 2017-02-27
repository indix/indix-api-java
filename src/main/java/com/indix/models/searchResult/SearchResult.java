package com.indix.models.searchResult;

import java.util.List;
import java.util.Map;

public class SearchResult {
    private int count;
    private Map<String, List<Facet>> facets;
    private Map<String, List<AttrFacet>> attrFacets;

    public int getCount() {
        return count;
    }

    public Map<String, List<Facet>> getFacets() {
        return facets;
    }

    public Map<String, List<AttrFacet>> getAttrFacets() {
        return attrFacets;
    }
}
