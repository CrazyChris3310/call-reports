public abstract class Tariff {

    private final String id;

    public Tariff(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract double call(int minutesAlreadyDone, int duration);
}