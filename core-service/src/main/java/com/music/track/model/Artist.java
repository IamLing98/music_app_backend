package com.music.track.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "external_urls")
    private Long externalUrls;

//    @Column(name = "followers")
//    private Long followers;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "artists_genres",
            joinColumns = {
                    @JoinColumn(
                            name = "artist_id",
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

    @Column(name = "href")
    private String href;

//    @Column(name="id")
//    private Long images;

    @Column(name = "name")
    private String name;

    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "type")
    private String type;

    @Column(name = "uri")
    private String uri;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "artists_albums",
            joinColumns = {
                    @JoinColumn(
                            name = "artist_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "album_id",
                            nullable = false,
                            updatable = false
                    )
            }

    )
    private Set<Album> albums;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "tracks_artists",
            joinColumns = {
                    @JoinColumn(
                            name = "artist_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "track_id",
                            nullable = false,
                            updatable = false
                    )
            }

    )
    private Set<Artist> tracks;
}
