package com.dev.service;

import java.io.File;

public class CSVSMSService extends SMSService{

	@Override
	Parser createParser(File file) {
		return new CSVParserImpl(file);
	}
	
}
