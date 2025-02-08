package toys;

public class Doll extends Toy {
    private static int CurrentProductCode = 200;

    private final Color hairColor;
    private final int age;
    private final String speak;

    protected Doll(String name, Color hairColor, int age, String speak) {
        this(CurrentProductCode, name, hairColor, age, speak);
        Doll.CurrentProductCode++;
    }

    protected Doll(int productCode, String name, Color hairColor, int age, String speak) {
        super(productCode, name);

        this.hairColor = hairColor;
        this.age = age;
        this.speak = speak;
    }

    /**
     * @return the hair colour
     */
    public Color getHairColor() {
        return this.hairColor;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * @return the catchphrase
     */
    public String getSpeak() {
        return this.speak;
    }

    /**
     * The following things happen in order: <br />
     *
     * <ol>
     *      <li>A tab indented, "\t", message is displayed to standard output in the format: <br />
     *              {@code {name} brushes their {hair color} hair and says, "{speak}"} <br />
     *          For example: <br />
     *              {@code GabbyGabby brushes their RED hair and says, "Will_you_be_my_friend?"}
     *      </li>
     *      <li>The wear increases by the age.</li>
     * </ol>
     *
     * @param time the amount of time the toy was played with
     */
    @Override
    protected void specialPlay(int time) {
        System.out.println("\t" + this.getName() + " brushes their " + this.hairColor + " hair and says, \"" + this.speak + "\"");
        this.increaseWear(this.age);
    }

     /**
     * Returns a string representation in the format: <br />
     * {@code Toy{PC:?, N:?, H:?, R:?, W:?}, Doll{HC:?, A:?, S:?}}
     * <ul>
     *     <li>The {@code Toy{...}} portion comes from {@link Toy#toString}</li>
     *     <li>HC for the hair color</li>
     *     <li>A for the age</li>
     *     <li>S for the spoken line</li>
     * </ul>
     *
     * <p>For example: <br />
     * {@code Toy{PC:200, N:GabbyGabby, H:101, R:true, W:18.0}, Doll{HC:RED, A:6, S:Will_you_be_my_friend?}}
     */
    @Override
    public String toString() {
        return super.toString() + ", Doll{HC:" + this.hairColor + ", A:" + this.age + ", S:" + this.speak + "}";
    }
}
