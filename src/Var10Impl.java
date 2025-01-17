
import java.util.List;
import java.util.Objects;

class Var10Impl implements Var10 {
    private final EventLogger logger;

    public Var10Impl(EventLogger logger) {
        this.logger = logger;
    }

    @Override
    public void runTask(List<Integer> numbers) throws LabaException {
        logger.logEvent("Начало обработки задачи");
        if (Objects.equals(numbers, List.of(1, 1, 1, 1, 1))) {
            logger.logEvent("Лист входных данных совпадает с 1, 1, 1, 1, 1");
        }

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

        for (int number : numbers) {
            if (number <= 0) {
                continue;
            }
            if (number % 2 == 0) {
                evenPositiveCount++;
            } else {
                oddPositiveCount++;
                logger.logEvent("Переменная oddPositiveCount изменила свое значение!");
            }
        }

        if (evenPositiveCount > oddPositiveCount) {
            logger.logEvent("Больше чётных и положительных чисел.");
        } else if (oddPositiveCount > evenPositiveCount) {
            logger.logEvent("Больше нечётных и положительных чисел.");
        } else {
            logger.logEvent("Чётных и нечётных положительных чисел поровну.");
        }
    }
}
