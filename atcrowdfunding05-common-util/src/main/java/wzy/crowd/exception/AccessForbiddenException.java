package wzy.crowd.exception;

/**
 * @author 网中鱼
 * @date 2021/8/27-13:50
 */

public class AccessForbiddenException extends RuntimeException{

    private static final long serialVersionUID = 14911786222222879L;

    public AccessForbiddenException() {
        super();
    }
    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public AccessForbiddenException(String message, Throwable cause) {

        super(message, cause);
    }
    public AccessForbiddenException(String message) {
        super(message);
    }
    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }
}
