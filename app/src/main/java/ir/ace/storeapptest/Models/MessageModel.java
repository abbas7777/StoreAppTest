package ir.ace.storeapptest.Models;

public class MessageModel{
	private String message;
	private String status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}
