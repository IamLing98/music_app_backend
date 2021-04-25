package com.music.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "files")
public class FileModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "version")
    private String version;

    @Column(name = "signature")
    private String signature;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "format")
    private String format;

    @Column(name = "resourceType")
    private String resourceType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "bytes")
    private Long bytes;

    @Column(name = "type")
    private String type;

    @Column(name = "url", columnDefinition = "TEXT")
    private String url;

    @Column(name = "secure_url", columnDefinition = "TEXT")
    private String secureUrl;

    @Column(name = "etag", columnDefinition = "TEXT")
    private String etag;

}
