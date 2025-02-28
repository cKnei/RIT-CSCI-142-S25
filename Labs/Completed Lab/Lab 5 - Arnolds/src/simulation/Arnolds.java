package simulation;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Arnolds {
    public static final int SIMULATIONS = 200000;
    public static final long SEED = 42;

    public static final Random RNG = new Random();

    private final HashMap<Song, Integer> jukebox;
    private final ArrayList<Song> songs;

    public Arnolds(String filename) throws FileNotFoundException {
        System.out.println("Loading the jukebox with songs:");
        System.out.println("\tReading songs from " + filename + " into jukebox...");

        Scanner scn = new Scanner(new File(filename));

        this.jukebox = new HashMap<>();
        while ( scn.hasNextLine() ) {
            String[] lineTokens = scn.nextLine().split("<SEP>");
            this.jukebox.put(new Song(lineTokens[2], lineTokens[3]), 0);
        }
        this.songs = new ArrayList<>(this.jukebox.keySet());

        System.out.println("\tJukebox is loaded with " + this.jukebox.size() + " songs");
        System.out.println("\tFirst song in jukebox: " + this.songs.getFirst());
        System.out.println("\tLast song in jukebox: " + this.songs.getLast());

        Arnolds.RNG.setSeed(Arnolds.SEED);
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public HashSet<Integer> play() {
        HashSet<Integer> played = new HashSet<>();

        while ( played.size() < this.jukebox.size() ) {
            int next = Arnolds.RNG.nextInt(this.jukebox.size());
            if ( played.contains(next) ) break;
            played.add(next);
            Song s = this.songs.get(next);
            this.jukebox.put(s, 1 + this.jukebox.get(s));
        }

        return played;
    }

    public void displaySimulationStatistics() {
        /*
         * 	Most played song: "Silent Night" by "Faster Pussy cat"
         * 	All songs alphabetically by "Faster Pussy cat":
         * 		"Silent Night" with 73264 plays
         * 	Most played artist: "Faster Pussy cat" with 73264 plays
         */

        int totalPlayed = 0;
        for ( Song s : this.songs ) {
            totalPlayed += this.jukebox.get(s);
        }


        System.out.println("Displaying simulation statistics:");
        System.out.println("\tNumber of simulations run: " + Arnolds.SIMULATIONS);
        System.out.println("\tTotal number of songs played: " + totalPlayed);
        System.out.println("\tAverage number of songs played per simulation: " + (int) ((double) totalPlayed / Arnolds.SIMULATIONS));
    }

    public static void main(String[] args) throws FileNotFoundException {
        if ( args.length != 1 ) {
            System.out.println("Usage: java Arnolds filename");
            return;
        }

        Arnolds inst = new Arnolds(args[0]);
        ArrayList<Song> songs = inst.getSongs();
        ArrayList<Song> firstFive = new ArrayList<>();

        long sMillis = System.currentTimeMillis();

        for ( int i = 0; i < Arnolds.SIMULATIONS; i++ ) {
            HashSet<Integer> thisRun = inst.play();

            for ( int j = firstFive.size(), k = 0; j < 5 && k < thisRun.size(); j++, k++ )
                firstFive.add(songs.get(k));
        }

        long deltaMillis = System.currentTimeMillis() - sMillis;

        System.out.println("Running the simulation.  The jukebox starts rockin'!");
        System.out.println("\tPrinting first 5 songs played...");
        for ( Song s : firstFive )
            System.out.println("\t\t" + s);

        System.out.println("\tSimulation took " + (int) (deltaMillis/1000) + " second/s to run");

        inst.displaySimulationStatistics();
    }
}
