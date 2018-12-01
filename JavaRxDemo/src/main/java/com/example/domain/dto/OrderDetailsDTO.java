package com.example.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This domain class gives an abstract view of the Order to the end user
 * including products associated with it, their prices and shipping details.
 *
 */
public class OrderDetailsDTO {
	private final String itemName;
	private final double totalPrice;
	private final double unitPrice;
	private final String currency;
	private final String soldBy;
	private final String courierService;
	private final String shippingAddress;
	private final double shippingCost;

	@JsonCreator
	public OrderDetailsDTO(@JsonProperty("itemName") String itemName, @JsonProperty("totalPrice") double totalPrice,
			@JsonProperty("unitPrice") double unitPrice, @JsonProperty("currency") String currency,
			@JsonProperty("soldBy") String soldBy, @JsonProperty("courierService") String courierService,
			@JsonProperty("shippingAddress") String shippingAddress,
			@JsonProperty("shippingCost") double shippingCost) {
		super();
		this.itemName = itemName;
		this.totalPrice = totalPrice;
		this.unitPrice = unitPrice;
		this.currency = currency;
		this.soldBy = soldBy;
		this.courierService = courierService;
		this.shippingAddress = shippingAddress;
		this.shippingCost = shippingCost;
	}

	public OrderDetailsDTO(ProductDetailsDTO productInfo, ShippingDetailsDTO shippingInfo) {
		this.itemName = productInfo.getName();
		this.totalPrice = productInfo.getUnitPrice() + shippingInfo.getCost();
		this.unitPrice = productInfo.getUnitPrice();
		this.currency = productInfo.getCurrency();
		this.soldBy = productInfo.getSoldBy();
		this.courierService = shippingInfo.getCourierService();
		this.shippingAddress = shippingInfo.getAddress();
		this.shippingCost = shippingInfo.getCost();
	}

	public String getItemName() {
		return itemName;
	}

	public double getTotalPrice() {
		return totalPrice;
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

	public String getCourierService() {
		return courierService;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public double getShippingCost() {
		return shippingCost;
	}
}
