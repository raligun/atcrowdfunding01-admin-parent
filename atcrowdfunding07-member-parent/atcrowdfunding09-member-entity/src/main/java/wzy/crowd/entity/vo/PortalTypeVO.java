package wzy.crowd.entity.vo;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/8-19:11
 */
public class PortalTypeVO {
    private Integer id;
    private String name;
    private String remark;

    private List<PortalProjectVO> portalProjectVOList;

    @Override
    public String toString() {
        return "PortalTypeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", portalProjectVOList=" + portalProjectVOList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PortalProjectVO> getPortalProjectVOList() {
        return portalProjectVOList;
    }

    public void setPortalProjectVOList(List<PortalProjectVO> portalProjectVOList) {
        this.portalProjectVOList = portalProjectVOList;
    }

    public PortalTypeVO(String name, String remark, List<PortalProjectVO> portalProjectVOList) {
        this.name = name;
        this.remark = remark;
        this.portalProjectVOList = portalProjectVOList;
    }

    public PortalTypeVO() {
    }
}
