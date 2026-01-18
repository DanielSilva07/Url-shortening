package com.daniel.silva.url_shortening.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {

    @NotBlank
    @JsonProperty("url")
    private String url;
}
