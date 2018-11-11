package com.myOtherFiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class parseJSONWithoutKnowingFormat {

	public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
		// TODO Auto-generated method stub
		
		String payload=null;
		JSONParser parser = new JSONParser();
    	FileReader fileReader;
		try {
			fileReader = new FileReader("SpoonyDataChunk1.json");
			JSONObject json = (JSONObject) parser.parse(fileReader);
	    	parse(json.toString());
	    	JSONArray elements = (JSONArray) json.get("elements");
	    	System.out.println(json.get(""));
	    	System.out.println(elements.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//parse("{\"url\" : \"http://someurl.com\", \"method\" : \"POST\", \"isauth\" : false }");
		//parse(" {\"id\" : 12345, \"days\" : [ \"Monday\", \"Wednesday\" ], \"person\" : { \"firstName\" : \"David\", \"lastName\" : \"Menoyo\" } }\r\n" + 
		//		"");
		//parse("{\"deviceId\" : \"Controller\", \"description\" : \"Device Desc\", \"unit\": \"v\", \"t\": \"2018-09-05\", \"m\": \"kWh\", \"v\":\"139\"}");
		//SHRIKANT
		//String jsonString = "{\"Devices\" : {\"device1\" : {\"unit\" : \"Temp\", \"v\" : 24.3, \"t\": \"2016-07-05T15:13:53.998Z\"}, \"deice2\" : {\"unit\" : \"FREQ\", \"v\" : 29.76,  \"t\": \"2016-07-05T15:13:53.998Z\"}, \"device3\" : { \"unit\" : \"VRMSA\", \"v\" : 232.323, \"t\": \"2016-07-05T15:13:53.998Z\"}}}";
		//parse(payload);
		//parse("{\"Devices\" : {\"device1\" : {\"unit\" : \"Temp\", \"v\" : 24.3, \"t\": \"2016-07-05T15:13:53.998Z\"}, \"deice2\" : {\"unit\" : \"FREQ\", \"v\" : 29.76,  \"t\": \"2016-07-05T15:13:53.998Z\"}, \"device3\" : { \"unit\" : \"VRMSA\", \"v\" : 232.323, \"t\": \"2016-07-05T15:13:53.998Z\"}}}");
		//parse("{\"descriptor\" : {\"app1\" : {\"name\" : \"mehdi\", \"age\" : 21, \"messages\": [\"msg 1\",\"msg 2\",\"msg 3\"]}, \"app2\" : {\"name\" : \"mkyong\", \"age\" : 29,  \"messages\": [\"msg 11\",\"msg 22\",\"msg 33\"]}, \"app3\" : { \"name\" : \"amine\", \"age\" : 23, \"messages\": [\"msg 111\",\"msg 222\",\"msg 333\"]}}}");
		//extractParent();
	}
	
	public static void parse(String json) throws IOException, ParseException, java.text.ParseException  {
		//1st way to iterate to JSON
		JsonFactory factory = new JsonFactory();

	       ObjectMapper mapper = new ObjectMapper(factory);
	       JsonNode rootNode = mapper.readTree(json);  

	       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
	       while (fieldsIterator.hasNext()) {

	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
	           System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
	       }
	    //2nd way - read any node -
	       String device = "$['from']['deviceId']";
	       
	       String measname = "$['elements.1.n']";
	       String valuepath = "$['elements'][1]['records'][0]['v']";
	       String timestamppath = "$['elements'][1]['records'][0]['t']";
	       DocumentContext jsonContext = JsonPath.parse(json);
	       String measname1 = jsonContext.read(measname);
	       Double valuepath1 = jsonContext.read(valuepath);
	       String timestamppath1 = jsonContext.read(timestamppath);
	       System.out.println(measname1);
	       System.out.println(valuepath1);
	       System.out.println(timestamppath1);
	   //check instr
	       String strcontain = "$['elements'][0]['records'][0]['v']";
	       Pattern p = Pattern.compile("\\[\\d\\]");
	       Matcher m = p.matcher(strcontain);
	       while(m.find()) {
	    	    System.out.println(m.group(0));
	    	    //System.out.println(m.group(1));
	    	}
	       
	       if (strcontain.matches(".*\\[\\d\\].*") || strcontain.matches(".*\\[\\d\\d\\].*")) {
	    	   System.out.println("Yes it contain digit with bracket");
	       }
	       else {
	    	   System.out.println("No it does not contain digit with bracket");
	       }
	       
	}
}
