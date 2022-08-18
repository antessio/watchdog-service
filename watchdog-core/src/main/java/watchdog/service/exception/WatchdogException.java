package watchdog.service.exception;

public class WatchdogException extends RuntimeException{
    public WatchdogException() {
    }

    public WatchdogException(String message) {
        super(message);
    }

    public WatchdogException(String message, Throwable cause) {
        super(message, cause);
    }

    public WatchdogException(Throwable cause) {
        super(cause);
    }

    public WatchdogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
