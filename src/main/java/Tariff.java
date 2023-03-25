public interface Tariff {
    double call(int minutesAlreadyDone, int duration);
}