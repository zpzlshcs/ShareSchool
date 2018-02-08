package po;

public class MessageCustom extends Message{
	private String sendUserPhone;
	
	private String sendUserName;
	
    private String receiveUserPhone;
	
	private String receiveUserName;

	public String getSendUserPhone() {
		return sendUserPhone;
	}

	public void setSendUserPhone(String sendUserPhone) {
		this.sendUserPhone = sendUserPhone;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getReceiveUserPhone() {
		return receiveUserPhone;
	}

	public void setReceiveUserPhone(String receiveUserPhone) {
		this.receiveUserPhone = receiveUserPhone;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}
}
