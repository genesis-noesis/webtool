package com.dev.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dev.domain.SMS;

public class CSVParserImpl implements Parser{
	
	private File file;
	
    public CSVParserImpl(File file) {
		super();
		this.file = file;
	}

	public List<SMS> parse() {
        String line = "";
        String cvsSplitBy = ",";
        List<SMS> smsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] sms = line.split(cvsSplitBy);
                smsList.add(new SMS(Long.parseLong(sms[0]), sms[1]));
                System.out.println("SMS [number= " + sms[0] + " , message=" + sms[1] + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		return smsList;
    }
}