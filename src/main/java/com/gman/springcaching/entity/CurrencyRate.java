package com.gman.springcaching.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {

    private String ccyPair;
    private BigDecimal rate;

    public CurrencyRate(String ccyPair, double rate) {
        this.ccyPair = ccyPair;
        this.rate = BigDecimal.valueOf(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
