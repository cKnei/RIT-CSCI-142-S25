package toys;

public interface IToy {
    /**
     * @return the unique integer product code of the toy.
     */
    int getProductCode();

    /**
     * @return the name of the toy.
     */
    String getName();

    /**
     * @return the toys happiness
     */
    int getHappiness();

    /**
     * A toy is retired when its happiness level reaches/exceeds {@link Toy#MAX_HAPPINESS}
     *
     * @return {@code true} if happiness reaches/exceeds {@link Toy#MAX_HAPPINESS}, false otherwise
     */
    boolean isRetired();

    /**
     * @return the toys current wear
     */
    double getWear();

    /**
     * Increase the wear by the amount passed in.
     *
     * @param amount the amount to increase by
     */
    void increaseWear(double amount);

    /**
     * When played with, the following things happen in order:
     * <ol>
     *      <li>A playing message is displayed to standard output in the format (## is the number of minutes): <br />
     *              {@code PLAYING(##): toy-toString} <br />
     *          For example: <br />
     *              {@code PLAYING(40): Toy{PC:100, N:Play-Doh, H:0, R:false, W:0.0}, PlayDough{C:GREEN}}
     *      </li>
     *      <li>The special play is invoked for the same number of minutes.</li>
     *      <li>The happiness of the toy increases by the number of minutes.</li>
     *      <li>If the happiness of the toy reaches or exceeds MAX_HAPPINESS, the toy retires and displays a message to
     *          standard output in the format: <br />
     *              {@code RETIRED: toy-toString} <br />
     *          For example: <br />
     *              {@code RETIRED: Toy{PC:100, N:Play-Doh, H:101, R:true, W:5.050000000000001}, PlayDough{C:GREEN}}
     * </ol>
     *
     * @param time the amount of time the toy was played with
     */
    void play(int time);
}
