package wzy.crowd.entity.vo;

import java.io.Serializable;

/**
 * @author 网中鱼
 * @date 2021/9/6-17:49
 */
public class LoginMemberVO implements Serializable {

    private static final long serialVersionUID = 149122222879L;

    private Integer MID;
    private String userName;
    private String email;

    @Override
    public String toString() {
        return "LoginMemberVO{" +
                "MID='" + MID + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getMID() {
        return MID;
    }

    public void setMID(int MID) {
        this.MID = MID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginMemberVO(Integer MID, String userName, String email) {
        this.MID = MID;
        this.userName = userName;
        this.email = email;
    }

    public LoginMemberVO() {
    }
}
