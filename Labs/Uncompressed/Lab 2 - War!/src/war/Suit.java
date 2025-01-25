package war;

/**
 * An enumerated type for the card suits.
 *
 * @author RIT CS
 */
public enum Suit {
    CLUB("\u2667"),
    HEART("\u2665"),
    DIAMOND("\u2662"),
    SPADE("\u2660");

    /**
     * How the suit is displayed
     */
    private final String display;

    /**
     * Set the suit for this card.
     *
     * @param display the display character
     */
    Suit(String display) {
        this.display = display;
    }

    /**
     * Returns the display character for the suit.
     *
     * @return display character
     */
    @Override
    public String toString() {
        return this.display;
    }
}
