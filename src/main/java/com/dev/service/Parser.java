package com.dev.service;

import java.io.File;
import java.util.List;

import com.dev.domain.SMS;

public interface  Parser {
	
	public abstract List<SMS> parse(File file);

}
