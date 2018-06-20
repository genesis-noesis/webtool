package com.dev.domain;

public class SMS {

	private long number;
	private String message;
	
	
	public SMS(long number, String message) {
		super();
		this.number = number;
		this.setMessage(message);
	}
	
	public long getNumber() {
		return number;
	}
	
	public void setNumber(long number) {
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SMS [number=" + number + ", message=" + message + "]";
	}
	
	
}
