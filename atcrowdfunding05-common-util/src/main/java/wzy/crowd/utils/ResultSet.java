package wzy.crowd.utils;

/**
 * @author 网中鱼
 * @date 2021/8/26-15:13
 */
public class ResultSet {
    private Integer code;
    private String message;
    private Object data;



    public static ResultSet success(){
        return new ResultSet(200,"SUCCESS");
    }
    public static ResultSet success(String message){
        return new ResultSet(200,message);
    }
    public static ResultSet error(){
        return new ResultSet(400,"ERROR");
    }
    public static ResultSet error(String message){
        return new ResultSet(400,message);
    }

    public ResultSet addData(Object data){
        this.data = data;
        return this;
    }


    public ResultSet(Integer code, String message, Object date) {
        this.code = code;
        this.message = message;
        this.data = date;
    }

    public ResultSet(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultSet() {
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
