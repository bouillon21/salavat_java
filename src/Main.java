import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, -2, 3, 4, 5, -6);

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
