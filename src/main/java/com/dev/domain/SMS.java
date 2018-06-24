package com.dev.domain;

import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WEBTOOL_SMS_DETAILS")
public class SMS {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private long mobileNumber;
	@NotNull
	private String message;
	private String messageID;
	private Date createdDate;
	private String senderId;
	@Column(name="sent_to_kannel", nullable=false)
	private boolean sentToKannel;
	@ManyToOne
	@JoinColumn(name="sms_file_id", nullable=false)
	private SMSFile smsFile;
	
	public SMS() {
	}

	public SMS(long number, String message) {
		super();
		this.mobileNumber = number;
		this.setMessage(message);
		this.createdDate = new Date();
		this.sentToKannel = false;
		this.messageID = number+"WEBTOOL"+getRandomNumberInRange(100000, 1000000);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SMS [number=" + mobileNumber + ", message=" + message + "]";
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the mobileNumber
	 */
	public long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the messageID
	 */
	public String getMessageID() {
		return messageID;
	}

	/**
	 * @param messageID the messageID to set
	 */
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the senderId
	 */
	public String getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the sentToKannel
	 */
	public boolean isSentToKannel() {
		return sentToKannel;
	}

	/**
	 * @param sentTOKannel the sentTOKannel to set
	 */
	public void setSentTOKannel(boolean sentToKannel) {
		this.sentToKannel = sentToKannel;
	}

	public SMSFile getSmsFile() {
		return smsFile;
	}

	public void setSmsFile(SMSFile smsFile) {
		this.smsFile = smsFile;
	}


	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
