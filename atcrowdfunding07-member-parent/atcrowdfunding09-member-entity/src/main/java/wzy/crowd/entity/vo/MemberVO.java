package wzy.crowd.entity.vo;

/**
 * @author 网中鱼
 * @date 2021/9/6-13:59
 */
public class MemberVO {

    private String loginacct;
    private String userpswd;
    private String username;
    private String phoneNum;
    private String email;
    private String code;

    public MemberVO(String loginacct, String userpswd, String username,
                    String phoneNum, String email, String code) {
        this.loginacct = loginacct;
        this.userpswd = userpswd;
        this.username = username;
        this.phoneNum = phoneNum;
        this.email = email;
        this.code = code;
    }

    public MemberVO() {
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "loginacct='" + loginacct + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", username='" + username + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
