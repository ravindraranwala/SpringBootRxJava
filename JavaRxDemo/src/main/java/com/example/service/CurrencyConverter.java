package com.example.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.dto.CurrencyRatesDTO;

import rx.Observable;

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
	public Observable<CurrencyRatesDTO> getCurrencyRates(Set<String> currencies) {
		return getCurrencyRatesObservable(currencies);
	}

	private Observable<CurrencyRatesDTO> getCurrencyRatesObservable(Set<String> currencies) {
		return Observable.<CurrencyRatesDTO>create(sub -> {
			CurrencyRatesDTO currencyRatesDTO = restTemplate
					.getForEntity(UriComponentsBuilder.fromUriString(CURRENCY_SERVICE_API)
							.queryParam(SYMBOLS, currencies.toArray()).toUriString(), CurrencyRatesDTO.class)
					.getBody();
			sub.onNext(currencyRatesDTO);
			sub.onCompleted();
		}).doOnNext(c -> log.debug("Currency rates were retrieved successfully."))
				.doOnError(e -> log.error("An ERROR occurred while retrieving the currency rates.", e));
	}

}
