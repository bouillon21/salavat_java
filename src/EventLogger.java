import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class EventLogger {
    private final String logFilePath;

    public EventLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void logEvent(String message) {
        System.out.println(message);
        try (FileWriter fw = new FileWriter(logFilePath, true);
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}