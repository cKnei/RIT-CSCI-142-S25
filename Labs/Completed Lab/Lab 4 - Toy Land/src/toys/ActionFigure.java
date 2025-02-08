package toys;

public class ActionFigure extends Doll {
    public final static Color HAIR_COLOR = Color.ORANGE;
    public final static int MIN_ENERGY_LEVEL = 1;

    private static int CurrentProductCode = 500;

    private int energyLevel;

    protected ActionFigure(String name, int age, String speak) {
        super(ActionFigure.CurrentProductCode, name, ActionFigure.HAIR_COLOR, age, speak);
        ActionFigure.CurrentProductCode++;

        this.energyLevel = ActionFigure.MIN_ENERGY_LEVEL;
    }

    /**
     * @return the current energyLevel
     */
    public int getEnergyLevel() {
        return this.energyLevel;
    }

    /**
     * The following things happen in order: <br />
     * <ol>
     *      <li>A tab indented, "\t", message is displayed to standard output in the format: <br />
     *              {@code {name} kung foo chops with {energy * time} energy!} <br />
     *          For example: <br />
     *              {@code He-man kung foo chops with 46 energy!}
     *      </li>
     *      <li>The energy is increased by 1.</li>
     *      <li>The special play of Doll is called.</li>
     * </ol>
     *
     * @param time the amount of time the toy was played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\t" + this.getName() + " kung foo chops with " + this.energyLevel * time + " energy!");
        this.energyLevel++;

        super.specialPlay(time);
    }

    /**
     * Returns a string representation in the format: <br />
     * {@code Toy{PC:?, N:?, H:?, R:?, W:?}, Doll{HC:?, A:?, S:?}, ActionFigure{E:?}}
     *
     * <ul>
     *     <li>The {@code Toy{...}} portion comes from {@link Toy#toString}</li>
     *     <li>The {@code Doll{...}} portion comes from {@link Doll#toString}</li>
     *     <li>E for the energy</li>
     * </ul>
     *
     * <p>For example: <br />
     *     {@code Toy{PC:500, N:He-man, H:63, R:false, W:60.0}, Doll{HC:ORANGE, A:30, S:By_the_power_of_Grayskull!}, ActionFigure{E:3}}
     */
    @Override
    public String toString() {
        return super.toString() + ", ActionFigure{E:" + this.energyLevel + "}";
    }
}
