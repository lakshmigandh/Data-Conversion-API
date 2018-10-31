package com.risksense.conversionapi.converters;

import org.springframework.stereotype.Component;

/**
 * Factory class for creating instances of {@link XMLJSONConverterI}.
 */
@Component
public final class ConverterFactory {

    /**
     * You should implement this method having it return your version of
     * {@link com.risksense.converters.XMLJSONConverterI}.
     *
     * @return {@link com.risksense.converters.XMLJSONConverterI} implementation you created.
     */
    public static final XMLJSONConverterI createXMLJSONConverter() {
       return new JSON2TypedXMLConverter();
    }
}
