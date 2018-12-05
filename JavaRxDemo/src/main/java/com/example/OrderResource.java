package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.OrderService;

import rx.Observable;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/api/order")
public class OrderResource {
	private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

	private final OrderService orderService;

	public OrderResource(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Observable<?> getOrder() {
		log.debug("Retrieving Order Details.");
		return orderService.getOrderDetails().subscribeOn(Schedulers.io());
	}
}
