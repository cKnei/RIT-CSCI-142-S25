package war;

/**
 * Represents a single card in the game.
 *
 * @author RIT CS
 * @author Knei
 */
public class Card {
    /**
     * The <code>{@link war.Rank}</code> of the card
     */
    private final Rank rank;
    /**
     * The <code>{@link war.Suit}</code> of the card
     */
    private final Suit suit;
    /**
     * Is the card face up?
     */
    private boolean faceUp;


    /**
     * Create the card with the rank and suit.
     * The card is not face up to begin with.
     *
     * @param rank The card's rank
     * @param suit The card's suit
     */
    public Card(Rank rank, Suit suit) {
        this.faceUp = false;

        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Set the card to be face up
     */
    public void setFaceUp() {
        this.faceUp = true;
    }

    /**
     * Set the card to be face down
     */
    public void setFaceDown() {
        this.faceUp = false;
    }

    /**
     * Is the card face up or not?
     *
     * @return <code>true</code> if this card is face up, otherwise <code>false</code>
     */
    public boolean isFaceUp() {
        return this.faceUp;
    }

    /**
     * Does this card beat (have a higher rank value) than the other card?
     *
     * @param other The card to compare to
     * @return <code>true</code> if this card's value is greater than the other card, <code>false</code> otherwise
     */
    public boolean beats(Card other) {
        return this.rank.getValue() > other.rank.getValue();
    }

    /**
     * Returns the string representation for a Card which contains the rank, suit and whether the card is face up or not.
     * <p> For example:
     * <ul>
     *     <li>If the card is an Ace of Clubs and is face up it would return <code>"A♧(U)"</code>.</li>
     *     <li>If the card is a Seven of Spades and is face down it would return <code>"7♠(D)"</code>.</li>
     * </ul>
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        String shown;

        if (this.faceUp) {
            shown = "U";
        } else {
            shown = "D";
        }

        return "" + this.rank + this.suit + "(" + shown + ")";
    }

    /**
     * Two cards are equal if they have the same rank (regardless of the suit).
     *
     * @param other The card to compare to for equality
     * @return <code>false</code> if the provided {@link java.lang.Object} is not an instance of {@link Card} or if
     *         the ranks are not the same. Otherwise, <code>true</code> if the ranks are the same.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof Card && this.rank.getValue() == ((Card) other).rank.getValue();
    }
}
