package com.music.track.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="disc_number")
    private Integer discNumber;

    @Column(name="duration_ms")
    private Integer durationMs;

    @Column(name="explicit")
    private Boolean explicit;

    @Column(name="href")
    private String href;

    @Column(name="is_local")
    private Boolean isLocal;

    @Column(name="is_playable")
    private Boolean isPlayable;

    @Column(name="name")
    private String name;

    @Column(name="popularity")
    private Integer popularity;

    @Column(name="preview_url")
    private String previewUrl;

    @Column(name="track_number")
    private Integer trackNumber;

    @Column(name="type")
    private String type;

    @Column(name="uri")
    private String uri;

    @Column(name="external_ids")
    private String externalIds;

    @Column(name="external_urls")
    private String externalUrls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)

    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "tracks"})
    private Album album;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "tracks_artists",
            joinColumns = {
                    @JoinColumn(
                            name = "track_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "artist_id",
                            nullable = false,
                            updatable = false
                    )
            }

    )
    private Set<Artist> artists;

}
