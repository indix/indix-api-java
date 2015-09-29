package models.searchResult;

import java.util.List;
import java.util.Map;

public class SearchResult {
    private int count;
    private Map<String, List<Facet>> facets;

    public int getCount() {
        return count;
    }

    public Map<String, List<Facet>> getFacets() {
        return facets;
    }
}
