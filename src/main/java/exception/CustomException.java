package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CustomException extends Exception{
	
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
