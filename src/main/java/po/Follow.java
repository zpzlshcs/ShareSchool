package po;

import java.util.Date;

public class Follow {
    private Integer followId;

    private Integer userId;

    private Integer followUserId;

    private Integer followState;

    private Date followCreatetime;

    private Date followChangetime;

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }

    public Integer getFollowState() {
        return followState;
    }

    public void setFollowState(Integer followState) {
        this.followState = followState;
    }

    public Date getFollowCreatetime() {
        return followCreatetime;
    }

    public void setFollowCreatetime(Date followCreatetime) {
        this.followCreatetime = followCreatetime;
    }

    public Date getFollowChangetime() {
        return followChangetime;
    }

    public void setFollowChangetime(Date followChangetime) {
        this.followChangetime = followChangetime;
    }
}