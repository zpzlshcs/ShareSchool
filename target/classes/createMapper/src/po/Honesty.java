package po;

import java.util.Date;

public class Honesty {
    private Integer honestyId;

    private Integer userId;

    private Integer honestyScore;

    private Date honestyCreatetime;

    private Date honestyChangetime;

    public Integer getHonestyId() {
        return honestyId;
    }

    public void setHonestyId(Integer honestyId) {
        this.honestyId = honestyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHonestyScore() {
        return honestyScore;
    }

    public void setHonestyScore(Integer honestyScore) {
        this.honestyScore = honestyScore;
    }

    public Date getHonestyCreatetime() {
        return honestyCreatetime;
    }

    public void setHonestyCreatetime(Date honestyCreatetime) {
        this.honestyCreatetime = honestyCreatetime;
    }

    public Date getHonestyChangetime() {
        return honestyChangetime;
    }

    public void setHonestyChangetime(Date honestyChangetime) {
        this.honestyChangetime = honestyChangetime;
    }
}