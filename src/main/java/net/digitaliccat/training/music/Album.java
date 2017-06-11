package net.digitaliccat.training.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Album {

    private List<Song> trackList;

    private String albumArtist;

    private String name;

    public Album(String name, List<Song> songs, String albumArtist) {
        this.name = name;
        this.trackList = new ArrayList<>();
        this.trackList.addAll(songs);
        this.albumArtist = albumArtist;
    }

    public Song getSong(int track) {
        if (track > trackList.size()) {
            throw new IllegalArgumentException("Track does not exist");
        }

        return trackList.get(track);
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public List<Song> getTrackList() {
        return Collections.unmodifiableList(trackList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTrack() {
        return this.trackList.size();
    }

    public String toString() {
        return String.format("Album: %s, Artist: %s, Number of Track: %d",
                this.name, this.albumArtist, this.trackList.size());
    }
}