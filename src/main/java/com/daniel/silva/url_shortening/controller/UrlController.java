package com.daniel.silva.url_shortening.controller;

import com.daniel.silva.url_shortening.domain.entity.Url;
import com.daniel.silva.url_shortening.dto.UrlRequest;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import com.daniel.silva.url_shortening.mapper.UrlMapper;
import com.daniel.silva.url_shortening.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("api/v1")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlMapper urlMapper;


    @PostMapping
    public ResponseEntity<Url> saveUrl(
            @RequestBody UrlRequest urlRequest
    ){
        return ResponseEntity.ok().body(urlService.save(urlRequest));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getUrl(
            @PathVariable String shortUrl)
    {
        UrlResponse urlResponse = urlService.getShortUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlResponse.getLongUrl())).build();
    }
}
