package com.risksense.conversionapi.converters;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.springframework.stereotype.Component;

/**
 * This implementation converts json to a custom typed-xml format
 * @author lakshmigandh
 *
 */
@Component("JSON2TypedXMLConverter")
public class JSON2TypedXMLConverter implements XMLJSONConverterI{
	
	private StringBuilder XML = new StringBuilder();
	
	private static final String OBJECT_START_TAG = "<object";
	private static final String OBJECT_END_TAG = "</object>";
	private static final String ARRAY_START_TAG = "<array";
	private static final String ARRAY_END_TAG = "</array>";
	private static final String STRING_START_TAG = "<string";
	private static final String STRING_END_TAG = "</string>";
	private static final String NUMBER_START_TAG = "<number";
	private static final String NUMBER_END_TAG = "</number>";
	private static final String BOOLEAN_START_TAG = "<boolean";
	private static final String BOOLEAN_END_TAG = "</boolean>";
	private static final String NULL_START_TAG = "<null";
	private static final String CLOSING_ANGLE = ">";
	
	@Override
	public void convertJSONtoXML(File json, File xml) throws IOException {
		throw new UnsupportedOperationException("Currently not supported yet !");
	}
	
	@Override
	public String convertJSONtoXML(String json) throws IOException {

		JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObj = reader.readObject();
        parseJSON(null,jsonObj);
		return XML.toString();
	}
	
	public void parseJSON(String key, JsonValue obj){
		
		switch (obj.getValueType()) {
		case OBJECT:
			parseJSON(key, (JsonObject) obj);
			break;
		case ARRAY:
			parseJSON(key, (JsonArray) obj);
			break;
		case NUMBER:
			parseJSON(key, (JsonNumber) obj);
			break;
		case FALSE:
			parseJSONBoolean(key,Boolean.FALSE);
			break;
		case TRUE:
			parseJSONBoolean(key,Boolean.TRUE);
			break;
		case NULL:
			parseJSONNull(key);
			break;
		default:
			parseJSON(key, (JsonString) obj);
    }

	}
	public void parseJSON(String key, JsonObject obj){
		XML.append(OBJECT_START_TAG);
		parseKey(key);
        Set<String> keys = obj.keySet();
        for (String k : keys) {
            JsonValue value = obj.get(k);
            parseJSON(k, value);
        }
        XML.append(OBJECT_END_TAG);
	}
	public void parseJSON(String key, JsonArray obj){
		XML.append(ARRAY_START_TAG);
		parseKey(key);
        List<JsonValue> vals = obj.getValuesAs(JsonValue.class);
        for(JsonValue val : vals)
        	parseJSON(null, val);
        XML.append(ARRAY_END_TAG);
	}
	public void parseJSON(String key, JsonString obj){
		XML.append(STRING_START_TAG);
		parseKey(key);
		XML.append(obj.getString());
        XML.append(STRING_END_TAG);
	}
	public void parseJSON(String key, JsonNumber obj){
		XML.append(NUMBER_START_TAG);
		parseKey(key);
		XML.append(obj);
        XML.append(NUMBER_END_TAG);
	}
	public void parseJSONNull(String key){
		XML.append(NULL_START_TAG);
		if(key !=null)
			XML.append(" name=\""+key+"\"/>");
	}
	public void parseJSONBoolean(String key, Boolean val){
		XML.append(BOOLEAN_START_TAG);
		parseKey(key);
		XML.append(val);
        XML.append(BOOLEAN_END_TAG);
	}
	
	private void parseKey(String key){
		if(key !=null)
			XML.append(" name=\""+key+"\">");
		else
			XML.append(CLOSING_ANGLE);
	}
	
	
	
}