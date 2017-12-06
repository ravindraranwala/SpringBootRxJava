package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.dto.OrderDetailsDTO;
import com.example.service.OrderService;

import rx.Observable;

@RestController
@RequestMapping("/api/order")
public class OrderResource {
	private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

	private final OrderService orderService;

	public OrderResource(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Observable<OrderDetailsDTO> getOrder() {
		log.debug("Retrieving Order Details.");
		return orderService.getOrderDetails();
	}
}
