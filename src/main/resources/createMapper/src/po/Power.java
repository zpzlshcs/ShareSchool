package po;

import java.util.Date;

public class Power {
    private Integer powerId;

    private String powerKey;

    private Integer userId;

    private Date powerCreatetime;

    private Date powerEndtime;

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowerKey() {
        return powerKey;
    }

    public void setPowerKey(String powerKey) {
        this.powerKey = powerKey == null ? null : powerKey.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPowerCreatetime() {
        return powerCreatetime;
    }

    public void setPowerCreatetime(Date powerCreatetime) {
        this.powerCreatetime = powerCreatetime;
    }

    public Date getPowerEndtime() {
        return powerEndtime;
    }

    public void setPowerEndtime(Date powerEndtime) {
        this.powerEndtime = powerEndtime;
    }
}