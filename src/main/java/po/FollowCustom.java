package po;

public class FollowCustom extends Follow {
	private String userPhone;
	private String userName;
	private String followUserPhone;
	private String followUserName;
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
	public String getFollowUserPhone() {
		return followUserPhone;
	}
	public void setFollowUserPhone(String followUserPhone) {
		this.followUserPhone = followUserPhone;
	}
	public String getFollowUserName() {
		return followUserName;
	}
	public void setFollowUserName(String followUserName) {
		this.followUserName = followUserName;
	}
}
