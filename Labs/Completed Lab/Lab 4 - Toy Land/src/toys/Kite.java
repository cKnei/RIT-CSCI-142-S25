package toys;

public class Kite extends Toy {
    private static final double ALTITUDE_CHANGE_RATE = 0.5;
    private static final double WEAR_MULTIPLIER = 0.05;

    private static int CurrentProductCode = 400;

    private final Color color;
    private final Type type;
    private final int lineLength;

    private final double maxAltitude;
    private double currentAltitude;

    public Kite(String name, Color color, Type type, int lineLength) {
        super(Kite.CurrentProductCode, name);
        Kite.CurrentProductCode++;

        this.color = color;
        this.type = type;
        this.lineLength = lineLength;
        this.maxAltitude = lineLength;
        this.currentAltitude = 0;
    }

    public double getCurrentAltitude() {
        return this.currentAltitude;
    }

    public double getMaxAltitude() {
        return this.maxAltitude;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public int getLineLength() {
        return this.lineLength;
    }

    /**
     * The special behaviour when playing is the following:
     * <ol>
     *      <li>It flies increasing their current altitude by time * ALTITUDE_CHANGE_RATE (0.5 feet).</li>
     *      <li>The first time the toy flies, a tab indented, "\t", take off message is displayed in the format:<br />
     *              {@code {name} took off!}<br />
     *          For example:<br />
     *              {@code Cometa took off!}
     *      </li>
     *      <li>A tab indented, "\t", message is displayed to standard output in the format: <br />
     *              {@code {name} current altitude: {current altitude} feet} <br />
     *          For example: <br />
     *              {@code Cometa current altitude: 20.0 feet}
     *      </li>
     *      <li>The wear increases by a multiple of time * WEAR_MULTIPLIER (0.05).</li>
     *      <li>A tab indented, "\t", message is displayed to standard output in the format:<br />
     *              {@code Flying the {color}, {type} kite {name} with {line length} feets of line}<br />
     *          For example:<br />
     *              {@code Flying the RED, DELTA kite Cometa with 100 feets of line}
     *      </li>
     * </ol>
     *
     * @param time the amount of time the toy was played with
     */
    @Override
    protected void specialPlay(int time) {
        if (this.currentAltitude == 0.0) {
            System.out.println("\t" + this.getName() + " took off!");
        }

        this.currentAltitude += time * Kite.ALTITUDE_CHANGE_RATE;
        this.increaseWear(time * Kite.WEAR_MULTIPLIER);

        System.out.println("\t" + this.getName() + " current altitude: " + this.currentAltitude + " feet");
        System.out.println("\tFlying the " + this.color + ", " + this.type + " kite " + this.getName() + " with " + this.lineLength + " feets of line");
    }

    /**
     * When a Kite toy gets printed, the following information is displayed: <br />
     * {@code Toy{PC:?, N:?, H:?, R:?, W:?}, Flying{MA:?, CA:?}, Kite{C:?, T:?, LL:?}}
     * <ul>
     *     <li>The {@code Toy{...}} portion comes from {@link Toy#toString}</li>
     *     <li>The {@code Flying{...}} portion comes from {@link Flying#toString}</li>
     *     <li>C for the color</li>
     *     <li>T for the type</li>
     *     <li>LL for the line length</li>
     * </ul>
     *
     * <p>For example: <br />
     * {@code Toy{PC:400, N:Cometa, H:0, R:false, W:0.0}, Flying{MA:100.0, CA:0.0}, Kite{C:RED, T:DELTA, LL:100}}
     */
    @Override
    public String toString() {
        return super.toString() + ", Flying{MA:" + this.maxAltitude + ", CA:" + this.currentAltitude + "}, Kite{C:" + this.color + ", T:" + this.type + ", LL:" + this.lineLength + "}";
    }

    public enum Type {
        CELLULAR,
        DELTA,
        DIAMOND,
        PARAFOIL,
        ROKKAKUS,
        SLED,
        STUNT
    }
}
