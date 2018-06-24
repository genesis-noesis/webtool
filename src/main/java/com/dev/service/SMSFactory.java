package com.dev.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.dao.SMSDao;
import com.dev.dao.SMSFileDao;
import com.dev.domain.SMS;
import com.dev.domain.SMSFile;

@Service
public abstract class SMSFactory {

	abstract Parser createParser();
	@Autowired
	private SMSDao smsDao;
	@Autowired
	private SMSFileDao smsFileDao;

	public void process(String fileName, MultipartFile file)
	{
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
/*				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);*/
				File serverFile = new File(fileName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				Parser parser  = createParser();
				List<SMS> smsList = parser.parse(serverFile);
				serverFile.delete();
				SMSFile smsFile = new SMSFile(fileName,smsList);
				for(SMS sms : smsList)
				{
					sms.setSmsFile(smsFile);
//					SMSSenderUtil.sendMessageToKannel(sms);
				}
				this.smsFileDao.save(smsFile);

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("You failed to upload  => " + e.getMessage());
			}
		} else {
			System.err.println("You failed to upload because the file was empty.");
		}
	}

	/**
	 * @param smsDao the smsDao to set
	 */
	public void setSmsDao(SMSDao smsDao) {
		this.smsDao = smsDao;
	}

	/**
	 * @param smsFileDao the smsFileDao to set
	 */
	public void setSmsFileDao(SMSFileDao smsFileDao) {
		this.smsFileDao = smsFileDao;
	}
	
}
