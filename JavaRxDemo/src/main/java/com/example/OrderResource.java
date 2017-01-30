package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.dto.OrderDetailsDTO;
import com.example.service.OrderService;

import rx.Observable;

@Controller
@RequestMapping("/api/order")
public class OrderResource {
	@Autowired
	private OrderService orderService;
	private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Observable<OrderDetailsDTO> getOrder() {
		log.debug("Retrieving Order Details.");
		return orderService.getOrderDetails();
	}
}
