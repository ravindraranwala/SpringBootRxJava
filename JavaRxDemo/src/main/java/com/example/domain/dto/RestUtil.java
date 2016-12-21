package com.example.domain.dto;

import java.util.Set;
import java.util.StringJoiner;

public class RestUtil {
	public static final String SEPARATOR = "=";
	public static final String DELIMITER = ",";
	public static final String QUERY_PARAM_START_SYMBOL = "?";

	public static String getQueryParamStringForMultiValuedAttribute(String paramName, Set<String> values) {
		StringJoiner joiner = new StringJoiner(",");
		for (String value : values) {
			joiner.add(value);
		}
		return QUERY_PARAM_START_SYMBOL + paramName + SEPARATOR + joiner.toString();
	}
}
