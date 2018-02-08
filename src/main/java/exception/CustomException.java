package exception;

public class CustomException extends Exception{
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	int resultCode;
	String resultMessage;
	public CustomException(int resultCode,String resultMessage){
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
}
