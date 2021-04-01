package com.music.track.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {

    private Long id;

    private Integer discNumber;

    private Integer durationMs;

    private Boolean explicit;

    private String href;

    private Boolean isLocal;

    private Boolean isPlayable;

    private String name;

    private Integer popularity;

    private String previewUrl;

    private Integer trackNumber;

    private String type;

    private String uri;


}
