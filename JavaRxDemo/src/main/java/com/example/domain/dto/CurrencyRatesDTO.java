package com.example.domain.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyRatesDTO {
	private final String base;
	private final String date;
	private final Map<String, Double> rates;

	@JsonCreator
	public CurrencyRatesDTO(@JsonProperty("base") String base, @JsonProperty("date") String date,
			@JsonProperty("rates") Map<String, Double> rates) {
		super();
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public String getDate() {
		return date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}
}
