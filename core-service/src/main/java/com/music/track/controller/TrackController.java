package com.music.track.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The track controller.
 *
 * @author : linkdoan
 */

@RestController
public class TrackController {

    @GetMapping(value = "/v1/audio-analysis/{id}")
    public ResponseEntity<?> getAudioAnalysisForATrack(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/audio-features")
    public ResponseEntity<?> getAudioFeaturesForSeveralTracks(@RequestParam("ids") List<Long> ids) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/audio-features/{id}")
    public ResponseEntity<?> getAudioFeaturesForATrack(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/tracks")
    public ResponseEntity<?> getSeveralTracks(@RequestParam("ids") List<Long> ids, @RequestParam("market") String market) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/v1/tracks/{id}")
    public ResponseEntity<?> getATrack(@PathVariable("id") Long id,@RequestParam("market") String market) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
