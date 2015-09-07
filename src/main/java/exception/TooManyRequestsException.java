package exception;

/**
 * Indix exception corresponding to HTTP 429 error code
 */
public class TooManyRequestsException extends IndixApiException {

    public TooManyRequestsException() {
        super();
    }

    public TooManyRequestsException(String message) {
        super(429, message);
    }
}
