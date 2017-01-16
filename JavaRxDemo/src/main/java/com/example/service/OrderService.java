package com.example.service;

import com.example.domain.dto.OrderDetailsDTO;

import rx.Observable;

/**
 * Defines the contract to deal with the orders in our eCommerce system.
 */
public interface OrderService {
	/**
	 * Fetches the order details including the items, their prices and shipping
	 * information associated with the order.
	 * 
	 * @return Details of the Order being considered.
	 */
	Observable<OrderDetailsDTO> getOrderDetails();
}
