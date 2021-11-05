package com.gman.springcaching.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.time.Duration;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@ConfigurationProperties(prefix = "rates-fluctuation")
@Component("ratesChangesProps")
@Validated
@Data
@Slf4j
public class RatesChangesProps {

    @DurationMin(seconds = 1)
    @DurationMax(seconds = 10)
    private Duration period;

    @DecimalMin(value = "0.01")
    @DecimalMax(value = "1")
    private double amount;

    @PostConstruct
    public void init() {
        log.info("initialized with values: {}, {}", period, amount);
    }
}
