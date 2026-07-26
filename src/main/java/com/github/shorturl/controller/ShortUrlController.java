package com.github.shorturl.controller;

import com.github.shorturl.dto.request.ShortUrlRequestDTO;
import com.github.shorturl.dto.response.ShortUrlResponseDTO;
import com.github.shorturl.dto.response.ShortUrlStatsResponseDTO;
import com.github.shorturl.entity.ShortUrl;
import com.github.shorturl.service.ShortUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("shorten")
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShortUrl> getShortUrls() {
        return shortUrlService.getShortUrls();
    }

    @GetMapping("/{code}")
    public ShortUrlResponseDTO getOriginalUrlByShortCode(@PathVariable String code) {
        return shortUrlService.getOriginalUrlByShortCode(code);
    }

    @GetMapping("/{code}/stats")
    public ShortUrlStatsResponseDTO getShortUrlStats(@PathVariable String code) {
        return shortUrlService.getShortCodeStats(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlResponseDTO createShortUrl(@RequestBody @Valid ShortUrlRequestDTO url) {
        return shortUrlService.createShortUrl(url);
    }

    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public ShortUrlResponseDTO updateShortUrl(@RequestBody @Valid ShortUrlRequestDTO url, @PathVariable String code) {
        return shortUrlService.updateShortUrl(url, code);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShortUrl(@PathVariable String code) {
        shortUrlService.deleteShortUrl(code);
    }

}
