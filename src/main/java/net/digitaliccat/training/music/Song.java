package net.digitaliccat.training.music;


public class Song {

    private String name;

    private String artist;

    private double length;

    public Song(String name, String artist, double length) {
        this.name = name;
        this.artist = artist;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
