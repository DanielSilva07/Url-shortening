package com.daniel.silva.url_shortening.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UrlException extends RuntimeException{
    private final HttpStatus status;

    private UrlException(String message, HttpStatus status, Throwable cause){
        super(message, cause);
        this.status = status;
    }

    public static UrlException notFound(String url){
        return new UrlException(
                 "URL não encontrada: " + url,
        HttpStatus.NOT_FOUND,
        null
        );
    }


}
