package client.impl;

/**
 * Generic template of the responses obtained from the different API endpoints
 */
class IndixApiResponse<T>
{
    private String message;
    private T result;

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}