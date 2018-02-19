package ir.oddrun.befrest.models.exception;

public class HttpRequestFailedException extends Exception {
    public HttpRequestFailedException() {
    }

    public HttpRequestFailedException(String message) {
        super(message);
    }

    public HttpRequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRequestFailedException(Throwable cause) {
        super(cause);
    }

    public HttpRequestFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
