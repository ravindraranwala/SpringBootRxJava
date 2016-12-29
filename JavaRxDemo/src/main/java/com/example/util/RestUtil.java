package com.example.util;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class RestUtil {
	public static final String SEPARATOR = "=";
	public static final String DELIMITER = ",";
	public static final String QUERY_PARAM_START_SYMBOL = "?";

	public static String getQueryParamStringForMultiValuedAttribute(final String paramName, Set<String> values) {
		StringJoiner queryParam = new StringJoiner(DELIMITER);
		values.stream().forEach(value -> queryParam.add(paramName + SEPARATOR + value));
		return queryParam.toString();
	}

	public static String getQueryParamStringForSingleValuedAttributes(final List<String> paramNames,
			final List<String> values) {
		StringJoiner queryParam = new StringJoiner(DELIMITER);
		IntStream.range(0, Math.min(paramNames.size(), values.size()))
				.mapToObj(i -> paramNames.get(i) + SEPARATOR + values.get(i)).forEach(val -> queryParam.add(val));
		return queryParam.toString();
	}
}
