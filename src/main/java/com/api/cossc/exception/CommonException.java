package com.api.cossc.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@RequiredArgsConstructor
public class CommonException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;


}
