package po;

import java.util.Date;

public class CommentCustom extends Comment{
	private String taskTitle;

    private Integer taskUserId;

    private String taskUserName;

    private Integer taskSchedule;

    private Integer taskState;

    private Integer taskType;

    private String taskContent;

    private Double taskPrice;

    private Integer taskCounts;

    private Integer taskRestcounts;

    private String taskImg;

    private Date taskCreatetime;

    private Date taskEndtime;
    
    private String userPhone;
    
    private String userName;
    
    private String commentToUserPhone;
    
    private String commentToUserName;

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public Integer getTaskUserId() {
		return taskUserId;
	}

	public void setTaskUserId(Integer taskUserId) {
		this.taskUserId = taskUserId;
	}

	public String getTaskUserName() {
		return taskUserName;
	}

	public void setTaskUserName(String taskUserName) {
		this.taskUserName = taskUserName;
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
		this.taskContent = taskContent;
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
		this.taskImg = taskImg;
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

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentToUserPhone() {
		return commentToUserPhone;
	}

	public void setCommentToUserPhone(String commentToUserPhone) {
		this.commentToUserPhone = commentToUserPhone;
	}

	public String getCommentToUserName() {
		return commentToUserName;
	}

	public void setCommentToUserName(String commentToUserName) {
		this.commentToUserName = commentToUserName;
	}
}
