package hotpotato;

/**
 * Represents a single {@code Player} in the game.
 *
 * @author Knei
 */
public class Player {
    /**
     * Name of the {@code Player}
     */
    private final String name;
    /**
     * ID of the {@code Player}
     */
    private final int id;

    /**
     * Creates a new {@code Player} instance.
     *
     * @param name of the {@code Player}
     * @param id   of the {@code Player}
     */
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Getter for the name of the {@code Player}
     *
     * @return the name of the {@code Player}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the ID of the {@code Player}
     *
     * @return the ID of the {@code Player}
     */
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( !(o instanceof Player) ) return false;

        Player p = (Player) o;
        return this.name.equals(p.getName()) && this.id == p.getId();
    }

    /**
     * Returns a String in the form: {@code {name}({id})}
     *
     * @return the String representation of a {@code Player}
     */
    @Override
    public String toString() {
        return this.name + "(" + this.id + ")";
    }
}
