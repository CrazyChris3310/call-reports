import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;

public class Printer {

    private final File dir;

    public Printer(String directory) {
        dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public void writeReports(Collection<Client> clients) throws IOException {
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        PrintStream output = System.out;
        for (Client client : clients) {
            File file = new File(dir.getAbsolutePath() + "/" + client.getPhoneNumber());
            file.createNewFile();
            output = new PrintStream(new FileOutputStream(file));
            output.printf("Tariff index: %s\n", client.getTariff().getId());
            output.printf("Report for number: %s\n", client.getPhoneNumber());
            output.printf("%-10s %-20s %-20s %-12s %-10s\n", "Call type", "Start Time", "End Time", "Duration", "Cost");
            client.getCalls().sort(Comparator.comparing(CallSession::getStart));
            for (CallSession call : client.getCalls()) {
                output.printf("%-10s %-20s %-20s %-12s %-10.2f\n", call.getCallType(), outFmt.format(call.getStart()),
                        outFmt.format(call.getEnd()), minutesToFormattedString(call.getDuration()), call.getCost());
            }
        }
        output.close();
    }

    public static String minutesToFormattedString(long seconds) {
        long hours = seconds / 3600;
        long mins = seconds % 3600 / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, mins, secs);
    }

}
