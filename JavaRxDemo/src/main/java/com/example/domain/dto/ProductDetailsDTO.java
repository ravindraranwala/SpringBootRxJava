package com.example.domain.dto;

/**
 * This domain class represents a Product entity in our eCommerce system. It
 * encapsulates all the basic attributes relevant to a particular Product. An
 * order can have one or more Products associated with it.
 *
 */
public class ProductDetailsDTO {
	private String name;
	private double unitPrice;
	private String currency;
	private String soldBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(String soldBy) {
		this.soldBy = soldBy;
	}

}
