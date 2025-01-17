import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Client {
    static String serverAddress;
    static int serverPort = 0;
    static String logFilePath;
    static BufferedReader input;
    static PrintWriter output;
    static PrintWriter logWriter;

    public static void main(String[] args) throws IOException {
        loadConfiguration();

        // Создаём пул потоков
        ExecutorService executor = Executors.newCachedThreadPool();
        Socket socket = new Socket(serverAddress, serverPort);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        logWriter = new PrintWriter(new FileWriter(logFilePath, true));
        Scanner scanner = new Scanner(System.in);
        // Запускаем поток для приёма сообщений
        executor.submit(() -> {
            try {
                receiveMessages();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        while (true){
            System.out.println("Напишите список цифр через пробел");
            List<String> input = Arrays.stream(scanner.nextLine().trim().split(" ")).toList();
            List[] res = splitTwo(input);
            output.println("PART1:" + res[0]);
            output.println("PART2:" + res[1]);
        }
    }

    public static List[] splitTwo(List<String> list)
    {
        int midIndex
                = ((list.size() / 2)
                - (((list.size() % 2) > 0) ? 0 : 1));

        List<List<String> > lists = new ArrayList<>(
                list.stream()
                        .collect(Collectors.partitioningBy(
                                s -> list.indexOf(s) > midIndex))
                        .values());

        return new List[] { lists.get(0), lists.get(1) };
    }

    private static void loadConfiguration() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("clientConfig.properties")) {
            properties.load(input);
            serverAddress = properties.getProperty("serverAddress");
            serverPort = Integer.parseInt(properties.getProperty("serverPort"));
            logFilePath = properties.getProperty("logFilePath");
        } catch (IOException ex) {
            System.out.println("Ошибка чтения конфигурации: " + ex.getMessage());
        }
    }

    private static void receiveMessages() throws IOException {
        String response;
        while ((response = input.readLine()) != null) {
            System.out.println("Ответ от сервера: " + response);
            logWriter.println("Ответ от сервера: " + response);
            logWriter.flush();
        }
    }
}
