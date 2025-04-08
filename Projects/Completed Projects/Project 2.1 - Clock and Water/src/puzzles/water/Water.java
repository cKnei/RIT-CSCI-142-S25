package puzzles.water;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Water {
    /**
     * Run an instance of the water buckets puzzle.
     *
     * @param args [0]: desired amount of water to be collected;
     *             [1..N]: the capacities of the N available buckets.
     */
    public static void main(String[] args) {
        if ( args.length < 2 ) {
            System.out.println("Usage: java Water amount bucket1 bucket2 ...");
            return;
        }
        // TODO YOUR MAIN CODE HERE

        int target = Integer.parseInt(args[0]);
        int[] buckets = new int[args.length - 1];
        for ( int i = 1; i < args.length; i++ )
            buckets[i - 1] = Integer.parseInt(args[i]);

        WaterConfig w = new WaterConfig(target, buckets);
        Solver s = new Solver(w);

        Optional<Configuration> solution = s.solve();

        System.out.println(w);
        System.out.println("Total configs: " + s.getConfigurations());
        System.out.println("Unique configs: " + s.getUniqueConfigurations());

        if ( solution.isEmpty() ) {
            System.out.println("No Solution");
            return;
        }

        List<Configuration> optimal = s.getOptimal(solution.get()).reversed();
        for ( int i = 0; i < optimal.size(); i++ )
            System.out.println("Step " + i + ": " + Arrays.toString(((WaterConfig) optimal.get(i)).getCurrentBuckets()));
    }
}
