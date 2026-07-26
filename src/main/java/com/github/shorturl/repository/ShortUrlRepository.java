package com.github.shorturl.repository;

import com.github.shorturl.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// TODO: Short url should be case sensitive
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    boolean existsByShortCode(String shortCode);

    Optional<ShortUrl> findByShortCode(String shortCode);
}
