package client;

import java.io.Closeable;

/**
 * Indix Api Client
 */
public interface IndixApiClient extends SearchApi, ProductDetailsApi, MetadataApi, SuggestionsApi, ProductHistoryApi, Closeable {
}
