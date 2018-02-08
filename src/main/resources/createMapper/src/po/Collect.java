package po;

import java.util.Date;

public class Collect {
    private Integer collectId;

    private Integer userId;

    private Integer taskId;

    private Integer collectState;

    private String collectRemarks;

    private Date collectCreatetime;

    private Date collectChangetime;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCollectState() {
        return collectState;
    }

    public void setCollectState(Integer collectState) {
        this.collectState = collectState;
    }

    public String getCollectRemarks() {
        return collectRemarks;
    }

    public void setCollectRemarks(String collectRemarks) {
        this.collectRemarks = collectRemarks == null ? null : collectRemarks.trim();
    }

    public Date getCollectCreatetime() {
        return collectCreatetime;
    }

    public void setCollectCreatetime(Date collectCreatetime) {
        this.collectCreatetime = collectCreatetime;
    }

    public Date getCollectChangetime() {
        return collectChangetime;
    }

    public void setCollectChangetime(Date collectChangetime) {
        this.collectChangetime = collectChangetime;
    }
}