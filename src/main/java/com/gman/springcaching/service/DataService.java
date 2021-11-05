package com.gman.springcaching.service;

import com.gman.springcaching.config.RatesChangesProps;
import com.gman.springcaching.exception.RateNotFoundException;
import com.gman.springcaching.repository.CurrencyRatesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@Component
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(RatesChangesProps.class)
public class DataService {

    private final CurrencyRatesRepo dataRepo;

    public double getRate(String ccy) {
        return dataRepo.getByKey(ccy).orElseThrow(RateNotFoundException::new).getRate().doubleValue();
    }


}
