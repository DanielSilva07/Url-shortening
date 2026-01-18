package com.daniel.silva.url_shortening.mapper;

import com.daniel.silva.url_shortening.domain.Url;
import com.daniel.silva.url_shortening.dto.UrlRequest;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {


    public UrlResponse urlRequestToDomain(UrlRequest urlRequest){
        return UrlResponse.builder().shortURL(urlRequest.getUrl())
                .build();
    }

    public UrlResponse buildUrlResponse(Url url){
        return UrlResponse.builder()
                .id(url.getId())
                .longUrl(url.getLongURL())
                .shortURL(String.valueOf(url.getId()))
                .build();
    }
}
