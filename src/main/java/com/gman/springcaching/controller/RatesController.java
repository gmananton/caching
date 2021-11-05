package com.gman.springcaching.controller;

import com.gman.springcaching.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@RestController
@RequestMapping("rates")
@RequiredArgsConstructor
public class RatesController {

    private final DataService dataService;

    @GetMapping("{ccy}")
    public Double getDataFresh(@PathVariable String ccy) {
        return dataService.getRate(ccy);
    }

}
