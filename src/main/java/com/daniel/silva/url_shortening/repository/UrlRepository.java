package com.daniel.silva.url_shortening.repository;

import com.daniel.silva.url_shortening.domain.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, BigInteger> {
    Optional<Url> findByShortCode(String shortCode);
}