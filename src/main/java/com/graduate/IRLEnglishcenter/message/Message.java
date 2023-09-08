package com.graduate.IRLEnglishcenter.message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Message {
private Message() {
		
	}
	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
	}
}
