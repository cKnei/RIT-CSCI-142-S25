package war;

/**
 * An enumerated type for the card's rank.
 *
 * @author RIT CS
 */
public enum Rank {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    /**
     * The display character for the rank
     */
    private final String display;
    /**
     * The integer value of the card
     */
    private final int value;

    /**
     * Create the rank for this card.
     *
     * @param display display character
     * @param value   numerical value
     */
    Rank(String display, int value) {
        this.display = display;
        this.value = value;
    }

    /**
     * Get the value for this card.
     *
     * @return the card's value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the display character for the rank.
     *
     * @return the display character
     */
    @Override
    public String toString() {
        return this.display;
    }
}
