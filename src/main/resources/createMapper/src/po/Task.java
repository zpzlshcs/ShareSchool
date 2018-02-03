package po;

import java.util.Date;

public class Task {
    private Integer taskId;

    private String taskTitle;

    private Integer userId;

    private String taskUserName;

    private Integer taskSchedule;

    private Integer taskState;

    private Integer taskIsDelete;

    private Integer taskType;

    private String taskContent;

    private Double taskPrice;

    private Integer taskCounts;

    private Integer taskRestcounts;

    private String taskImg;

    private Date taskCreatetime;

    private Date taskEndtime;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle == null ? null : taskTitle.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTaskUserName() {
        return taskUserName;
    }

    public void setTaskUserName(String taskUserName) {
        this.taskUserName = taskUserName == null ? null : taskUserName.trim();
    }

    public Integer getTaskSchedule() {
        return taskSchedule;
    }

    public void setTaskSchedule(Integer taskSchedule) {
        this.taskSchedule = taskSchedule;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    public Integer getTaskIsDelete() {
        return taskIsDelete;
    }

    public void setTaskIsDelete(Integer taskIsDelete) {
        this.taskIsDelete = taskIsDelete;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Integer getTaskCounts() {
        return taskCounts;
    }

    public void setTaskCounts(Integer taskCounts) {
        this.taskCounts = taskCounts;
    }

    public Integer getTaskRestcounts() {
        return taskRestcounts;
    }

    public void setTaskRestcounts(Integer taskRestcounts) {
        this.taskRestcounts = taskRestcounts;
    }

    public String getTaskImg() {
        return taskImg;
    }

    public void setTaskImg(String taskImg) {
        this.taskImg = taskImg == null ? null : taskImg.trim();
    }

    public Date getTaskCreatetime() {
        return taskCreatetime;
    }

    public void setTaskCreatetime(Date taskCreatetime) {
        this.taskCreatetime = taskCreatetime;
    }

    public Date getTaskEndtime() {
        return taskEndtime;
    }

    public void setTaskEndtime(Date taskEndtime) {
        this.taskEndtime = taskEndtime;
    }
}