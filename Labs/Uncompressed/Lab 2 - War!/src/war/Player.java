package war;

/**
 * Represents a single player in the game.
 *
 * @author Knei
 */
public class Player {
    /**
     * The <code>Player</code>'s pile of cards
     */
    private final Pile pile;

    /**
     * Is this <code>Player</code> the winner of the game?
     */
    private boolean winner;

    /**
     * Create the <code>Player</code> with their id.
     *
     * <p> The <code>Pile</code>'s name should be "P1" or "P2" based on the id.
     *
     * <p> Initially they are not the winner of the game.
     *
     * @param id The player's id
     */
    public Player(int id) {
        this.pile = new Pile("P" + id);
        this.winner = false;
    }

    /**
     * Add a single card to the bottom of the player's pile.
     *
     * @param card the card to add
     */
    public void addCard(Card card) {
        this.pile.addCard(card);
    }

    /**
     * Add the collection of cards from the incoming pile to the bottom of player's pile, in order. Do not remove cards from the incoming pile! Use Pile's getCards() method to get the collection of cards to add.
     *
     * @param cards the incoming pile of cards to add to this player's pile
     */
    public void addCards(Pile cards) {
        for ( Card card : cards.getCards()) {
            this.pile.addCard(card);
        }
    }

    /**
     * Are there any cards left in this player's pile?
     *
     * @return whether there are cards in the player's pile or not.
     */
    public boolean hasCard() {
        return this.pile.hasCard();
    }

    /**
     * Remove a card from the top of the pile. The intention is the card should switch to be face up.
     *
     * @return the newly removed card from the top of the pile
     */
    public Card drawCard() {
        return this.pile.drawCard(true);
    }

    /**
     * Declare this player to be the winner.
     */
    public void setWinner() {
        this.winner = true;
    }

    /**
     * Is this player the winner?
     *
     * @return whether this player was the winner or not
     */
    public boolean isWinner() {
        return this.winner;
    }

    /**
     * Returns a string representation of this player's pile, e.g.: <br />
     * <code>"P1 pile: 10♢(D) 4♧(D)".</code>
     *
     * @return the string mentioned above
     */
    @Override
    public String toString() {
        return pile.toString();
    }
}
