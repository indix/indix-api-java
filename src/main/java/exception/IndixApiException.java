package exception;

/*
 * Base Indix API exception
 */
public class IndixApiException extends Exception {
    private int errorCode;

    public IndixApiException() {
        super();
    }

    public IndixApiException(String message) {
        super(message);
    }

    public IndixApiException(int _errorCode, String message) {
        super(message);
        errorCode = _errorCode;
    }

    public IndixApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndixApiException(Throwable cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
