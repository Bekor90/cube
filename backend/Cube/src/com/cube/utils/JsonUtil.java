package com.cube.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public JsonUtil() {
		
	}
	
	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(data);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("JsonUtil RuntimeException");
		}
	}
}
