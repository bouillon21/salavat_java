import java.util.List;

class Var10Impl implements Var10 {

    @Override
    public void runTask(List<Integer> numbers) throws LabaException {
        if (numbers.size() > maxElements) {
            throw new TooManyElementsException(maxElements);
        }
        if (numbers.stream().anyMatch(number -> number > maxValue)) {
            throw new GreaterThanAllowedException(maxValue);
        }
        if (numbers.stream().anyMatch(number -> number < minValue)) {
            throw new LessThanAllowedException(minValue);
        }

        int evenPositiveCount = 0;
        int oddPositiveCount = 0;

        // Подсчёт положительных чётных и нечётных чисел
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

        // Вывод результатов
        if (evenPositiveCount > oddPositiveCount) {
            System.out.println("Больше чётных и положительных чисел.");
        } else if (oddPositiveCount > evenPositiveCount) {
            System.out.println("Больше нечётных и положительных чисел.");
        } else {
            System.out.println("Чётных и нечётных положительных чисел поровну.");
        }
    }
}
