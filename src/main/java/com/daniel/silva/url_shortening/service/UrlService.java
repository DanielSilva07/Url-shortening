package com.daniel.silva.url_shortening.service;

import com.daniel.silva.url_shortening.domain.entity.Url;
import com.daniel.silva.url_shortening.dto.UrlRequest;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import com.daniel.silva.url_shortening.exceptions.UrlException;
import com.daniel.silva.url_shortening.mapper.UrlMapper;
import com.daniel.silva.url_shortening.repository.UrlRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final long MULTIPLIER = 7L;
    private static final long INCREMENT = 13L;
    private static final long MOD = 1_000_000_000L; // ajuste conforme esperado
    private static final int MIN_SHORTCODE_LENGTH = 4;

    @Autowired
    private UrlMapper urlMapper;

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }


    @Transactional
    public UrlResponse saveUrl(UrlRequest urlRequest) {
        Url url = new Url();
        url.setLongURL(urlRequest.getUrl());
        urlRepository.saveAndFlush(url);

        String shortCode = encodeId(url.getId().longValue());
        url.setShortCode(shortCode);

        return UrlResponse.builder()
                .id(url.getId())
                .shortCode(shortCode)
                .longUrl(url.getLongURL())
                .build();
    }

    public UrlResponse getShortUrl(String shortCode){
      Url url = urlRepository.findByShortCode(shortCode)
              .orElseThrow(() -> UrlException.notFound(shortCode));
        return urlMapper.buildUrlResponse(url);
    }


    private String encodeId(long id) {
        long scrambled = (id * MULTIPLIER + INCREMENT) % MOD;
        return toBase62(scrambled);
    }

    public String toBase62(long value) {
        final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (value < 0){
            throw new IllegalArgumentException("value deve ser positivo");
        }

        StringBuilder sb = new StringBuilder();
        if (value == 0){
            sb.append("0");
        }
        while (value > 0) {
            sb.append(BASE62.charAt((int) (value % 62)));
            value /= 62;
        }
        String base62Str = sb.reverse().toString();

        // Adiciona padding mínimo
        int padding = Math.max(0, MIN_SHORTCODE_LENGTH - base62Str.length());
        return "0".repeat(padding) + base62Str;
    }
}
