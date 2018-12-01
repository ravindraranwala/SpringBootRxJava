package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.domain.dto.OrderDetailsDTO;
import com.example.domain.dto.ProductDetailsDTO;
import com.example.domain.dto.ShippingDetailsDTO;

import rx.Observable;

@Service
public class OrderProcessor implements OrderService {
	@Value("${services.order.uri}")
	private String ORDER_API;

	private String PRODUCT_URL = "/products/northfacehoodie";

	private String SHIPPING_URL = "/shipping/northfacehoodie";

	private static final Logger log = LoggerFactory.getLogger(OrderProcessor.class);

	private final RestTemplate restTemplate;

	public OrderProcessor(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public Observable<OrderDetailsDTO> getOrderDetails() {
		log.debug("Retrieving the Order Details.");
		return Observable.zip(getProductDetails(), getShippingInformation(),
				(productInfo, shippingInfo) -> new OrderDetailsDTO(productInfo, shippingInfo));
	}

	private Observable<ProductDetailsDTO> getProductDetails() {
		return Observable.<ProductDetailsDTO>create(sub -> {
			ProductDetailsDTO productDetailsDTO = restTemplate
					.getForEntity(ORDER_API + PRODUCT_URL, ProductDetailsDTO.class).getBody();
			sub.onNext(productDetailsDTO);
			sub.onCompleted();
		}).doOnNext(p -> log.debug("Product details were received successfully."))
				.doOnError(e -> log.error("An ERROR occurred while fetching the Product details.", e));
	}

	private Observable<ShippingDetailsDTO> getShippingInformation() {
		return Observable.<ShippingDetailsDTO>create(sub -> {
			ShippingDetailsDTO shippingDetailsDTO = restTemplate
					.getForEntity(ORDER_API + SHIPPING_URL, ShippingDetailsDTO.class).getBody();
			sub.onNext(shippingDetailsDTO);
			sub.onCompleted();
		}).doOnNext(s -> log.debug("Shipping information was received successfully."))
				.doOnError(e -> log.error("An ERROR occurred while fetching Shipping Information", e));
	}
}
