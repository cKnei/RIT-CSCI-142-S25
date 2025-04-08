package puzzles.clock;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.List;
import java.util.Optional;

/**
 * Main class for the clock puzzle. <br/>
 * If you are exploring, can you find the random Easter Egg in this file?
 *
 * @author Knei
 */
public class Clock {
    /**
     * Run an instance of the clock puzzle.
     *
     * @param args [0]: the number of hours in the clock;
     *             [1]: the starting hour;
     *             [2]: the finish hour.
     */
    public static void main(String[] args) {
        if ( args.length < 3 ) {
            System.out.println(("Usage: java Clock hours start finish"));
            return;
        }

        ClockConfig c = new ClockConfig(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        Solver s = new Solver(c);
        Optional<Configuration> solution = s.solve();

        System.out.println(c);
        System.out.println("Total configs: " + s.getConfigurations());
        System.out.println("Unique configs: " + s.getUniqueConfigurations());

        if ( solution.isEmpty() ) {
            System.out.println("No Solution");
            return;
        }

        List<Configuration> optimal = s.getOptimal(solution.get()).reversed();
        for ( int i = 0; i < optimal.size(); i++ )
            System.out.println("Step " + i + ": " + ((ClockConfig) optimal.get(i)).getCurrentHour());
    }
}
