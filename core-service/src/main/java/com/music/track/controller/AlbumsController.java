package com.music.track.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * The album controller.
 *
 * @author : linkdoan
 */

@RestController
public class AlbumsController {

    @GetMapping(value = "/v1/albums/{id}")
    public ResponseEntity<?> getAnAlbumVersion1(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/albums/{id}/tracks")
    public ResponseEntity<?> GetAnAlbumTracksVersion1(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/albums")
    public ResponseEntity<?> getSeveralAlbumsVersion1() {
        String hello = "hello";
        return new ResponseEntity<>(hello,HttpStatus.OK);
    }

}
