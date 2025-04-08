package puzzles.water;

import puzzles.common.solver.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Main class for the water buckets puzzle.
 *
 * @author Knei
 */
public class WaterConfig implements Configuration {
    private static int target;
    private static int[] bucketsMaxFill;
    private final int[] buckets;

    public WaterConfig(int target, int... bucketsMaxFill) {
        WaterConfig.target = target;
        WaterConfig.bucketsMaxFill = bucketsMaxFill;
        this.buckets = new int[bucketsMaxFill.length];
    }

    private WaterConfig(int[] buckets) {
        this.buckets = buckets;
    }

    /**
     * Is the current configuration a solution?
     *
     * @return true if the configuration is a puzzle's solution; false, otherwise
     */
    @Override
    public boolean isSolution() {
        for ( Integer bucket : this.buckets )
            if ( bucket == target ) return true;
        return false;
    }

    /**
     * Get the collection of neighbors from the current configuration.
     *
     * @return All the neighbors
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        ArrayList<Configuration> neighboringConfigurations = new ArrayList<>();

        int bucketCount = this.buckets.length;

        for ( int i = 0; i < bucketCount; i++ ) {
            // Fill Self
            if ( this.buckets[i] < WaterConfig.bucketsMaxFill[i] ) {
                int[] nBuckets = this.buckets.clone();
                nBuckets[i] = WaterConfig.bucketsMaxFill[i];
                neighboringConfigurations.add(new WaterConfig(nBuckets));
            }

            // Empty Self
            if ( this.buckets[i] != 0 ) {
                int[] nBuckets = this.buckets.clone();
                nBuckets[i] = 0;
                neighboringConfigurations.add(new WaterConfig(nBuckets));
            }

            // Pour Into Other Buckets

            if ( this.buckets[i] != 0 )
                for ( int j = 0; j < bucketCount; j++ ) {
                    if ( i == j ) continue;
                    if ( this.buckets[j] == WaterConfig.bucketsMaxFill[j] ) continue;

                    int canMove = Math.min(this.buckets[i], Math.min(WaterConfig.bucketsMaxFill[j] - this.buckets[j], WaterConfig.bucketsMaxFill[j]));

                    int[] nBuckets = this.buckets.clone();
                    nBuckets[i] -= canMove;
                    nBuckets[j] += canMove;

                    neighboringConfigurations.add(new WaterConfig(nBuckets));
                }
        }

        return neighboringConfigurations;
    }

    public int[] getCurrentBuckets() {
        return this.buckets.clone();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.buckets);
    }

    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof WaterConfig w) ) return false;
        return Arrays.equals(this.buckets, w.buckets);
    }

    @Override
    public String toString() {
        return "Amount: " + WaterConfig.target + ", Buckets: " + Arrays.toString(WaterConfig.bucketsMaxFill);
    }
}
