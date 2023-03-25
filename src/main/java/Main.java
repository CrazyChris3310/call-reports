import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String, Client> clients = new HashMap<>();

    public static void main(String[] args) {
        DateTimeFormatter inFmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/cdr.txt")))) {
            String line = input.readLine();
            while (line != null) {
                String[] toks = line.split(", ");
                LocalDateTime start = LocalDateTime.parse(toks[2], inFmt);
                LocalDateTime end = LocalDateTime.parse(toks[3], inFmt);
                Client caller;
                if (!clients.containsKey(toks[1])) {
                    caller = new Client(toks[1], toks[4]);
                    clients.put(toks[1], caller);
                } else {
                    caller = clients.get(toks[1]);
                }
                caller.addCall(toks[1], start, end, toks[0]);
                line = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error happened with io. " + e.getMessage());
            return;
        }

        try {
            Printer printer = new Printer("./report");
            printer.writeReports(clients.values());
        } catch (IOException e) {
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

}
