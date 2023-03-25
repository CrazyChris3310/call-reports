public class UnlimitedTariff extends Tariff {

    private final int DEFAULT_COST = 100;
    private final int OVERHEAD_COST = 1;
    private final int CALLS_AMOUNT = 300;

    public UnlimitedTariff() {
        super("06");
    }

    @Override
    public double call(int minutesAlreadyDone, int duration) {
        int overhead = minutesAlreadyDone + duration - CALLS_AMOUNT;
        if (overhead < 0) {
            return 0;
        } else if (minutesAlreadyDone > 300) {
            return duration * OVERHEAD_COST;
        } else {
            return DEFAULT_COST + overhead * OVERHEAD_COST;
        }
    }
}
