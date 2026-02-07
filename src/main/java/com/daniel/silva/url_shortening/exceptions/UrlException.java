package com.daniel.silva.url_shortening.exceptions;

public class UrlException extends RuntimeException{

    public UrlException(String message){
        super(message);
    }

    public UrlException(String message, Throwable cause){
        super(message, cause);
    }
}
