package wzy.crowd.exception;

/**
 * @author 网中鱼
 * @date 2021/8/28-10:31
 */
public class IllegalFieldException extends RuntimeException{
    private static final long serialVersionUID = 1497112879L;

//    public IllegalFieldException() {
//        super();
//    }
//    public IllegalFieldException(String message, Throwable cause, boolean enableSuppression,
//                                    boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
//    public IllegalFieldException(String message, Throwable cause) {
//        super(message, cause);
//    }
    public IllegalFieldException(String message) {
        super(message);
    }
//    public IllegalFieldException(Throwable cause) {
//        super(cause);
//    }
}
