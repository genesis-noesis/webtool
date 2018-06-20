package com.dev.service;

import java.util.List;

import com.dev.domain.SMS;

public interface  Parser {
	
	public abstract List<SMS> parse();

}
