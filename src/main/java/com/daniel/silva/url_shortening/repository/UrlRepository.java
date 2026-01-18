package com.daniel.silva.url_shortening.repository;

import com.daniel.silva.url_shortening.domain.Url;
import com.daniel.silva.url_shortening.dto.UrlResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, BigInteger> {

    @Query("SELECT u FROM Url u WHERE u.shortURL = :shortUrl")
    Optional<Url> findByShortUrl(@Param("shortUrl") String shortUrl);

    Optional<Url> findByShortURL(String shortURL);
}