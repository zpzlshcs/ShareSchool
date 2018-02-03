package po;

public class School {
    private Integer schoolId;

    private String schoolName;

    private Integer schoolIsHot;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Integer getSchoolIsHot() {
        return schoolIsHot;
    }

    public void setSchoolIsHot(Integer schoolIsHot) {
        this.schoolIsHot = schoolIsHot;
    }
}