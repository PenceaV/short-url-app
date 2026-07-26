package com.github.shorturl.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlRequestDTO {

    @NotBlank(message = "URL is required")
    private String url;
}
