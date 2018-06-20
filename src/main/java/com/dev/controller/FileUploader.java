package com.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dev.service.CSVSMSService;
import com.dev.service.SMSService;

@Controller
public class FileUploader {

	@RequestMapping("/")
	public String index() {
		return "uploader";
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
		String fileType ="csv";
		SMSService smsService = getSMSService(fileType);
		smsService.process(file);
		return "uploader";
	}

	private SMSService getSMSService(String fileType) {
		if(fileType.equals("csv"))
		{
			return new CSVSMSService();
		}
		return null;
	}
}
