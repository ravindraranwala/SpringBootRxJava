package com.example.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.dto.CurrencyRatesDTO;

import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class CurrencyConverter implements CurrencyConverterService {
	@Value("${services.currency.uri}")
	private String CURRENCY_SERVICE_API;

	private static final String SYMBOLS = "symbols";

	private static final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

	private final RestTemplate restTemplate;

	public CurrencyConverter(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public Observable<CurrencyRatesDTO> getCurrencyRates(Collection<String> currencies) {
		log.info("Running in thread: " + Thread.currentThread().getName());
		return getCurrencyRatesFromEp(new HashSet<>(currencies)).subscribeOn(Schedulers.io());
	}

	private Observable<CurrencyRatesDTO> getCurrencyRatesFromEp(Set<String> currencies) {
		return Observable.<CurrencyRatesDTO>create(sub -> {
			CurrencyRatesDTO currencyRatesDTO = restTemplate
					.getForEntity(UriComponentsBuilder.fromUriString(CURRENCY_SERVICE_API)
							.queryParam(SYMBOLS, currencies.toArray()).toUriString(), CurrencyRatesDTO.class)
					.getBody();
			sub.onNext(currencyRatesDTO);
			sub.onCompleted();
		}).doOnNext(c -> log.info("Currency rates were retrieved successfully." + Thread.currentThread().getName()))
				.doOnError(e -> log.error("An ERROR occurred while retrieving the currency rates.", e));
	}

}
