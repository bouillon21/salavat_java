
import java.util.List;
import java.util.Objects;

class Var10Impl implements Var10 {
    private final TaskListener listener;

    Var10Impl(TaskListener listener) {
        this.listener = listener;
    }

    @Override
    public void runTask(List<Integer> numbers) throws LabaException {
        listener.onTaskStart();
        if (Objects.equals(numbers, List.of(1, 1, 1, 1, 1))) {
            listener.onConditionMatched("Лист входных данных совпадает с 1, 1, 1, 1, 1");
        }

        if (numbers.size() > maxElements) {
            throw new TooManyElementsException(maxElements, listener);
        }
        if (numbers.stream().anyMatch(number -> number > maxValue)) {
            throw new GreaterThanAllowedException(maxValue, listener);
        }
        if (numbers.stream().anyMatch(number -> number < minValue)) {
            throw new LessThanAllowedException(minValue, listener);
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
                listener.onCounterChange("Переменная oddPositiveCount изменила свое значение!");
            }
        }

        if (evenPositiveCount > oddPositiveCount) {
            listener.onResult("Больше чётных и положительных чисел.");
        } else if (oddPositiveCount > evenPositiveCount) {
            listener.onResult("Больше нечётных и положительных чисел.");
        } else {
            listener.onResult("Чётных и нечётных положительных чисел поровну.");
        }
    }
}
