package com.music.track.service;

import com.music.track.model.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TrackService {

    public List<Track> getSeveralTracks(List<Long> ids);

    public Track getATrack(Long id);

}
