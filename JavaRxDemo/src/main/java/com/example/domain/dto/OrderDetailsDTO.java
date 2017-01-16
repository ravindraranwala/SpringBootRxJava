package com.example.domain.dto;

/**
 * This domain class gives an abstract view of the Order to the end user
 * including products associated with it, their prices and shipping details.
 *
 */
public class OrderDetailsDTO {
	private String itemName;
	private double totalPrice;
	private double unitPrice;
	private String currency;
	private String soldBy;
	private String courierService;
	private String shippingAddress;
	private double shippingCost;

	public OrderDetailsDTO() {
		super();
	}

	public OrderDetailsDTO(String itemName, double totalPrice, double unitPrice, String currency, String soldBy,
			String courierService, String shippingAddress, double shippingCost) {
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getCourierService() {
		return courierService;
	}

	public void setCourierService(String courierService) {
		this.courierService = courierService;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

}
