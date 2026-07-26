package com.github.shorturl.service;

import com.github.shorturl.dto.request.ShortUrlRequestDTO;
import com.github.shorturl.dto.response.ShortUrlResponseDTO;
import com.github.shorturl.dto.response.ShortUrlStatsResponseDTO;
import com.github.shorturl.entity.ShortUrl;
import com.github.shorturl.exception.ShortCodeNotFoundException;
import com.github.shorturl.mapper.ShortUrlMapper;
import com.github.shorturl.repository.ShortUrlRepository;
import com.github.shorturl.util.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlMapper shortUrlMapper;
    private final ShortCodeGenerator shortCodeGenerator;

    public List<ShortUrl> getShortUrls() {
        return shortUrlRepository.findAll();
    }

    public ShortUrlResponseDTO getOriginalUrlByShortCode(String shortCode) {
        ShortUrl entity = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException("Short URL not found: " + shortCode));

        return shortUrlMapper.toResponseDTO(entity);
    }

    public ShortUrlStatsResponseDTO getShortCodeStats(String shortCode) {
        ShortUrl entity = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException("Short URL not found: " + shortCode));

        return shortUrlMapper.toStatsResponseDTO(entity);
    }

    public ShortUrlResponseDTO createShortUrl(ShortUrlRequestDTO shortUrlRequestDTO) {
        String shortCode = shortCodeGenerator.generateUnique();

        ShortUrl shortUrlEntity = shortUrlMapper.toEntity(shortUrlRequestDTO, shortCode);

        shortUrlRepository.save(shortUrlEntity);

        return shortUrlMapper.toResponseDTO(shortUrlEntity);
    }

    public ShortUrlResponseDTO updateShortUrl(ShortUrlRequestDTO shortUrlRequestDTO, String shortCode) {
        ShortUrl entity = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException("Short URL not found: " + shortCode));

        entity.setUrl(shortUrlRequestDTO.getUrl());
        shortUrlRepository.save(entity);

        return shortUrlMapper.toResponseDTO(entity);
    }

    public void deleteShortUrl(String shortCode) {
        ShortUrl entity = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException("Short URL not found: " + shortCode));

        shortUrlRepository.delete(entity);
    }

}
