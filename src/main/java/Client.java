import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private String phoneNumber;
    private Tariff tariff;
    private final List<CallSession> calls = new ArrayList<>();
    private int minutesSpent = 0;

    public Client(String phoneNumber, String tariff) {
        this.phoneNumber = phoneNumber;
        switch (tariff) {
            case "06": this.tariff = new UnlimitedTariff(); break;
            case "03": this.tariff = new MinuteTariff(); break;
            case "11": this.tariff = new GeneralTariff(); break;
        }
    }

    public void addCall(String toWhom, LocalDateTime start, LocalDateTime end, String type) {
        int duration = (int)Math.ceil(Duration.between(start, end).toSeconds() / 60.0);
        double cost = tariff.call(minutesSpent, duration);
        minutesSpent += duration;
        CallSession current = new CallSession(type, this.phoneNumber, start, end, cost);
        this.calls.add(current);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public List<CallSession> getCalls() {
        return calls;
    }
}
