package com.github.shorturl.mapper;

import com.github.shorturl.dto.request.ShortUrlRequestDTO;
import com.github.shorturl.dto.response.ShortUrlResponseDTO;
import com.github.shorturl.dto.response.ShortUrlStatsResponseDTO;
import com.github.shorturl.entity.ShortUrl;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlMapper {
    public ShortUrl toEntity(ShortUrlRequestDTO shortUrlRequestDTO, String shortCode) {
        return ShortUrl.builder()
                .url(shortUrlRequestDTO.getUrl())
                .shortCode(shortCode)
                .build();
    }

    public ShortUrlResponseDTO toResponseDTO(ShortUrl entity) {
        return ShortUrlResponseDTO.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .shortCode(entity.getShortCode())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public ShortUrlStatsResponseDTO toStatsResponseDTO(ShortUrl entity) {
        return ShortUrlStatsResponseDTO.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .shortCode(entity.getShortCode())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .accessCount(entity.getAccessCount())
                .build();
    }
}
