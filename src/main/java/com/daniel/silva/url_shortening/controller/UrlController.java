package com.daniel.silva.url_shortening.controller;

import com.daniel.silva.url_shortening.dto.UrlRequest;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import com.daniel.silva.url_shortening.mapper.UrlMapper;
import com.daniel.silva.url_shortening.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("api/v1/shortenings")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlMapper urlMapper;


    @PostMapping
    public ResponseEntity<UrlResponse> saveUrl(
            @RequestBody UrlRequest urlRequest
    ){
        return ResponseEntity.ok().body(urlService.saveUrl(urlRequest));
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> getUrl(
            @PathVariable String code)
    {
        UrlResponse urlResponse = urlService.getShortUrl(code);
        return ResponseEntity.status(302)
                .location(URI.create(urlResponse.getLongUrl())).build();
    }
}
