package com.dev.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_File")
public class SMSFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date createdDate;
	private String fileName;
	@OneToMany(mappedBy="smsFile",cascade=CascadeType.ALL)
	private List<SMS> smsList;


	public SMSFile() {
	}

	public SMSFile(String fileName, List<SMS> smsList) {
		super();
		this.fileName = fileName;
		this.smsList = smsList;
		this.createdDate = new Date();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the smsList
	 */
	public List<SMS> getSmsList() {
		return smsList;
	}

	/**
	 * @param smsList the smsList to set
	 */
	public void setSmsList(List<SMS> smsList) {
		this.smsList = smsList;
	}
	
	public int getCount()
	{
		return this.smsList.size();
	}

}
