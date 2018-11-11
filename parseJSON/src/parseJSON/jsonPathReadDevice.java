package parseJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;

import net.minidev.json.JSONArray;

public class jsonPathReadDevice {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
    	FileReader fileReader;
		try {
			fileReader = new FileReader("SpoonyDataChunk.json");
			JSONObject json = (JSONObject) parser.parse(fileReader);
			String payload;
	    	payload = json.toJSONString();		// this command convert JSON object into a string
	        //System.out.println("Display JSON file as a string :" + payload);
	    	covertOutgoingJSONMessageToMEMSFormat(payload);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}
	public static void covertOutgoingJSONMessageToMEMSFormat(String payload) throws Exception {


    	// Always Return LIST so you know you need to iterate for any key you read
    	int i=0;
    	Configuration conf = Configuration.defaultConfiguration()
    			.addOptions(Option.ALWAYS_RETURN_LIST)
    			.addOptions(Option.SUPPRESS_EXCEPTIONS);

    	DocumentContext jsonContext = JsonPath.using(conf).parse(payload);
    	
    	//net.minidev.json.JSONArray arrDevice = JsonPath.parse(memsDevices).read("$[?(@.DEVICE_ID == '" + mf.getDEVICE_ID() + "')]");

    	/*
    	List<String> transTime = JsonPath.using(conf).parse(payload).read("$['t']");
    	for (i=0;i<transTime.size();i++) {
    		//mf.setTRANSMIT_TIMESTAMP(transTime.get(i));
    	}
*/

    	String tmstmp = "$['t']";
    	String MeasurementNamePath = "$['elements'][0]['n']";
    	String recordsPath = "$.elements.[*].records.[*]";	// We don't get this from the UI????
    	String recordTimestampPath = "$['elements'][*]['records'][*]['t']";

    	System.out.println(jsonContext.read(tmstmp).getClass());
    	System.out.println(jsonContext.read(MeasurementNamePath).getClass());
    	
    	JSONArray json = jsonContext.read(MeasurementNamePath);
    	///JSONObject object = new JSONObject();
    	for (int s=0;s<json.size();s++) {
    		System.out.println(json.get(s));
    		
    	}
    	System.out.println(json.size());
    	
        if (MeasurementNamePath != null && !MeasurementNamePath.isEmpty()) {
        	String str = MeasurementNamePath.replaceAll("index1","*");
        	str = MeasurementNamePath.replaceAll("index1","*");
        	List<String> measNameList = jsonContext.read(str);
        	
        	int p,q;

        	//loop to iterate Measurement Names
        	for (p=0;p<measNameList.size();p++) {
        		str = MeasurementNamePath.replaceAll("index1",Integer.toString(p));
        		List<String> measurementNameList =  jsonContext.read(str);
        		//mf.setINPUT_MEASUREMENT_NAME(measurementNameList.get(0));

        		str = recordsPath.replace("index1", Integer.toString(p));
        		str = str.replace("index2", "*");
        		List<String> recordsList =  jsonContext.read(str);
        		//loop to iterate Measurement Records like value, record timestamp, multiplier, quality
            	for(q=0;q<recordsList.size();q++) {

            		// Reading recordTimestamp
            		//mf.setRECORD_TIMESTAMP("");
            		if (recordTimestampPath != null && !recordTimestampPath.isEmpty()) {
            			String str1 = recordTimestampPath.replace("index1", Integer.toString(p));
            			str1 = str1.replace("index2", Integer.toString(q));
            			List<String> timestamp =  jsonContext.read(str1);
                    	for (i=0;i<timestamp.size();i++) {
                    		//mf.setRECORD_TIMESTAMP(timestamp.get(i));
                    	}
                		//System.out.println(mf.getRECORD_TIMESTAMP());
            		}
            		
            	}
        	}
        }
    }

}
