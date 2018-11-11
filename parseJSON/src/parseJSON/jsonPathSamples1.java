package parseJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import net.minidev.json.JSONArray;

public class jsonPathSamples1 {
    public static void main(String[] args) throws Exception {
    	
    	// Read JSON file and convert it to a String
		String payload = "";
		JSONParser parser = new JSONParser();
    	FileReader fileReader;
    	HashMap<String, Object> retJson = new HashMap<String, Object>();

    	try {
			fileReader = new FileReader("SpoonyDataChunk.json");
			JSONObject json = (JSONObject) parser.parse(fileReader);
	    	payload = json.toJSONString();		// this command convert JSON object into a string
	        //System.out.println("Display JSON file as a string :" + payload);
	    	retJson = JsonParsing.createHashMapFromJsonString(payload);
	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
    	 Iterator<Entry<String, Object>> firstIterator = retJson.entrySet().iterator();
    	 // To store single elements first like Device Id, Transmit timestamp
    	    while (firstIterator.hasNext()) {
    	        Entry<String, Object> Key1 = firstIterator.next();
    	        System.out.println("---------" + Key1.getValue().getClass().toString());
    	        if (Key1.getValue().getClass().toString().equals("class java.lang.String")) {
    	        	//look for the $.key1 in the mapping .. if found store the value in the variable
    	        	System.out.println("$."+Key1.getKey());
    	        	System.out.println(Key1.getValue());
    	        	firstIterator.remove();
    	        } else if (Key1.getValue().getClass().toString().equals("class java.util.HashMap")) {
    	        	HashMap<String,String> hMap = new HashMap<String,String>();
    	        	hMap = (HashMap<String, String>) Key1.getValue();
    	        	Iterator<Entry<String, String>> secondIterator = hMap.entrySet().iterator();
    	        	while (secondIterator.hasNext()) {
    	        		Entry<String, String> Key2 = secondIterator.next();
    	        		//look for the $.key1.key2 in the mapping .. if found store the value in the variable (which variable) check for mapping key???
    	        		System.out.println("$." + Key1.getKey() + "." + Key2.getKey());
    	        		System.out.println(Key2.getValue());
    	        	}
    	        	firstIterator.remove();
    	        } else if (Key1.getValue().getClass().toString().equals("class java.util.ArrayList")) {
    	        	System.out.println("Array List");
    	        	retJson = JsonParsing.createHashMapFromJsonString(Key1.getValue().toString());
    	        	Iterator<Entry<String, Object>> loopIterator = retJson.entrySet().iterator();
    	        	while (loopIterator.hasNext()) {
    	        		Entry<String, Object> Key3 = loopIterator.next();
    	        		//look for the $.key1.key2 in the mapping .. if found store the value in the variable (which variable) check for mapping key???
    	        		System.out.println("$." + Key1.getKey() + "." + Key3.getKey());
    	        		System.out.println(Key3.getValue());
    	        	}
    	        }
    	    }
    	    
    	    //now break the array list from firstIterator until you get Strings .. and at the end of each first loop save the record in DB
    	    
    	    

		
		//JsonParserOld jp = new JsonParserOld(payload);
		//System.out.println(jp.getPathList());
		//System.out.println(jp.getPathTable());
		
		// Read JSON file and convert it to a String End
		//jsonPathMethods(payload);
    }
    public static void jsonPathMethods(String payload) {

		//Default configuration
    	Configuration confDef = Configuration.defaultConfiguration();
    	List<String> jsonPaths = JsonPath.using(confDef).parse(payload).read("$");

    	for (String path : jsonPaths) {
    	    System.out.println(path);
    	}

    	DocumentContext jsonContext = JsonPath.parse(payload);

    	String key = "$.elements.*";
    	System.out.println("value of address "+key+" ="+ jsonContext.read(key));
    	
    	String DeviceIdPath = "$['from']['deviceId']";
    	String transmitTimestamp = "$['t']";
    	//String MeasurementNamePath = "$.elements.[*]";
    	String MeasurementNamePath = "$.elements.[index1].n";
    	String recordsPath = "$.elements.[index1].records.[index2]";

    	String recordTimestampPath = "$.elements.[index1].records.[index2].t";
    	String MeasurementValuePath = "$.elements.[index1].records.[index2].v";
    	String qualityPath = "$.elements.[*1].records.[index2].q";
    	
    	

    	// Always Return LIST so you know you need to iterate for any key you read
    	int i=0;
    	Configuration conf = Configuration.defaultConfiguration()
    			.addOptions(Option.ALWAYS_RETURN_LIST)
    			.addOptions(Option.SUPPRESS_EXCEPTIONS);
    	// Read Device as a list
    	System.out.println(payload);
    	DocumentContext context = JsonPath.using(conf).parse(payload);
    	//List<String> device = context.read(DeviceIdPath);    	// Read measurementname as a list
    	//System.out.print("\nDevice List size: " + device.size() + " " + device.getClass());
    	String loopPath = "$.elements.[*]";
    	List<String> loopPathList = context.read(loopPath);    	// Read measurementname as a list

    	System.out.print("\nLoop List size: " + loopPathList.size() + " " + loopPathList + "\n");
    	
    	
    	System.exit(0);
    	
    	List<String> records = context.read(transmitTimestamp);

    	String str = MeasurementNamePath.replaceAll("index1","*");
    	str = MeasurementNamePath.replaceAll("index1","*");
    	List<String> measNameList = context.read(str);
    	List<String> memsRecord=new ArrayList<String>();
    	int recordNum=0;
    	
    	for(int p=0;p<measNameList.size(); p++) {
    		str = MeasurementNamePath.replaceAll("index1",Integer.toString(p));
    		List<String> measurementNameList =  context.read(str);
    		String measurementName=measurementNameList.get(0);
    		
    		str = recordsPath.replace("index1", Integer.toString(p));
    		str = str.replace("index2", "*");
    		List<String> recordsList =  context.read(str);
    		System.out.println("recordsList.size():"+recordsList.size());
    		for(int q = 0; q<recordsList.size();q++) {
    			String str1 = recordTimestampPath.replace("index1", Integer.toString(p));
    			str1 = str1.replace("index2", Integer.toString(q));
    			List<String> timestamp =  context.read(str1);
    			String str2 = MeasurementValuePath.replace("index1", Integer.toString(p));
    			str2 = str2.replace("index2", Integer.toString(q));
    			List<String> value = context.read(str2);
    			String result = measurementName+","+timestamp+","+value;
    			memsRecord.add(result);
    			recordNum++;
    		}
    	}
    	
    	System.out.println(memsRecord);
    }
}