package com.myOtherFiles;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class jsonusinggson {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		String payload = "";
		JSONParser parser = new JSONParser();
    	FileReader fileReader;
		try {
			fileReader = new FileReader("SpoonyDataChunk2.json");
			JSONObject json = (JSONObject) parser.parse(fileReader);
	    	payload = json.toJSONString();		// this command convert JSON object into a string
	        //System.out.println("Display JSON file as a string :" + payload);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JsonFactory factory = new JsonFactory();
	       ObjectMapper mapper = new ObjectMapper(factory);
	       JsonNode rootNode = mapper.readTree(payload);  

	       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
	       while (fieldsIterator.hasNext()) {

	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
	           System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
	       }

	}

}
