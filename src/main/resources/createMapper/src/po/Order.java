package po;

import java.util.Date;

public class Order {
    private Integer orderId;

    private Integer orderLaunchUserId;

    private Integer orderReceiveUserId;

    private Integer orderLaunchSchedule;

    private Integer orderReceiveSchedule;

    private Integer orderTaskId;

    private String orderDescribe;

    private Integer orderLaunchEvaluateState;

    private Integer orderReceiveEvaluateState;

    private Double orderPrice;

    private Integer orderState;

    private Integer orderLaunchState;

    private Integer orderReceiveState;

    private Date orderCreatetime;

    private Date orderCompleteTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderLaunchUserId() {
        return orderLaunchUserId;
    }

    public void setOrderLaunchUserId(Integer orderLaunchUserId) {
        this.orderLaunchUserId = orderLaunchUserId;
    }

    public Integer getOrderReceiveUserId() {
        return orderReceiveUserId;
    }

    public void setOrderReceiveUserId(Integer orderReceiveUserId) {
        this.orderReceiveUserId = orderReceiveUserId;
    }

    public Integer getOrderLaunchSchedule() {
        return orderLaunchSchedule;
    }

    public void setOrderLaunchSchedule(Integer orderLaunchSchedule) {
        this.orderLaunchSchedule = orderLaunchSchedule;
    }

    public Integer getOrderReceiveSchedule() {
        return orderReceiveSchedule;
    }

    public void setOrderReceiveSchedule(Integer orderReceiveSchedule) {
        this.orderReceiveSchedule = orderReceiveSchedule;
    }

    public Integer getOrderTaskId() {
        return orderTaskId;
    }

    public void setOrderTaskId(Integer orderTaskId) {
        this.orderTaskId = orderTaskId;
    }

    public String getOrderDescribe() {
        return orderDescribe;
    }

    public void setOrderDescribe(String orderDescribe) {
        this.orderDescribe = orderDescribe == null ? null : orderDescribe.trim();
    }

    public Integer getOrderLaunchEvaluateState() {
        return orderLaunchEvaluateState;
    }

    public void setOrderLaunchEvaluateState(Integer orderLaunchEvaluateState) {
        this.orderLaunchEvaluateState = orderLaunchEvaluateState;
    }

    public Integer getOrderReceiveEvaluateState() {
        return orderReceiveEvaluateState;
    }

    public void setOrderReceiveEvaluateState(Integer orderReceiveEvaluateState) {
        this.orderReceiveEvaluateState = orderReceiveEvaluateState;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderLaunchState() {
        return orderLaunchState;
    }

    public void setOrderLaunchState(Integer orderLaunchState) {
        this.orderLaunchState = orderLaunchState;
    }

    public Integer getOrderReceiveState() {
        return orderReceiveState;
    }

    public void setOrderReceiveState(Integer orderReceiveState) {
        this.orderReceiveState = orderReceiveState;
    }

    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }

    public Date getOrderCompleteTime() {
        return orderCompleteTime;
    }

    public void setOrderCompleteTime(Date orderCompleteTime) {
        this.orderCompleteTime = orderCompleteTime;
    }
}