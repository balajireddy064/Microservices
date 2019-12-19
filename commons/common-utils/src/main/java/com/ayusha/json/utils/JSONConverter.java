package com.ayusha.json.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
* This is JSON message formmater
*
* @author  Nagendra Kumar
* @version 1.0
* @since   2019-05-03 
*/
public class JSONConverter {
	
	
	/**
	 * string to object
	 */
	public static Object convertStringToPOJO(String message, Class classRef) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(message, classRef);
		} catch (JsonGenerationException e) { 
			e.printStackTrace(); 
		} catch (JsonMappingException e) { 
			e.printStackTrace(); 
		}catch (IOException e) {
			e.printStackTrace(); 
		}
		return null;
	}
	
	/**
	 * string to object
	 */
	public static String convertPOJOToString(Object pojo) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("Default JSON Stringggggggggg:" + mapper.writeValueAsString(pojo)); 
			System.out.println("formatted JSON Stringg \n" + mapper.defaultPrettyPrintingWriter().writeValueAsString(pojo)); 
			System.out.println("7777777777777777777777777777777777");
			mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			System.out.println("000000000000000000000000000000000000");
			return mapper.writeValueAsString(pojo);
		} catch (JsonGenerationException e) { 
			e.printStackTrace(); 
		} catch (JsonMappingException e) { 
			e.printStackTrace(); 
		}catch (IOException e) {
			e.printStackTrace(); 
		}
		return "";
	}
	
	

}
