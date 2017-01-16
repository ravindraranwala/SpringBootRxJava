package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.http.HttpStatus;

import com.example.domain.dto.OrderDetailsDTO;
import com.example.service.OrderService;

@Controller
@RequestMapping("/api/order")
public class OrderResource {
	@Autowired
	private OrderService orderService;
	private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public DeferredResult<ResponseEntity<OrderDetailsDTO>> getOrder() {
		log.debug("Retrieving Order Details.");
		DeferredResult<ResponseEntity<OrderDetailsDTO>> deferredResult = new DeferredResult<ResponseEntity<OrderDetailsDTO>>();
		orderService.getOrderDetails().subscribe(
				sub -> deferredResult.setResult(new ResponseEntity<OrderDetailsDTO>(sub, HttpStatus.OK)),
				e -> deferredResult.setErrorResult(e));
		return deferredResult;
	}
}
