import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Server {
    static List<String> msg1 = new ArrayList<String>();
    static List<String> msg2 = new ArrayList<String>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Укажите порт в командной строке.");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу журнала сервера:");
        String logFilePath = scanner.nextLine().trim();
        String msg1 = null;
        String msg2 = null;

        try (ServerSocket serverSocket = new ServerSocket(port);
             PrintWriter logWriter = new PrintWriter(new FileWriter(logFilePath, true))) {

            System.out.println("Сервер ожидает подключения...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Клиент подключен.");
                    String message;
                    while ((message = input.readLine()) != null) {
                        processRequest(message, logWriter, output);
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при взаимодействии с клиентом: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }

    private static void processRequest(String request, PrintWriter logWriter, PrintWriter output) throws IOException {
        List<String> parts = Arrays.stream(
                request.replace("[", "")
                        .replace("]", "")
                        .split("[:,]")).toList();
        String action = parts.getFirst().trim();

        if (action.equals("PART1")) {
            logWriter.println("Получено от клиента: " + request);
            logWriter.flush();
            parts.stream().skip(1).forEach(msg1::add);
        } else if (action.equals("PART2")) {
            logWriter.println("Получено от клиента: " + request);
            logWriter.flush();
            if (msg1.isEmpty()) {
                logWriter.println("Потеряна первая часть" + request);
                System.out.println("Потеряна первая часть");
                return;
            }
            parts.stream().skip(1).forEach(msg2::add);
            List<Integer> newList = Stream.concat(msg1.stream(), msg2.stream()).map(String::trim).map(Integer::parseInt).toList();
            output.println(test(newList));
        }
    }

    private static String test(List<Integer> numbers) {
        int evenPositiveCount = 0;
        int oddPositiveCount = 0;

        for (int number : numbers) {
            if (number <= 0) {
                continue;
            }
            if (number % 2 == 0) {
                evenPositiveCount++;
            } else {
                oddPositiveCount++;
            }
        }

        if (evenPositiveCount > oddPositiveCount) {
            return "Больше чётных и положительных чисел.";
        } else if (oddPositiveCount > evenPositiveCount) {
            return "Больше нечётных и положительных чисел.";
        } else {
            return "Чётных и нечётных положительных чисел поровну.";
        }
    }

}
