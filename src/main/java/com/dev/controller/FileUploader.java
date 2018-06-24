package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dev.domain.SMSFile;
import com.dev.service.CSVSMSFactoryImpl;
import com.dev.service.SMSFactory;
import com.dev.service.SMSService;

@Controller
public class FileUploader {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SMSService smsService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam String name,
			@RequestParam String password) {

		model.put("name", name);
		return "uploader";
	}

	@RequestMapping("/")
	public String index() {
		return "uploader";
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file,
									@RequestParam("fileName") String fileName) {
		String fileType ="csv";
		SMSFactory smsService = getSMSFactory(fileType);
		smsService.process(fileName,file);
		return "redirect:list";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		
		List<SMSFile> files = this.smsService.listFiles();
		model.addAttribute("files",	files);
		return "listFiles";
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam long id) {
		SMSFile file = smsService.findById(id);
		model.addAttribute("file", file);
		return "showFile";
	}

	private SMSFactory getSMSFactory(String fileType) {
		if(fileType.equals("csv"))
		{
			return applicationContext.getBean(CSVSMSFactoryImpl.class);
		}
		return null;
	}
}
