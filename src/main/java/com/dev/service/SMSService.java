package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dao.SMSFileDao;
import com.dev.domain.SMSFile;

@Service
public class SMSService{

	@Autowired
	private SMSFileDao smsFileDao;
	
	public List<SMSFile> listFiles() {
		return (List<SMSFile>) this.smsFileDao.findAll();
	}

	public SMSFile findById(long id) {
		return this.smsFileDao.findById(id);
	}
	
}
