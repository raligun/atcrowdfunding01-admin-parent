package wzy.crowd.exception;

/**
 * @author 网中鱼
 * @date 2021/8/28-10:27
 */
public class AccountExistException extends RuntimeException{
    private static final long serialVersionUID = 14922552879L;

    private String viewName;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

//    public AccountExistException() {
//        super();
//    }
//    public AccountExistException(String message, Throwable cause, boolean enableSuppression,
//                                    boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
    public AccountExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public AccountExistException(String message,String viewName) {
        super(message);
        this.viewName = viewName;
    }
//    public AccountExistException(Throwable cause) {
//        super(cause);
//    }
}
