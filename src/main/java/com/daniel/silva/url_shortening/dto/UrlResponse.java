package com.daniel.silva.url_shortening.dto;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponse {

    private BigInteger id;
    private String shortCode;
    private String longUrl;
}
