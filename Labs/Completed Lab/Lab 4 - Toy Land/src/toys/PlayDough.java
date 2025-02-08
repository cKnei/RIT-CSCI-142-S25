package toys;

public class PlayDough extends Toy {
    public static final double WEAR_MULTIPLIER = 0.05;
    private static int CurrentProductCode = 100;

    private final Color color;

    protected PlayDough(String name, Color color) {
        super(PlayDough.CurrentProductCode, name);
        PlayDough.CurrentProductCode++;

        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    /**
     * The following things happen in order:
     * <ol>
     *     <li>A tab indented, "\t", message is displayed to standard output in the format: <br />
     *             {@code Arts and crafting with {color} {name}} <br />
     *         For example: <br />
     *             {@code Arts and crafting with GREEN Play-Doh}
     *     </li>
     *    <li>The wear increases by a multiple of WEAR_MULTIPLIER * time.</li>
     * </ol>
     *
     * @param time the amount of time the toy was played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\tArts and crafting with " + this.color + " " + this.getName());
        this.increaseWear(time * PlayDough.WEAR_MULTIPLIER);
    }

    /**
     * Returns a string representation in the format:<br />
     * {@code Toy{PC:?, N:?, H:?, R:?, W:?}, PlayDough{C:?}}
     *
     * <ul>
     *      <li>The {@code Toy{...}} portion comes from {@link Toy#toString}</li>
     *      <li>C for the color</li>
     * </ul>
     *
     * <p>For example:<br />
     *     {@code Toy{PC:100, N:Play-Doh, H:40, R:false, W:2.0}, PlayDough{C:GREEN}}
     */
    @Override
    public String toString() {
        return super.toString() + ", PlayDough{C:" + this.color + "}";
    }
}
