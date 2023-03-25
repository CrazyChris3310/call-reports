public class MinuteTariff extends Tariff {

    private final double MINUTE_COST = 1.5;

    public MinuteTariff() {
        super("03");
    }

    @Override
    public double call(int minutesAlreadyDone, int duration) {
        return duration * MINUTE_COST;
    }
}
