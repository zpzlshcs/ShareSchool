package utils;

import java.util.Date;

public class ResultBean<T> {
	int resultCode;
	String resultMessage;
	T resultData;
	Date resultTime;
	public Date getResultTime() {
		return resultTime;
	}
	public void setResultTime(Date resultTime) {
		this.resultTime = resultTime;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultMessage = ResultCode.getResultMessage(resultCode);
		this.resultCode = resultCode;
		this.resultTime = new Date();
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public T getResultData() {
		return resultData;
	}
	public void setResultData(T resultData) {
		this.resultData = resultData;
	}
	
}
