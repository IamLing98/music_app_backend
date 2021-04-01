package com.music.track.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="genres")
@Entity
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="genres_name")
    private String genresName;



}
