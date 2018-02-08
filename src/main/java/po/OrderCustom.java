package po;

import java.util.Date;

public class OrderCustom extends Order{

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

	private String orderLaunchUserPhone;
    
    private String orderLaunchUserName;
    
    private String orderReceiveUserPhone;
    
    private String orderReceiveUserName;

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

	public String getOrderLaunchUserPhone() {
		return orderLaunchUserPhone;
	}

	public void setOrderLaunchUserPhone(String orderLaunchUserPhone) {
		this.orderLaunchUserPhone = orderLaunchUserPhone;
	}

	public String getOrderLaunchUserName() {
		return orderLaunchUserName;
	}

	public void setOrderLaunchUserName(String orderLaunchUserName) {
		this.orderLaunchUserName = orderLaunchUserName;
	}

	public String getOrderReceiveUserPhone() {
		return orderReceiveUserPhone;
	}

	public void setOrderReceiveUserPhone(String orderReceiveUserPhone) {
		this.orderReceiveUserPhone = orderReceiveUserPhone;
	}

	public String getOrderReceiveUserName() {
		return orderReceiveUserName;
	}

	public void setOrderReceiveUserName(String orderReceiveUserName) {
		this.orderReceiveUserName = orderReceiveUserName;
	}

    
}
