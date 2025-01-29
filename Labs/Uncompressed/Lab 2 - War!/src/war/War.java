package war;

/**
 * The main program for the card game, War. It is run on the command line as:<br />
 * <code>java War cards-per-player seed</code>
 *
 * @author RIT CS
 * @author Knei
 */
public class War {
    /**
     * The maximum number of cards a single player can have
     */
    public final static int MAX_CARDS_PER_PLAYER = 26;
    /**
     * The first player
     */
    private final Player p1;
    /**
     * The second player
     */
    private final Player p2;
    /**
     * The current round
     */
    private int round;

    /**
     * Initialize the game.
     *
     * <ol>
     *     <li>Create the initial {@link war.Pile} of cards (face down), shuffle and print them.</li>
     *     <li>Next, create the two {@link war.Player}'s in order. </li>
     *     <li>From there distribute the {@link Card}'s to the {@link war.Player} {@link war.Pile} in order
     *         (p1 draws/adds the first card, p2 draws/adds the second card, rinse/repeat for the total cards
     *         per player).</li>
     * </ol>
     *
     * @param cardsPerPlayer The number of cards for a single player
     */
    public War(int cardsPerPlayer) {
        this.round = 0;

        Pile initialPile = new Pile("Initial");

        for ( Rank rank : Rank.values() ) {
            for ( Suit suit : Suit.values() ) {
                initialPile.addCard(new Card(rank, suit));
            }
        }
        initialPile.shuffle();
        System.out.println(initialPile);

        this.p1 = new Player(1);
        this.p2 = new Player(2);

        for ( ; cardsPerPlayer > 0; cardsPerPlayer-- ) {
            p1.addCard(initialPile.drawCard(false));
            p2.addCard(initialPile.drawCard(false));
        }
    }

    /**
     * Play a single round of the game.
     */
    private void playRound() {
        this.round += 1;

        System.out.println("ROUND " + this.round);

        Pile trick = new Pile("Trick");

        while (true) {
            System.out.println(this.p1);
            System.out.println(this.p2);

            if ( trick.hasCard() ) {
                if ( !this.p2.hasCard() ) {
                    System.out.println("P1 wins round gets " + trick);
                    this.p1.addCards(trick);
                    this.p1.setWinner();
                    break;
                } else if ( !this.p1.hasCard() ) {
                    System.out.println("P2 wins round gets " + trick);
                    this.p2.addCards(trick);
                    this.p2.setWinner();
                    break;
                }
            }

            Card p1Card = this.p1.drawCard();
            trick.addCard(p1Card);

            Card p2Card = this.p2.drawCard();
            trick.addCard(p2Card);

            System.out.println("P1 card: " + p1Card);
            System.out.println("P2 card: " + p2Card);

            if ( p1Card.equals(p2Card) ) {
                System.out.println("WAR!");
                continue;
            }

            if ( p1Card.beats(p2Card) ) {
                System.out.println("P1 wins round gets " + trick);
                this.p1.addCards(trick);
                break;
            } else if ( p2Card.beats(p1Card) ) {
                System.out.println("P2 wins round gets " + trick);
                this.p2.addCards(trick);
                break;
            } else {
                throw new Error("Oh no");
            }
        }
    }

    /**
     * Play the full game.
     */
    public void playGame() {
        while ( this.p1.hasCard() && this.p2.hasCard() ) {
            this.playRound();
        }

        System.out.println(this.p1);
        System.out.println(this.p2);

        if (!this.p2.hasCard()) {
            this.p1.setWinner();
            System.out.println("P1 WINS!");
        } else if (!this.p1.hasCard()) {
            this.p2.setWinner();
            System.out.println("P2 WINS!");
        } else {
            throw new Error("Okay how did no one win");
        }
    }

    /**
     * The main method get the command line arguments, then constructs and plays the game. The <code>Pile</code>'s
     * random number generator and seed need should get set here using Pile.setSeed(seed).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if ( args.length != 2 ) {
            System.out.println("Usage: java War cards-per-player seed");
            return;
        }

        int cardsPerPlayer = Integer.parseInt(args[0]);

        // must be between 1 and 26 cards per player
        if ( cardsPerPlayer <= 0 || cardsPerPlayer > MAX_CARDS_PER_PLAYER ) {
            System.out.println("cards-per-player must be between 1 and " + MAX_CARDS_PER_PLAYER);
            return;
        }

        // set the rng seed
        Pile.setSeed(Integer.parseInt(args[1]));
        War war = new War(cardsPerPlayer);
        war.playGame();
    }
}
