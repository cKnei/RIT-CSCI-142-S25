/**
 * Java record to store a 2-D coordinate
 * @param r Row number
 * @param c Column number
 * @author RIT CS
 */
public record Coord(int r, int c) {

    /* Feel free to add any additional helper methods here that you would like. */

    /**
     * A simpler toString than the one provided by Java.
     * @return "(r,c)"
     */
    @Override
    public String toString() {
        return "(" + r + ","+ c + ")";
    }
}
