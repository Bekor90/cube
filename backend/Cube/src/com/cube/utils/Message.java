package com.cube.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
	private Integer code;
	private String status;
	private Object data;
	
	public Message() {}
	
	public Message(Integer code, String status, Object data) {
		this.code = code;
		this.status = status;
		this.data = data;
	}

	public Message(Exception e) {
		this.data = e.getMessage();
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}	
}