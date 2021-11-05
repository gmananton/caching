package com.gman.springcaching.repository;

import com.gman.springcaching.config.RatesChangesProps;
import com.gman.springcaching.entity.CurrencyPairs;
import com.gman.springcaching.entity.CurrencyRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@Component
@EnableConfigurationProperties(RatesChangesProps.class)
@Slf4j
@RequiredArgsConstructor
public class CurrencyRatesRepo implements DataRepo<CurrencyRate, String> {

    private final RatesChangesProps ratesChangesProps;


    Map<String, CurrencyRate> storage = new ConcurrentHashMap<>();

    @PostConstruct
    public void initStorage() {
        save(new CurrencyRate(CurrencyPairs.USD_EUR, 0.86));
        save(new CurrencyRate(CurrencyPairs.USD_RUB, 71.73));
        save(new CurrencyRate(CurrencyPairs.USD_YEN, 114.00));


        var executor = Executors.newScheduledThreadPool(storage.size());

        var period = ratesChangesProps.getPeriod().getSeconds();
        var amount = BigDecimal.valueOf(ratesChangesProps.getAmount()).setScale(2, RoundingMode.HALF_UP);
        storage.keySet().forEach(ccy -> {
            executor.scheduleAtFixedRate(() -> {
                var rnd = new Random().nextInt(2);
                if (rnd == 0) {
                    update(new CurrencyRate(ccy, getByKey(ccy).get().getRate().subtract(amount)));
                } else {
                    update(new CurrencyRate(ccy, getByKey(ccy).get().getRate().add(amount)));
                }
            }, period, period, TimeUnit.SECONDS);
        });
    }

    @Override
    public Optional<CurrencyRate> getByKey(String key) {
        return Optional.ofNullable(storage.get(key.toUpperCase()));
    }

    @Override
    public void save(CurrencyRate entity) {
        storage.put(entity.getCcyPair(), entity);
    }

    @Override
    public void update(CurrencyRate entity) {
        storage.put(entity.getCcyPair(), entity);
    }
}
