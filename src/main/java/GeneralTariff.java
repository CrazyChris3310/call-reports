public class GeneralTariff implements Tariff {

    private final int MINUTES_AVAILABLE = 100;
    private final double OVERHEAD_COST = 1.5;
    private final double INITIAL_COST = 0.5;

    @Override
    public double call(int minutesAlreadyDone, int duration) {
        int overhead = minutesAlreadyDone + duration - MINUTES_AVAILABLE;
        if (overhead <= 0) {
            return INITIAL_COST * duration;
        } else if (overhead < duration) {
            int normal = duration - overhead;
            return normal * INITIAL_COST + overhead * OVERHEAD_COST;
        } else {
            return overhead * OVERHEAD_COST;
        }
    }
}
