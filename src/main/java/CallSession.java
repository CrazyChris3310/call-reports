import java.time.Duration;
import java.time.LocalDateTime;

public class CallSession {

    public static final String INCOMING_CALL = "02";
    public static final String OUTGOING_CALL = "01";

    private String callType;
    private String caller;
    private LocalDateTime start;
    private LocalDateTime end;
    private long duration;
    private double cost;

    public CallSession(String callType, String caller, LocalDateTime start,
                       LocalDateTime end, double cost) {
        this.caller = caller;
        this.start = start;
        this.end = end;
        this.duration = Duration.between(start, end).toSeconds();
        this.callType = callType;
        if (this.callType.equals(INCOMING_CALL)) {
            this.cost = 0;
        } else {
            this.cost = cost;
        }
    }

    public String getCallType() {
        return callType;
    }

    public String getCaller() {
        return caller;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
