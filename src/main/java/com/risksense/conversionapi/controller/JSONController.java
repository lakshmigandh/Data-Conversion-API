package com.risksense.conversionapi.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risksense.conversionapi.converters.ConverterFactory;
import com.risksense.conversionapi.converters.XMLJSONConverterI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This Controller deals with all sorts of conversions from JSON format
 * @author lakshmigandh
 *
 */
@Controller
@RequestMapping(path = "/json/")
@CrossOrigin(origins = "*")
@Api(tags={"JSON Conversion Controller"})
public class JSONController {
	
	@Autowired
	private Environment env;

	
	@PostMapping("convert2xml")
	@ApiOperation(value="Convert JSON to XML",notes="This operation converts JSON to XML")
	public @ResponseBody String convert2XML(@RequestBody String json,final ServletRequest req,final ServletResponse res) {
		String xml = null;
		
		try {
			XMLJSONConverterI xmlConverter = ConverterFactory.createXMLJSONConverter();
			xml = xmlConverter.convertJSONtoXML(json);
		} catch (Exception e) {
			e.printStackTrace();
			xml = env.getProperty("JSON_PARSE_ERROR");
		}
		
		return xml;
	}
	
}