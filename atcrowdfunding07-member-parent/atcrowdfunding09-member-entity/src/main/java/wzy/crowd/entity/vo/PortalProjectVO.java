package wzy.crowd.entity.vo;

/**
 * @author 网中鱼
 * @date 2021/9/8-19:13
 */
public class PortalProjectVO {

    private Integer projectId;
    private String projectName;
    private String headerPicturePath;
    private Integer money;
    private String deployDate;
    private String percentage;
    private Integer supporter;

    @Override
    public String toString() {
        return "PortalProjectVO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", headerPicturePaht='" + headerPicturePath + '\'' +
                ", money=" + money +
                ", deployDate='" + deployDate + '\'' +
                ", percentage='" + percentage + '\'' +
                ", supporter=" + supporter +
                '}';
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(String deployDate) {
        this.deployDate = deployDate;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getSupporter() {
        return supporter;
    }

    public void setSupporter(Integer supporter) {
        this.supporter = supporter;
    }

    public PortalProjectVO(String projectName, String headerPicturePaht, Integer money, String deployDate, String percentage, Integer supporter) {
        this.projectName = projectName;
        this.headerPicturePath = headerPicturePaht;
        this.money = money;
        this.deployDate = deployDate;
        this.percentage = percentage;
        this.supporter = supporter;
    }

    public PortalProjectVO() {
    }
}
