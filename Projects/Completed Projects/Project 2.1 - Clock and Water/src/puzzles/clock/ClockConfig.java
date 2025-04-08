package puzzles.clock;

import puzzles.common.solver.Configuration;

import java.util.Collection;
import java.util.List;

public class ClockConfig implements Configuration {
    private final int totalHours;
    private final int endHour;
    private final int currentHour;

    public ClockConfig(int totalHours, int startHour, int endHour) {
        this.totalHours = totalHours;
        this.endHour = endHour;

        this.currentHour = startHour;
    }

    private ClockConfig(ClockConfig old, int shift) {
        this.totalHours = old.totalHours;
        this.endHour = old.endHour;

        int newCurrentHour = old.currentHour + shift;
        if ( newCurrentHour <= 0 )
            newCurrentHour += this.totalHours;
        else if ( newCurrentHour > this.totalHours )
            newCurrentHour -= this.totalHours;

        this.currentHour = newCurrentHour;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    /**
     * Is the current configuration a solution?
     *
     * @return true if the configuration is a puzzle's solution; false, otherwise
     */
    @Override
    public boolean isSolution() {
        return this.currentHour == this.endHour;
    }

    /**
     * Get the collection of neighbors from the current configuration.
     *
     * @return All the neighbors
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        return List.of(
                new ClockConfig(this, -1),
                new ClockConfig(this, +1)
        );
    }

    @Override
    public int hashCode() {
        return (this.currentHour * 31) ^ -889275714;
    }

    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof ClockConfig c) ) return false;
        return c.currentHour == this.currentHour;
    }

    @Override
    public String toString() {
        return "Hours: " + this.totalHours + ", Start: " + this.currentHour + ", End: " + this.endHour;
    }
}
