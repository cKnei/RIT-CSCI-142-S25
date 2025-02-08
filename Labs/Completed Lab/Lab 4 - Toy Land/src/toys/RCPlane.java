package toys;

public class RCPlane extends Flying {
    private static final double STARTING_SPEED = 15;
    private static final double ALTITUDE_CHANGE_RATE = 5;
    private static final double SPEED_CHANGE_RATE = 2;

    private static int CurrentProductCode = 300;
    private final double maxSpeed;

    private double currentSpeed;

    protected RCPlane(String name, double maxSpeed, double maxAltitude) {
        super(RCPlane.CurrentProductCode, name, maxAltitude);
        RCPlane.CurrentProductCode++;

        this.maxSpeed = maxSpeed;
        this.currentSpeed = RCPlane.STARTING_SPEED;
    }

    public double getSpeed() {
        return this.currentSpeed;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * The special behaviour when playing is the following:
     * <ol>
     *     <li>It flies increasing its current altitude by time * ALTITUDE_CHANGE_RATE (5 feet).</li>
     *     <li>The first time the toy flies, a tab indented, "\t", take off message is displayed in the format: <br />
     *             {@code {name} took off!} <br />
     *         For example: <br />
     *             {@code Darkstar took off!}
     *     </li>
     *     <li>A tab indented, "\t", message is displayed to standard output in the format: <br />
     *             {@code {name} current altitude: {current altitude} feet} <br />
     *         For example: <br />
     *             {@code Darkstar current altitude: 200.0 feet}
     *     </li>
     *     <li>The wear increases by the current speed.</li>
     *     <li>The current speed increases by time * SPEED_CHANGE_RATE (2.0 mph).</li>
     *     <li>A tab indented, "\t", message is displayed to standard output in the format: <br />
     *            {@code {name} current speed: {current speed} mph} <br />
     *        For example: <br />
     *            {@code Darkstar current speed: 95.0 mph}*
     *     </li>
     * </ol>
     *
     * @param time the amount of time the toy was played with
     */
    @Override
    protected void specialPlay(int time) {
        if (this.currentAltitude == 0.0) {
            System.out.println("\t" + this.getName() + " took off!");
        }

        this.increaseWear(this.currentSpeed);

        this.currentAltitude += time * RCPlane.ALTITUDE_CHANGE_RATE;
        if ( this.currentAltitude > this.maxAltitude)
            this.currentAltitude = this.maxAltitude;

        this.currentSpeed += time * RCPlane.SPEED_CHANGE_RATE;
        if ( this.currentSpeed > this.maxSpeed ) {
            this.currentSpeed = this.maxSpeed;
        }

        System.out.println("\t" + this.getName() + " current altitude: " + this.currentAltitude + " feet");
        System.out.println("\t" + this.getName() + " current speed: " + this.currentSpeed + " mph");
    }

    /**
     * When a RCPlane toy gets printed, the following information is displayed: <br />
     * {@code Toy{PC:?, N:?, H:?, R:?, W:?}, Flying{MA:?, CA:?}, RCPlane{S:?}}
     * <ul>
     *     <li>The {@code Toy{...}} portion comes from {@link Toy#toString}</li>
     *     <li>The {@code Flying{...}} portion comes from {@link Flying#toString}</li>
     *     <li>MA for the maximum altitude</li>
     *     <li>CA for the current altitude</li>
     *     <li>S for the current speed</li>
     * </ul>
     * <p>For example: <br />
     * {@code Toy{PC:300, N:Darkstar, H:0, R:false, W:0.0}, Flying{MA:400.0, CA:0.0}, RCPlane{S:15.0}}
     */
    @Override
    public String toString() {
        return super.toString() + ", RCPlane{S:" + this.currentSpeed + "}";
    }
}
