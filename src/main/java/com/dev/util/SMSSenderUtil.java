package com.dev.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import com.dev.domain.SMS;

public class SMSSenderUtil {
	public static String sendMessageToKannel (SMS sms) throws IOException {
		// HTTP GET request
		String messageId = sms.getNumber()+"GUI"+getRandomNumberInRange(100000, 1000000);
		
		String dlrUrl = "http://52.66.156.150:8080/noesis-smpp/DLRRequestListener?dr=%a&smscid=%i&statuscd=%d&uniqID="+messageId+"&customerref=ashish"+
				"&recivetime="+System.currentTimeMillis()+"&dlrtype=9&mobile="+sms.getNumber()+"&submittime="+System.currentTimeMillis()
				+"&expiry=12senderid=NOESIS&carrierid=TELCONAME&circleid=DELHI&routeid=DELHI&systemid=%o";

		String url = "http://52.66.156.150:13013/cgi-bin/sendsms?username=test&password=test&smscid=test&from=NOESIS&to="+URLEncoder.encode(String.valueOf(sms.getNumber()),"UTF-8")+
				"&text="+URLEncoder.encode(sms.getMessage(),"UTF-8")+"&coding=0&dlr-mask=23&dlr-url="+URLEncoder.encode(dlrUrl, "UTF-8");

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();
	}


	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
