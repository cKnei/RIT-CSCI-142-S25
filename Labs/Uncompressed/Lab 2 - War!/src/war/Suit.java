package war;

/**
 * An enumerated type representing the <code>Suit</code> of a <code>{@link war.Card Card}</code>.
 *
 * @author RIT CS
 */
public enum Suit {
    CLUB('\u2667'),
    HEART('\u2665'),
    DIAMOND('\u2662'),
    SPADE('\u2660');

    /**
     * How the suit is displayed
     */
    private final char display;

    /**
     * Constructs a new <code>Suit</code> with the specified display character
     *
     * @param display The display character
     */
    Suit(char display) {
        this.display = display;
    }

    /**
     * Returns the display character for this <code>Suit</code>.
     *
     * @return The display character
     */
    @Override
    public String toString() {
        return String.valueOf(this.display);
    }
}