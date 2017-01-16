package com.example.domain.dto;

/**
 * This model class represents shipping information associated with a given
 * order.
 *
 */
public class ShippingDetailsDTO {
	private String courierService;
	private double cost;
	private String currency;
	private String address;

	public String getCourierService() {
		return courierService;
	}

	public void setCourierService(String courierService) {
		this.courierService = courierService;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
