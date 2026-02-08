package com.daniel.silva.url_shortening.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UrlException.class)
    public ResponseEntity<ApiError> handlerUrlException(UrlException exception, HttpServletRequest request){

              HttpStatus status = exception.getStatus();
              ApiError body = new ApiError(
                      LocalDateTime.now(),
                      status.value(),
                      status.getReasonPhrase(),
                      exception.getMessage(),
                      request.getRequestURI()
              );
        return ResponseEntity.status(status).body(body);

    }



}
