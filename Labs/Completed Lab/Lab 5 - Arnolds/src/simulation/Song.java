package simulation;

public class Song implements Comparable<Song> {
    private final String artist;
    private final String title;

    public Song(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public int compareTo(Song s) {
        int a = this.artist.compareTo(s.getArtist());
        if ( a != 0 ) return a;

        return this.title.compareTo(s.getTitle());
    }

    @Override
    public int hashCode() {
        return this.artist.hashCode() + this.title.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof Song) ) return false;
        Song s = (Song) o;
        return this.artist.equals(s.getArtist()) && this.title.equals(s.getTitle());
    }

    @Override
    public String toString() {
        return "Artist: " + this.artist + ", Title: " + this.title;
    }
}
