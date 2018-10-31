package com.risksense.conversionapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/conversion-api.properties")
public class PropertiesLoader{}