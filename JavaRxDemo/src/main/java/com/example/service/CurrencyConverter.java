package com.example.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.domain.dto.CurrencyRatesDTO;
import com.example.util.RestUtil;

import rx.Observable;

@Service
public class CurrencyConverter implements CurrencyConverterService {
	@Value("${services.currency.uri}")
	private String CURRENCY_SERVICE_API;

	private static final String SYMBOLS = "symbols";

	private static final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public Observable<CurrencyRatesDTO> getCurrencyRates(Set<String> currencies) {
		return getCurrencyRatesObservable(currencies);
	}

	private Observable<CurrencyRatesDTO> getCurrencyRatesObservable(Set<String> currencies) {
		return Observable.<CurrencyRatesDTO> create(sub -> {
			CurrencyRatesDTO currencyRatesDTO = restTemplate.getForEntity(
					CURRENCY_SERVICE_API
							+ RestUtil.getQueryParamStringForMultiValuedAttribute(SYMBOLS, currencies),
					CurrencyRatesDTO.class).getBody();
			sub.onNext(currencyRatesDTO);
			sub.onCompleted();
			log.debug("Currency rates were retrieved successfully.");
		});
	}

}
