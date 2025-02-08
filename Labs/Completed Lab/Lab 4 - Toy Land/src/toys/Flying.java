package toys;

public abstract class Flying extends Toy {
    private double maxAltitude;
    private double currentAltitude;

    protected Flying(int productCode, String name, double maxAltitude) {
        super(productCode, name);

        this.maxAltitude = maxAltitude;
        this.currentAltitude = 0;
    }

    public double getCurrentAltitude() {
        return this.currentAltitude;
    }

    public double getMaxAltitude() {
        return this.maxAltitude;
    }

    @Override
    protected void specialPlay(int time) {
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString() + ", Flying{MA:" + this.maxAltitude + "CA:" + this.currentAltitude + "}";
    }
}
