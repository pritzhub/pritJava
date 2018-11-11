package com.myOtherFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON2CSV {
    public static void main(String myHelpers[]){
 
  	
    	//String jsonString = "{\"infile\": [{\"field1\": 11,\"field2\": 12,\"field3\": 13},{\"field1\": 21,\"field2\": 22,\"field3\": 23},{\"field1\": 31,\"field2\": 32,\"field3\": 33}]}";
    	//String jsonString = "{\"from\": {\"deviceId\": \"SpoonyDotVisionDev\",\"unit\": \"ODMDataChunk\",\"locatin\":\"NAVY\"}, \"t\": \"2016-07-05T15:13:54.013Z\",\"count\": 29,\"elements\": [{\"n\": \"TEMP\",	\"count\": 1,	\"records\": [{\"i\": 2068,	\"t\": \"2016-07-05T15:13:53.998Z\",\"q\": \"good\",\"v\": 25.03737, \"multi\":10.33},{\"i\": 2068,\"t\": \"2016-07-05T15:13:53.998Z\",\"q\": \"good\",\"v\": 25.03737,\"multi\":10.33}]}, {\"n\": \"FREQ\",\"count\": 1,\"records\": [{\"i\": 2068,\"t\": \"2016-07-05T15:13:53.998Z\",\"q\": \"good\",\"v\": 50.00000}]}, {\"n\": \"VRMSA\",\"count\": 1,\"records\": [{\"i\": 2068,\"t\": \"2016-07-05T15:13:53.998Z\",\"q\": \"good\",\"v\": 220.03842}]}, {\"n\": \"PFC\",\"count\": 1,\"records\": [{\"i\": 2068,	\"t\": \"2016-07-05T15:13:53.998Z\",\"q\": \"good\",\"v\": 0.99896}]}]}";
    	String jsonString = "{ \"from\":{ \"deviceId\":\"SpoonyDotVisionDev\", \"unit\":\"ODMDataChunk\" }, \"t\":\"2016-07-05T15:13:54.013Z\", \"count\":29, \"elements\":[ { \"n\":\"TEMP\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":25.03737 } ] }, { \"n\":\"FREQ\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":50.00000 } ] }, { \"n\":\"VRMSA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":220.03842 } ] }, { \"n\":\"VRMSB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":219.70237 } ] }, { \"n\":\"VRMSC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":220.19691 } ] }, { \"n\":\"IRMSA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-9.85277 } ] }, { \"n\":\"IRMSB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":7.34283 } ] }, { \"n\":\"IRMSC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":2.99373 } ] }, { \"n\":\"WATTHRA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-1250.21447 } ] }, { \"n\":\"WATTHRB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":887.71948 } ] }, { \"n\":\"WATTHRC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":382.72506 } ] }, { \"n\":\"VARHRA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-54.76239 } ] }, { \"n\":\"VARHRB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":37.80604 } ] }, { \"n\":\"VARHRC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":16.43738 } ] }, { \"n\":\"VAHRA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-1251.86120 } ] }, { \"n\":\"VAHRB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":888.86926 } ] }, { \"n\":\"VAHRC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":383.22750 } ] }, { \"n\":\"WATTA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-2163.50097 } ] }, { \"n\":\"WATTB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":1609.89099 } ] }, { \"n\":\"WATTC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":658.52893 } ] }, { \"n\":\"VARA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-139.41661 } ] }, { \"n\":\"VARB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":103.86761 } ] }, { \"n\":\"VARC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":30.00405 } ] }, { \"n\":\"VAA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":-2167.98828 } ] }, { \"n\":\"VAB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":1613.23815 } ] }, { \"n\":\"VAC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":659.21209 } ] }, { \"n\":\"PFA\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":0.99793 } ] }, { \"n\":\"PFB\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":0.99792 } ] }, { \"n\":\"PFC\", \"count\":1, \"records\":[ { \"i\":2068, \"t\":\"2016-07-05T15:13:53.998Z\", \"q\":\"good\", \"v\":0.99896 } ] } ] }";
        JSONObject output;
        try {
            output = new JSONObject(jsonString);


            JSONArray docs = output.getJSONArray("elements");

            File file=new File("fromJSON.csv");
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }

}