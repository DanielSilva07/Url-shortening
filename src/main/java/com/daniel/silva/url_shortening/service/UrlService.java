package com.daniel.silva.url_shortening.service;

import com.daniel.silva.url_shortening.domain.Url;
import com.daniel.silva.url_shortening.dto.UrlRequest;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import com.daniel.silva.url_shortening.mapper.UrlMapper;
import com.daniel.silva.url_shortening.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private UrlMapper urlMapper;

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public Url save(UrlRequest urlRequest){
        UrlResponse urlResponse = urlMapper.urlRequestToDomain(urlRequest);
        String shortUrl = generateShortUrl((urlRequest.getUrl()));
        Url url = Url.builder()
                .longURL(urlRequest.getUrl())
                .shortURL(shortUrl)
                .build();
        return urlRepository.save(url);
    }

    public UrlResponse getShortUrl(String shortUrl){
      Url url = urlRepository.findByShortURL(shortUrl)
              .orElseThrow(() -> new RuntimeException("URL não encontrada"));;
        return urlMapper.buildUrlResponse(url);
    }

    public String generateShortUrl(String longUrl){
        long hash = longUrl.hashCode();
        hash = hash < 0 ? -hash : hash;
        return toBase62(hash);
    }

    public String toBase62(Long value){
        String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        do {
            sb.insert(0, base62.charAt((int)(value % 62)));
            value /= 62;
        } while (value > 0);

        return sb.toString();
    }
}
