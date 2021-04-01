package com.music.track.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Table(name="album")
@Entity
public class Album {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="album_type")
    private String albumType;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "artists_albums",
            joinColumns = {
                    @JoinColumn(
                            name = "album_id",
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

    @Column(name="copyrights")
    private String copyrights;

    @Column(name="external_ids")
    private String externalIds;

    @Column(name="external_urls")
    private String externalUrls;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "albums_genres",
            joinColumns = {
                    @JoinColumn(
                            name = "album_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "genres_id",
                            nullable = false,
                            updatable = false
                    )
            }

    )
    private Set<Genres> genres;

    @Column(name="href")
    private String href;

//    @Column(name="images")
//    private String images;

    @Column(name="label")
    private String label;

    @Column(name="name")
    private String name;

    @Column(name="popularity")
    private Integer popularity;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @Column(name="release_date_precision")
    private String releaseDatePrecision;

    @Column(name="total_tracks")
    private Integer totalTracks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    @JsonIgnoreProperties({"album"})
    private Set<Track> tracks;

}
