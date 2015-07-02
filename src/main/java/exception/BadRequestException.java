package exception;

import org.apache.http.HttpStatus;

/*
 * Indix exception corresponding to HTTP 400 error code
 */
public class BadRequestException extends IndixApiException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(HttpStatus.SC_BAD_REQUEST, message);
    }
}
