package wzy.crowd.entity.vo;

import java.io.Serializable;

/**
 * @author 网中鱼
 * @date 2021/9/7-17:01
 */
public class MemberConfirmInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 易付宝账号
    private String paynum;
    // 法人身份证号
    private String cardnum;

    @Override
    public String toString() {
        return "MemberConfirmInfoVO{" +
                "paynum='" + paynum + '\'' +
                ", cardnum='" + cardnum + '\'' +
                '}';
    }

    public String getPaynum() {
        return paynum;
    }

    public void setPaynum(String paynum) {
        this.paynum = paynum;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public MemberConfirmInfoVO(String paynum, String cardnum) {
        this.paynum = paynum;
        this.cardnum = cardnum;
    }

    public MemberConfirmInfoVO() {
    }
}
