package war;

/**
 * An enumerated type representing the <code>Rank</code> of a <code>{@link war.Card Card}</code>.
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
     * The display character for the <code>Rank</code>
     */
    private final String display;
    /**
     * The integer value of the card
     */
    private final int value;

    /**
     * Create the <code>Rank</code> for this card.
     *
     * @param display The display character
     * @param value   The numerical value
     */
    Rank(String display, int value) {
        this.display = display;
        this.value = value;
    }

    /**
     * Get the value for this <code>Rank</code>.
     *
     * @return This <code>Rank</code>'s value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the display character for the <code>Rank</code>.
     *
     * @return The display character
     */
    @Override
    public String toString() {
        return this.display;
    }
}
