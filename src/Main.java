import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу данных:");
        String dataFilePath = scanner.nextLine().trim();

        List<String> lines = Files.readAllLines(Paths.get(dataFilePath));

        EventLogger logger = new EventLogger(lines.getFirst());
        List<Integer> numbers = Arrays.stream(lines.get(1)
                        .split(","))
                        .map(String::trim)
                        .map(Integer::parseInt).toList();

        try {
            Var10Impl task = new Var10Impl(logger);
            task.runTask(numbers);
        } catch (LabaException e) {
            logger.logEvent(e.getMessage());
            e.printStackTrace();
        }
    }
}
