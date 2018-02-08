package po;

import java.util.Date;

public class Wallet {
    private Integer walletId;

    private Integer userId;

    private Double walletRest;

    private Date walletCreatetime;

    private Date walletChangetime;

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getWalletRest() {
        return walletRest;
    }

    public void setWalletRest(Double walletRest) {
        this.walletRest = walletRest;
    }

    public Date getWalletCreatetime() {
        return walletCreatetime;
    }

    public void setWalletCreatetime(Date walletCreatetime) {
        this.walletCreatetime = walletCreatetime;
    }

    public Date getWalletChangetime() {
        return walletChangetime;
    }

    public void setWalletChangetime(Date walletChangetime) {
        this.walletChangetime = walletChangetime;
    }
}