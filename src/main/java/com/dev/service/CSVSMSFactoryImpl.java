package com.dev.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.domain.SMS;

@Service
public class CSVSMSFactoryImpl extends SMSFactory implements Parser{

	public CSVSMSFactoryImpl() {
		super();
	}

	public List<SMS> parse(File file) {
        String line = "";
        String cvsSplitBy = ",";
        List<SMS> smsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] smsVal = line.split(cvsSplitBy);
                SMS sms = new SMS(Long.parseLong(smsVal[0]), smsVal[1]);
                smsList.add(sms);
                System.out.println("SMS [number= " + smsVal[0] + " , message=" + smsVal[1] + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		return smsList;
    }

	@Override
	Parser createParser() {
		return new CSVSMSFactoryImpl();
	}

}