package war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a <code>Pile</code> of cards.
 *
 * <p> The behavior of a <code>Pile</code> is that it holds a {@link java.util.ArrayList collection} of
 * {@link Card cards} that can be {@link #addCard(Card) added} to the bottom that are either face up or face down.
 * The cards can be {@link #shuffle() shuffled} randomly into a face down position.
 *
 * <p> When a card is {@link #drawCard(boolean) drawn}, if the top card is face up, it means the <code>Pile</code> needs
 * to be {@link #shuffle() shuffled} before the card is removed from the top.
 *
 * @author Knei
 */
public class Pile {
    /**
     * The random number generator
     *
     * <p> Note this is static as there should be only one instance being used
     */
    private static Random rng;
    /**
     * The name of the <code>Pile</code>
     */
    private final String name;
    /**
     * The collection of cards
     */
    private final ArrayList<Card> cards;

    /**
     * Create the <code>Pile</code> of cards.
     *
     * <p> Initially there are no cards in the <code>Pile</code>.
     *
     * @param name Name of the pile
     */
    public Pile(String name) {
        this.name = name;
        this.cards = new ArrayList<>(0);
    }

    /**
     * Create and set the seed for the random number generator.
     *
     * <p> First create a static instance of {@link java.util.Random} using the default constructor,
     * and next call {@link java.util.Random#setSeed(long) setSeed} on it and pass in the seed number.
     *
     * @param seed The seed value
     */
    public static void setSeed(long seed) {
        Pile.rng = new Random();
        Pile.rng.setSeed(seed);
    }

    /**
     * Shuffle the cards and set them all to face down. It displays the following to standard output:
     *
     * <p><code>"Shuffling {name} pile"</code>
     *
     * <p> Where {name} is the name of the <code>Pile</code>.
     *
     * <p> To shuffle an array list of cards you can do the following:
     *
     * <pre>{@code
     * import Collections;
     * //..
     * Collections.shuffle(cards, rng);
     * }</pre>
     */
    public void shuffle() {
        System.out.println("Shuffling " + this.name + " pile");

        ArrayList<Card> pile = this.getCards();

        Collections.shuffle(pile, Pile.rng);

        for ( Card card : pile) {
            card.setFaceDown();
        }
    }

    /**
     * Add a card to the bottom of the <code>Pile</code> (leave the face setting as is).
     *
     * @param card The {@link Card} to add
     */
    public void addCard(Card card) {
        this.getCards().add(card);
    }

    /**
     * Returns the collection of cards in the <code>Pile</code>'s current state.
     *
     * @return A list of {@link Card} objects representing all the cards in the <code>Pile</code>.
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Get the next top card from the <code>Pile</code>.
     *
     * <p> First, check if the card at the top of the <code>Pile</code> is face up. If so, the <code>Pile</code> needs
     * to be {@link #shuffle() shuffled}, then print it again here after shuffling (cards should now be face down).
     *
     * <p> Second, remove the card that is now at the top. If faceUp is true, the card should be set to be face up,
     * otherwise that state is not changed.
     *
     * <p> Finally, return the top {@link Card card}.
     *
     * @param faceUp Should the card be set to face up when drawn.
     * @return The card that was at the top of the <code>Pile</code>
     */
    public Card drawCard(boolean faceUp) {
        ArrayList<Card> pile = this.getCards();

        if ( pile.getFirst().isFaceUp() ) {
            this.shuffle();
            System.out.println(this);
        }

        Card drawnCard = pile.removeFirst();

        if ( faceUp ) drawnCard.setFaceUp();
        else drawnCard.setFaceDown();

        return drawnCard;
    }

    /**
     * Is there at least one card in the <code>Pile</code>?
     *
     * @return <code>true</code> if the <code>Pile</code> has at least one {@link Card},
     *         otherwise <code>false</code>.
     */
    public boolean hasCard() {
        return !this.getCards().isEmpty();
    }

    /**
     * Remove all cards from the <code>Pile</code> by clearing it out.
     */
    public void clear() {
        this.getCards().clear();
    }

    /**
     * Returns a string representation of the <code>Pile</code> in the format:<br />
     * <code>"{name} pile: first-card second-card ..."</code>
     *
     * <p> Note that this uses the {@link Card#toString()} method to get the card's string.
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.name + " pile: ");
        for ( Card card : this.getCards() ) {
            builder.append(card.toString()).append(" ");
        }
        return builder.toString();
    }
}
