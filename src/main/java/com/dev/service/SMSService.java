package com.dev.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dev.domain.SMS;
import com.dev.util.SMSSenderUtil;

public abstract class SMSService {

	abstract Parser createParser(File file);
	
	public void process(MultipartFile file)
	{
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				System.out.println("Saving file");
/*				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);*/
				File serverFile = new File("test");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Server File Location="
						+ serverFile.getAbsolutePath());
				
				Parser parser  = createParser(serverFile);
				List<SMS> smsList = parser.parse();
				serverFile.delete();
				for(SMS sms : smsList)
				{
					SMSSenderUtil.sendMessageToKannel(sms);
				}
				System.out.println(smsList);
				System.out.println("Messages sent successfully");

			} catch (Exception e) {
				System.err.println("You failed to upload  => " + e.getMessage());
			}
		} else {
			System.err.println("You failed to upload because the file was empty.");
		}
	}
}
