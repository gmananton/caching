package com.gman.springcaching.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anton Mikhaylov on 04.11.2021.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RateNotFoundException extends RuntimeException {

}
