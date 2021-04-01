package com.music.track.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * The artist controller.
 *
 * @author : linkdoan
 */

@RestController
public class ArtistController {

    @GetMapping(value = "/v1/artists/{id}/albums")
    public ResponseEntity<?> getAnArtistAlbumsVersion1(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/artists/{id}/related-artists")
    public ResponseEntity<?> getAnArtistRelatedArtistsVersion1(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/artists/{id}/top-tracks")
    public ResponseEntity<?> getAnArtistTopTracksVersion1(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/artists/{id}")
    public ResponseEntity<?> getAnArtistVersion1(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/artists")
    public ResponseEntity<?> getSeveralArtistsVersion1() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
