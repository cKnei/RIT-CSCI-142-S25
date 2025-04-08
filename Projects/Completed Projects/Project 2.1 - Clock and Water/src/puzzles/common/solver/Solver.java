package puzzles.common.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Solver {
    private final HashMap<Configuration, Configuration> map;
    private final ArrayList<Configuration> queue;

    private int configurations = 0;

    public Solver(Configuration c) {
        this.map = new HashMap<>(0);
        this.map.put(c, null);

        this.queue = new ArrayList<>(0);
        this.queue.add(c);

        this.configurations++;
    }

    public Optional<Configuration> solve() {
        while (!this.queue.isEmpty()) {
            Configuration c = this.queue.removeFirst();
            if (c.isSolution()) return Optional.of(c);

            for ( Configuration neighbor : c.getNeighbors() ) {
                this.configurations++;
                if (this.map.containsKey(neighbor)) continue;
                this.map.put(neighbor, c);
                this.queue.add(neighbor);
            }
        }

        return Optional.empty();
    }

    public ArrayList<Configuration> getOptimal(Configuration end) {
        ArrayList<Configuration> optimal = new ArrayList<>();
        while ( end != null ) {
            optimal.add(end);
            end = this.map.get(end);
        }
        return optimal;
    }

    public int getConfigurations() {
        return this.configurations;
    }

    public int getUniqueConfigurations() {
        return this.map.size();
    }
}
