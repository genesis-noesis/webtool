package com.dev.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.dev.dao.SMSDao;
import com.dev.domain.SMS;

@Component
public class MessageSenderThread {

	@Autowired
	private SMSDao smsDao;

	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(MessageSenderThread.class);

	private ExecutorService executorService;

	@PostConstruct
	public void init() {

		BasicThreadFactory factory = new BasicThreadFactory.Builder().namingPattern("myspringbean-thread-%d").build();

		executorService = Executors.newSingleThreadExecutor(factory);
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("Thread started to send messages to Kannel");
					List<SMS> smsList = smsDao.findBySentToKannel(false, new PageRequest(0, 500));
					for (SMS sms : smsList) {
						System.out.println("Found SMS to send with list size: " + smsList.size());
						try {
							SMSSenderUtil.sendMessageToKannel(sms);
							sms.setSentTOKannel(true);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					smsDao.saveAll(smsList);
					try {
						if (smsList==null || smsList.size()==0) {
							System.out.println("Thread going to sleep for one minute");
							Thread.sleep(60000);
						}else {
							System.out.println("Thread going to sleep");
							Thread.sleep(50);
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		executorService.shutdown();
	}

	@PreDestroy
	public void beandestroy() {
		if (executorService != null) {
			executorService.shutdownNow();
		}
	}

}