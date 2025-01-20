import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Аргуенты должны идти через запятую");
            return;
        }
        List<Integer> numbers  = Arrays.stream(args[0].split(",")).map(Integer::parseInt).toList();
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
            System.out.println("Больше чётных и положительных чисел.");
        } else if (oddPositiveCount > evenPositiveCount) {
            System.out.println("Больше нечётных и положительных чисел.");
        } else {
            System.out.println("Чётных и нечётных положительных чисел поровну.");
        }
    }
}
