package com.github.shorturl.util;

import com.github.shorturl.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;


@Component
@RequiredArgsConstructor
public class ShortCodeGenerator {

    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CODE_LENGTH = 7;
    private final SecureRandom secureRandom = new SecureRandom();

    private final ShortUrlRepository shortUrlRepository;

    public String generateUnique() {
        String code;
        do {
            code = generate();
        } while (shortUrlRepository.existsByShortCode(code));
        return code;
    }

    private String generate() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = secureRandom.nextInt(CHARS.length());
            code.append(CHARS.charAt(index));
        }
        return code.toString();
    }
}