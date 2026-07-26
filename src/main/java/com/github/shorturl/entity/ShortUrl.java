package com.github.shorturl.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SHORT_URL")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "SHORT_CODE", nullable = false, unique = true, length = 7)
    private String shortCode;

    @Column(name = "ACCESS_COUNT", nullable = false)
    @Builder.Default
    private Long accessCount = 0L;

    @Column(name = "CREATED_AT", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
