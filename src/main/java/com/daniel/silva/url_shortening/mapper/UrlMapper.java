package com.daniel.silva.url_shortening.mapper;

import com.daniel.silva.url_shortening.domain.entity.Url;
import com.daniel.silva.url_shortening.dto.UrlRequest;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {


    public UrlResponse urlRequestToDomain(UrlRequest urlRequest){
        return UrlResponse.builder().shortCode(urlRequest.getUrl())
                .build();
    }

    public UrlResponse buildUrlResponse(Url url){
        return UrlResponse.builder()
                .id(url.getId())
//                .longUrl(url.getLongURL())
                .shortCode(String.valueOf(url.getId()))
                .build();
    }
}
