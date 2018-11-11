package parseJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import net.minidev.json.JSONArray;

public class jsonPathSamples {
    public static void main(String[] args) throws Exception {
    	
    	// Read JSON file and convert it to a String
		String payload = "";
		JSONParser parser = new JSONParser();
    	FileReader fileReader;
		try {
			fileReader = new FileReader("SpoonyDataChunk.json");
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
		System.out.println(payload);
		// Read JSON file and convert it to a String End
		jsonPathMethods(payload);
    }
    public static void jsonPathMethods(String payload) {
    	DocumentContext jsonContext = JsonPath.parse(payload);
    	String key = "$.elements.*";
    	System.out.println("value of address "+key+" ="+ jsonContext.read(key));
    	
    	String DeviceIdPath = "$['from']['deviceId']";
    	String transmitTimestamp = "$['t']";
    	String MeasurementNamePath = "$.elements.[*]";
    	//String MeasurementNamePath = "$.elements.[*].n";
    	String recordsPath = "$.elements.[*].records.[*]";

    	String recordTimestampPath = "$.elements.[*].records.[*].t";
    	String MeasurementValuePath = "$.elements.[*].records.[*].v";
    	String qualityPath = "$.elements.[*].records.[*].q";
    	
    	System.out.println("------Single Element---------------------------------");
    	System.out.println(" DeviceId = "+ jsonContext.read(DeviceIdPath));
    	System.out.println(" Transmit Timestamp = "+ jsonContext.read(transmitTimestamp));
    	System.out.println("-----------LIST/Array Elements----------------------------");
    	System.out.println(" MeasurementName(s) = "+ jsonContext.read(MeasurementNamePath));
    	System.out.println(" recordTimestamp(s) = "+ jsonContext.read(recordTimestampPath));
    	System.out.println(" MeasurementValue(s) = "+ jsonContext.read(MeasurementValuePath));
    	System.out.println(" Quality(s) = "+ jsonContext.read(qualityPath));
    	
    	System.out.println(DeviceIdPath.getClass());
    	System.out.println(MeasurementNamePath.getClass());
    	
		
    	// Always Return LIST so you know you need to iterate for any key you read
    	int i=0;
    	Configuration conf = Configuration.defaultConfiguration()
    			.addOptions(Option.ALWAYS_RETURN_LIST)
    			.addOptions(Option.SUPPRESS_EXCEPTIONS);
    	// Read Device as a list
    	System.out.println(payload);
    	List<String> device = JsonPath.using(conf).parse(payload).read(DeviceIdPath);
    	System.out.println(device);
    	System.out.println(device.getClass());
    	for (i=0;i<device.size();i++) {
    		System.out.println(device.get(i));
    	}

    	// Read measurementname as a list
    	List<String> measName = JsonPath.using(conf).parse(payload).read(recordsPath);
    	List<String> measurements = JsonPath.using(conf).parse(payload).read(MeasurementNamePath);
    	for(i = 0; i<measName.size(); i++) {
    		Object obj= measName.get(i);
    		
    		if(obj.getClass().toString().equalsIgnoreCase("class java.util.LinkedHashMap")) {
    			HashMap record = (HashMap) obj;
    			for(int j=0; j<record.size(); j++) {
    				
    			}
    		}
    	}
    	
    	
    	System.out.println(measName);
    	System.out.println(measName.getClass());
    	for (i=0;i<measName.size();i++) {
    		System.out.println(measName.get(i));
    		// get length of an element
    		String lenOf="$.elements.[" + i +"].records.length()";
        	int len1 = JsonPath.parse(payload).read(lenOf);
        	for(int j=0;j<len1;j++) {
        		String rec="$.elements.[" + i +"].records.[" + j + "].t";
        		String recTime=jsonContext.read(rec);
        		System.out.println(recTime);
        		rec="$.elements.[" + i +"].records.[" + j + "].v";
        		Double recValue=jsonContext.read(rec);
        		System.out.println(recValue);
        	}
    		System.out.println("Length " + len1);
    	}
    }
}
