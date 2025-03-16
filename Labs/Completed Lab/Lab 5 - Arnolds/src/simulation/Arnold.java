package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Arnold {
    private final static int RUNS = 200000;
    public HashMap<Song, Integer> jukebox;
    public ArrayList<Song> songs;
    public Song mostPlayedSong;
    public String mostPlayedArtist;
    public int highestPlays;
    public int highestArtistPlays;
    public int topArtistTotal = 0;


    public Arnold(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        HashMap<Song, Integer> Jukebox = new HashMap<>();
        while ( in.hasNextLine() ) {
            String[] line = in.nextLine().split("<SEP>");
            Song song = new Song(line[2], line[3]);
            Jukebox.put(song, 0);
        }
        this.jukebox = Jukebox;
        this.songs = new ArrayList<>(Jukebox.keySet());

    }

    public void play(String filename) {
        System.out.println("Loading the jukebox with songs:");
        System.out.println("\tReading songs from " + filename + " into jukebox...");
        System.out.println("\tJukebox is loaded with " + this.jukebox.size() + " songs");
        System.out.println("\tFirst song in jukebox: " + this.songs.getFirst());
        System.out.println("\tLast song in jukebox: " + this.songs.getLast());
        System.out.println("Running the simulation.  The jukebox starts rockin'!");
        System.out.println("\tPrinting first 5 songs played...");

        Random rnd = new Random();
        rnd.setSeed(42);

        ArrayList<Song> firstSongs = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for ( int i = 0; i < RUNS; ++i ) {
            HashSet<Song> set = new HashSet<>();
            while ( true ) {
                int songIndex = rnd.nextInt(this.songs.size());
                if ( !set.add(this.songs.get(songIndex)) ) {
                    break;
                }
                if ( firstSongs.size() < 5 ) {
                    firstSongs.add(this.songs.get(songIndex));
                }
            }

            for ( Song song : set ) {
                if ( this.jukebox.containsKey(song) ) {
                    this.jukebox.put(song, this.jukebox.get(song) + 1);
                }
            }
        }
        long endTime = System.currentTimeMillis();

        int time = (int) ((endTime - startTime) / 1000);

        for ( Song songs : firstSongs ) {
            System.out.println("\t\t" + songs);
        }

        System.out.println("\tSimulation took " + time + " second/s to run");
    }

    public Song getMostPlayedSong() {
        int highestPlays = 0;
        for ( Map.Entry<Song, Integer> song : this.jukebox.entrySet() ) {
            if ( highestPlays < song.getValue() ) {
                highestPlays = song.getValue();
                this.mostPlayedSong = song.getKey();
            }
        }
        this.highestPlays = highestPlays;
        return this.mostPlayedSong;
    }

    public String getMostPlayedArtist() {
        HashMap<String, Integer> Artists = new HashMap<>();
        for ( Map.Entry<Song, Integer> song : this.jukebox.entrySet() ) {
            if ( Artists.containsKey(song.getKey().getArtist()) ) {
                Artists.put(song.getKey().getArtist(), Artists.get(song.getKey().getArtist()) + song.getValue());
            } else {
                Artists.put(song.getKey().getArtist(), song.getValue());
            }
        }
        for ( Map.Entry<String, Integer> artist : Artists.entrySet() ) {
            if ( this.highestArtistPlays < artist.getValue() ) {
                this.highestArtistPlays = artist.getValue();
                this.mostPlayedArtist = artist.getKey();
            }
        }
        return this.mostPlayedArtist;
    }

    public int getTotalPlays() {
        int total_plays = 0;
        for ( int value : this.jukebox.values() ) {
            total_plays += value;
        }
        return total_plays;
    }

    public int getAvgPlays() {
        int avgPlays;
        avgPlays = (int) Math.ceil((double) this.getTotalPlays() / RUNS);
        return avgPlays;
    }

    public void displayStatistics() {
        System.out.println("Displaying simulation statistics:");
        System.out.println("\tNumber of simulations run: " + RUNS);
        System.out.println("\tTotal number of songs played: " + this.getTotalPlays());
        System.out.println("\tAverage number of songs played per simulation to get duplicate: " + this.getAvgPlays());
        System.out.println("\tMost played song: \"" + this.getMostPlayedSong().getTitle() + "\" by \"" + this.getMostPlayedSong().getArtist() + "\"");
    }

    public void alphabeticalArtist() {
        TreeMap<String, Integer> topArtist = new TreeMap<>();
        String targetArtist = this.getMostPlayedSong().getArtist();
        for ( Map.Entry<Song, Integer> song : this.jukebox.entrySet() ) {
            if ( song.getKey().getArtist().equals(targetArtist) ) {
                topArtist.put(song.getKey().getTitle(), song.getValue());
            }
        }
        System.out.println("\tAll songs alphabetically by \"" + targetArtist + "\":");
        for ( Map.Entry<String, Integer> song : topArtist.entrySet() ) {
            System.out.println("\t\t\"" + song.getKey() + "\" with " + song.getValue() + " plays");
            this.topArtistTotal += song.getValue();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Arnold newArnolds = new Arnold(filename);
        newArnolds.play(filename);
        newArnolds.displayStatistics();
        newArnolds.alphabeticalArtist();
        System.out.println("\tMost played artist: \"" + newArnolds.getMostPlayedArtist() + "\" with " + newArnolds.highestArtistPlays + " plays");
    }
}
