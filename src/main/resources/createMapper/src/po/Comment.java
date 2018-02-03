package po;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer userId;

    private Integer commentToUserId;

    private Integer taskId;

    private String commentContent;

    private Integer commentState;

    private Date commentCreatetime;

    private Date commentChangetime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentToUserId() {
        return commentToUserId;
    }

    public void setCommentToUserId(Integer commentToUserId) {
        this.commentToUserId = commentToUserId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }

    public Date getCommentCreatetime() {
        return commentCreatetime;
    }

    public void setCommentCreatetime(Date commentCreatetime) {
        this.commentCreatetime = commentCreatetime;
    }

    public Date getCommentChangetime() {
        return commentChangetime;
    }

    public void setCommentChangetime(Date commentChangetime) {
        this.commentChangetime = commentChangetime;
    }
}