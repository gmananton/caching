package com.gman.springcaching.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@Component
@Slf4j
public class MutableDataRepo {

    Map<Integer, Integer> data = new ConcurrentHashMap<>();

    public int incrementAndGet(int key) {
        var value = data.getOrDefault(key, -1);
        data.put(key, value * 10);
        log.info("Data for key '{}' changed: '{}'", key, data.get(key));
        return value;
    }

    @PostConstruct
    public void init() {
        data.put(1, 1);
        data.put(2, 2);
        data.put(3, 3);
    }


}
