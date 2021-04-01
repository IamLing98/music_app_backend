package com.music.track.service.impl;

import com.music.track.model.Track;
import com.music.track.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    @Override
    public List<Track> getSeveralTracks(List<Long> ids, String market) {
        return null;
    }

    @Override
    public Track getATrack(Long id, String market) {
        return null;
    }

}
