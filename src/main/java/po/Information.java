package po;

import java.util.Date;

public class Information {
    private Integer infoId;

    private Integer userId;

    private Integer infoSex;

    private String infoRealname;

    private String infoIconimg;

    private Integer infoSchoolId;

    private String infoIntroduce;

    private String infoNickname;

    private Date infoBirthday;

    private Date infoCreatetime;

    private Date infoChangetime;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInfoSex() {
        return infoSex;
    }

    public void setInfoSex(Integer infoSex) {
        this.infoSex = infoSex;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname == null ? null : infoRealname.trim();
    }

    public String getInfoIconimg() {
        return infoIconimg;
    }

    public void setInfoIconimg(String infoIconimg) {
        this.infoIconimg = infoIconimg == null ? null : infoIconimg.trim();
    }

    public Integer getInfoSchoolId() {
        return infoSchoolId;
    }

    public void setInfoSchoolId(Integer infoSchoolId) {
        this.infoSchoolId = infoSchoolId;
    }

    public String getInfoIntroduce() {
        return infoIntroduce;
    }

    public void setInfoIntroduce(String infoIntroduce) {
        this.infoIntroduce = infoIntroduce == null ? null : infoIntroduce.trim();
    }

    public String getInfoNickname() {
        return infoNickname;
    }

    public void setInfoNickname(String infoNickname) {
        this.infoNickname = infoNickname == null ? null : infoNickname.trim();
    }

    public Date getInfoBirthday() {
        return infoBirthday;
    }

    public void setInfoBirthday(Date infoBirthday) {
        this.infoBirthday = infoBirthday;
    }

    public Date getInfoCreatetime() {
        return infoCreatetime;
    }

    public void setInfoCreatetime(Date infoCreatetime) {
        this.infoCreatetime = infoCreatetime;
    }

    public Date getInfoChangetime() {
        return infoChangetime;
    }

    public void setInfoChangetime(Date infoChangetime) {
        this.infoChangetime = infoChangetime;
    }
}