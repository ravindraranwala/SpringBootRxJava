package com.example.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model class represents shipping information associated with a given
 * order.
 *
 */
public class ShippingDetailsDTO {
	private final String courierService;
	private final double cost;
	private final String currency;
	private final String address;

	@JsonCreator
	public ShippingDetailsDTO(@JsonProperty("courierService") String courierService, @JsonProperty("cost") double cost,
			@JsonProperty("currency") String currency, @JsonProperty("address") String address) {
		super();
		this.courierService = courierService;
		this.cost = cost;
		this.currency = currency;
		this.address = address;
	}

	public String getCourierService() {
		return courierService;
	}

	public double getCost() {
		return cost;
	}

	public String getCurrency() {
		return currency;
	}

	public String getAddress() {
		return address;
	}
}
