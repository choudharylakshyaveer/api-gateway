package com.apiGateway.repository.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@Data //@NoArgsConstructor @AllArgsConstructor 
@ToString
@Document
public class Message {
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Message(String content) {
		super();
		this.content = content;
	}

	public Message() {
		super();
	}
	
	
}
