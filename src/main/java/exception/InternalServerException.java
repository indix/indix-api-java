package exception;

import org.apache.http.HttpStatus;

/*
 * Indix exception corresponding to HTTP 500 error code
 */
public class InternalServerException extends IndixApiException {

    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
