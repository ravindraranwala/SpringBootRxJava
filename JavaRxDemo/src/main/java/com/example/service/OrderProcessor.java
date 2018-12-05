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
import rx.schedulers.Schedulers;

@Service
public class OrderProcessor implements OrderService {
	@Value("${services.product.uri}")
	private String PRODUCT_API;

	@Value("${services.shipping.uri}")
	private String SHIPPING_API;

	private static final Logger log = LoggerFactory.getLogger(OrderProcessor.class);

	private final RestTemplate restTemplate;

	public OrderProcessor(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public Observable<OrderDetailsDTO> getOrderDetails() {
		log.debug("Retrieving the Order Details.");
		return Observable.zip(getProductDetails().subscribeOn(Schedulers.io()), getShippingInformation(),
				OrderDetailsDTO::new);
	}

	private Observable<ProductDetailsDTO> getProductDetails() {
		return Observable.<ProductDetailsDTO>create(sub -> {
			ProductDetailsDTO productDetailsDTO = restTemplate.getForEntity(PRODUCT_API, ProductDetailsDTO.class)
					.getBody();
			sub.onNext(productDetailsDTO);
			sub.onCompleted();
		}).doOnNext(p -> log.info("Product details were received successfully."))
				.doOnError(e -> log.error("An ERROR occurred while fetching the Product details.", e));
	}

	private Observable<ShippingDetailsDTO> getShippingInformation() {
		return Observable.<ShippingDetailsDTO>create(sub -> {
			ShippingDetailsDTO shippingDetailsDTO = restTemplate.getForEntity(SHIPPING_API, ShippingDetailsDTO.class)
					.getBody();
			sub.onNext(shippingDetailsDTO);
			sub.onCompleted();
		}).doOnNext(s -> log.info("Shipping information was received successfully."))
				.doOnError(e -> log.error("An ERROR occurred while fetching Shipping Information", e));
	}
}
