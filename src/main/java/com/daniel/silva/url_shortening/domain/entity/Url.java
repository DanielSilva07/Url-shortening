package com.daniel.silva.url_shortening.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "shortUrl", nullable = false)
    private String shortCode;

    @Column(name = "longUrl", nullable = false, columnDefinition = "TEXT")
    private String longURL;
}
