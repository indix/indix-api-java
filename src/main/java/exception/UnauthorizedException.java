package exception;

import org.apache.http.HttpStatus;

/*
 * Indix exception corresponding to HTTP 401 error code
 */
public class UnauthorizedException extends IndixApiException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(HttpStatus.SC_BAD_REQUEST, message);
    }
}
