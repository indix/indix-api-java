package query;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Index SearchQuery
 */
public class SearchQuery extends QueryParent<SearchQuery> {

    public SearchQuery() {
        super();
    }

    enum SortBy {
                PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, MOST_RECENT
    }
    public SearchQuery withUrl(String _url) {
        parameters.add(new BasicNameValuePair("url", _url));
        return this;
    }

    public SearchQuery withUpc(String _upc) {
        parameters.add(new BasicNameValuePair("upc", _upc));
        return this;
    }

    public SearchQuery withMpn(String _mpn) {
        parameters.add(new BasicNameValuePair("mpn", _mpn));
        return this;
    }

    public SearchQuery withSku(String _sku) {
        parameters.add(new BasicNameValuePair("sku", _sku));
        return this;
    }

    public SearchQuery withQ(String _q) {
             parameters.add(new BasicNameValuePair("q", _q));
                return this;
            }
    public SearchQuery withSortBy(SortBy _sortBy) {
        parameters.add(new BasicNameValuePair("sortBy", _sortBy.name()));
        return this;
    }

    public SearchQuery withFacetBy(List<String> _facetBy) {
        for (String _facet : _facetBy) {
            parameters.add(new BasicNameValuePair("facetBy", _facet));
        }
        return this;
    }

    public SearchQuery withPageNumber(int _pageNumber) {
        parameters.add(new BasicNameValuePair("pageNumber", String.valueOf(_pageNumber)));
        return this;
    }

    public SearchQuery withPageSize(int _pageSize) {
        parameters.add(new BasicNameValuePair("pageSize", String.valueOf(_pageSize)));
        return this;
    }
}
