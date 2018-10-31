package com.risksense.conversionapi;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.risksense.conversionapi.controller.JSONController;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONController.class)
public class JSONControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static final String EXPECTED_JSON_PARSE_ERROR="JSON Parse Error. Please try again with valid JSON";
	
	@Test
	public void testValidJSON() throws Exception {
		String inputJSON = Resources.toString(Resources.getResource("example.json"), Charsets.UTF_8);
		String expectedXML = Resources.toString(Resources.getResource("example.xml"), Charsets.UTF_8);
		System.out.println("Input JSON is : \n"+inputJSON);
		System.out.println("Expected XML is : \n"+expectedXML);
		String xmlResponse = mockMvc.perform(post("/json/convert2xml").content(inputJSON)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		System.out.println("Output XML is : \n"+xmlResponse);
		assertEquals(expectedXML, xmlResponse);
	}
	
	
	@Test
	public void testInValidJSON() throws Exception {
		String inputJSON = Resources.toString(Resources.getResource("example2.json"), Charsets.UTF_8);
		System.out.println("Input JSON is : \n"+inputJSON);
		String response = mockMvc.perform(post("/json/convert2xml").content(inputJSON)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		System.out.println("Output Response is : \n"+response);
		assertEquals(EXPECTED_JSON_PARSE_ERROR, response);
	}
	
	
}