public class MinuteTariff implements Tariff {

    private final double MINUTE_COST = 1.5;

    @Override
    public double call(int minutesAlreadyDone, int duration) {
        return duration * MINUTE_COST;
    }
}
