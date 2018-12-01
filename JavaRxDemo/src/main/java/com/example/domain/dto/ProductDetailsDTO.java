package com.example.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This domain class represents a Product entity in our eCommerce system. It
 * encapsulates all the basic attributes relevant to a particular Product. An
 * order can have one or more Products associated with it.
 *
 */
public class ProductDetailsDTO {
	private final String name;
	private final double unitPrice;
	private final String currency;
	private final String soldBy;

	@JsonCreator
	public ProductDetailsDTO(@JsonProperty("name") String name, @JsonProperty("unitPrice") double unitPrice,
			@JsonProperty("currency") String currency, @JsonProperty("soldBy") String soldBy) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
		this.currency = currency;
		this.soldBy = soldBy;
	}

	public String getName() {
		return name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public String getSoldBy() {
		return soldBy;
	}

}
