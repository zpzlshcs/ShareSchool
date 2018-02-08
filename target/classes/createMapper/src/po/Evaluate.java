package po;

import java.util.Date;

public class Evaluate {
    private Integer evaluateId;

    private Integer userId;

    private Integer taskId;

    private String evaluateContent;

    private Integer evaluateType;

    private Integer evaluateLevel;

    private Integer evaluateUserId;

    private Date evaluateCreatetime;

    private Integer evaluateState;

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
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

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Integer getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(Integer evaluateType) {
        this.evaluateType = evaluateType;
    }

    public Integer getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Integer evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public Integer getEvaluateUserId() {
        return evaluateUserId;
    }

    public void setEvaluateUserId(Integer evaluateUserId) {
        this.evaluateUserId = evaluateUserId;
    }

    public Date getEvaluateCreatetime() {
        return evaluateCreatetime;
    }

    public void setEvaluateCreatetime(Date evaluateCreatetime) {
        this.evaluateCreatetime = evaluateCreatetime;
    }

    public Integer getEvaluateState() {
        return evaluateState;
    }

    public void setEvaluateState(Integer evaluateState) {
        this.evaluateState = evaluateState;
    }
}