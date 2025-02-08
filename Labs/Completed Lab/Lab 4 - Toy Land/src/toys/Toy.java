package toys;

/**
 * This is the abstract superclass of the toy inheritance hierarchy.
 *
 * @implNote All documentation for implemented methods can be found in {@link IToy}
 */
public abstract class Toy implements IToy {
    /**
     * Initial happiness value for all toys
     */
    public static final int INITIAL_HAPPINESS = 0;
    /**
     * Initial Wear value for all toys
     */
    public static final double INITIAL_WEAR = 0.0;
    /**
     * Max happiness value for all toys
     */
    public static final int MAX_HAPPINESS = 100;

    /**
     * The unique product code for the toy
     */
    private final int productCode;
    /**
     * The name of the toy
     */
    private final String name;
    /**
     * The happiness value of the toy
     */
    private int happiness;
    /**
     * The wear of the toy
     */
    private double wear;


    protected Toy(int productCode, String name) {
        this.productCode = productCode;
        this.name = name;

        this.happiness = Toy.INITIAL_HAPPINESS;
        this.wear = Toy.INITIAL_WEAR;
    }

    public int getProductCode() {
        return this.productCode;
    }

    public String getName() {
        return this.name;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public boolean isRetired() {
        return this.happiness >= Toy.MAX_HAPPINESS;
    }

    public double getWear() {
        return this.wear;
    }

    public void increaseWear(double amount) {
        this.wear += amount;
    }

    public void play(int time) {
        System.out.println("PLAYING(" + time + "): " + this);
        this.specialPlay(time);
        this.happiness += time;

        if ( this.isRetired() )
            System.out.println("RETIRED: " + this);
    }

    /**
     * Each toy has a special play message, implemented through this method.
     *
     * @param time the amount of time the toy was played with
     */
    protected abstract void specialPlay(int time);

    /**
     * Returns a string representation of the toy in the format: <br />
     * {@code Toy{PC:?, N:?, H:?, R:?, W:?}}
     * <ul>
     *     <li>PC for product code</li>
     *     <li>N for name</li>
     *     <li>H for current happiness</li>
     *     <li>R for retired (true or false)</li>
     *     <li>W for current wear</li>
     * </ul>
     *
     * <p> For example: <br />
     *     {@code Toy{PC:100, N:Play-Doh, H:40, R:false, W:2.0}}
     *
     * @return the String representation of the Object
     */
    @Override
    public String toString() {
        return "Toy{PC:" + this.productCode +
                ", N:" + this.name +
                ", H:" + this.happiness +
                ", R:" + this.isRetired() +
                ", W:" + this.wear + "}";
    }
}
