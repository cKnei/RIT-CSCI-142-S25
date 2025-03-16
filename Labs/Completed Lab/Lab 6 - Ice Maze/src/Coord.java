/**
 * Java record to store a 2-D coordinate
 * @param r Row number
 * @param c Column number
 * @author RIT CS
 * @author Knei
 */
public record Coord(int r, int c) {

    /* Feel free to add any additional helper methods here that you would like. */
    @Override
    public boolean equals(Object o) {
        if ( o == null) return false;
        if ( !(o instanceof Coord(int r1, int c1)) ) return false;
        return this.r == r1 && this.c == c1;
    }

    /**
     * A simpler toString than the one provided by Java.
     * @return "(r,c)"
     */
    @Override
    public String toString() {
        return "(" + r + ","+ c + ")";
    }
}
