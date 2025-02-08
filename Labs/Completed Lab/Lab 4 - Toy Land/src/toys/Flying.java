package toys;

public abstract class Flying extends Toy {
    protected double maxAltitude;
    protected double currentAltitude;

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
    public String toString() {
        return super.toString() + ", Flying{MA:" + this.maxAltitude + ", CA:" + this.currentAltitude + "}";
    }
}
