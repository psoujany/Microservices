package com.example.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getConversionFactor(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangevalue = repository.findByFromAndTo(from, to);
		exchangevalue.setPort(Integer.parseInt((environment.getProperty("local.server.port"))));
		return exchangevalue;
		
	}
	
	@GetMapping("/currency-exchange-add/from/{from}/to/{to}")
	public ExchangeValue addConversionFactor(@PathVariable String from, @PathVariable String to
											) {
		return new ExchangeValue(1004L,from,to,BigDecimal.valueOf(35));
	}
	
}
