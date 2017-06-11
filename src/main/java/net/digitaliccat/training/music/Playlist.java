package net.digitaliccat.training.music;

import java.util.Collection;
import java.util.LinkedList;

public class Playlist {

    private String name;

    private LinkedList<Song> trackList;

    public Playlist(String name) {
        this.name = name;
        this.trackList = new LinkedList<>();
    }

    public Playlist(String name, Collection<Song> col) {
        this(name);
        this.trackList.addAll(col);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Song s) {
        this.trackList.add(s);
    }

    public void addLast(Song s) {
        this.add(s);
    }

    public void addFirst(Song s) {
        this.trackList.addFirst(s);
    }

    public void move(int songIndex, int moveToIndex) {
        if (this.trackList.size() <= songIndex || songIndex < 0) {
            throw new IllegalArgumentException("songIndex not in range of the playlist");
        }

        if (this.trackList.size() <= moveToIndex || moveToIndex < 0) {
            throw new IllegalArgumentException("moveToIndex not in range of the playlist");
        }

        if (songIndex == moveToIndex)
            return;

        if (moveToIndex > songIndex) {
            moveToIndex--;
        }

        Song s = this.trackList.remove(songIndex);
        this.trackList.add(moveToIndex, s);
    }


}
