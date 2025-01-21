import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

// Интерфейс для обработки различных типов событий
interface TaskListener extends EventListener {
    void onTaskStart();
    void onConditionMatched(String condition);
    void onCounterChange(String counterName);
    void onResult(String comparisonResult);
    void onError(String exception);
}

// Композитный слушатель для объединения нескольких обработчиков
class CompositeListener implements TaskListener {
    private final List<TaskListener> listeners = new ArrayList<>();

    public CompositeListener(TaskListener... listeners) {
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    public void onTaskStart() {
        listeners.forEach(TaskListener::onTaskStart);
    }

    @Override
    public void onConditionMatched(String condition) {
        listeners.forEach(l -> l.onConditionMatched(condition));
    }

    @Override
    public void onCounterChange(String counterName) {
        listeners.forEach(l -> l.onCounterChange(counterName));
    }

    @Override
    public void onResult(String comparisonResult) {
        listeners.forEach(l -> l.onResult(comparisonResult));
    }

    @Override
    public void onError(String exception) {
        listeners.forEach(l -> l.onError(exception));
    }
}

// Реализация логирования в файл
class FileLogger implements TaskListener {
    private final String logFilePath;

    public FileLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    private void log(String message) {
        try (FileWriter fw = new FileWriter(logFilePath, true);
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskStart() {
        log("Начало обработки задачи");
    }

    @Override
    public void onConditionMatched(String condition) {
        log("Сработало условие: " + condition);
    }

    @Override
    public void onCounterChange(String counterName) {
        log("Изменен счетчик: " + counterName);
    }

    @Override
    public void onResult(String comparisonResult) {
        log("Результат сравнения: " + comparisonResult);
    }

    @Override
    public void onError(String exception) {
        log("ОШИБКА: " + exception);
    }
}

// Дополнительный логгер для консоли
class ConsoleLogger implements TaskListener {
    @Override
    public void onTaskStart() {
        System.out.println("[Начало задачи]");
    }

    @Override
    public void onConditionMatched(String condition) {
        System.out.println("[Условие] " + condition);
    }

    @Override
    public void onCounterChange(String counterName) {
        System.out.println("[Счетчик] " + counterName);
    }

    @Override
    public void onResult(String comparisonResult) {
        System.out.println("[Результат] " + comparisonResult);
    }

    @Override
    public void onError(String exception) {
        System.out.println("[ОШИБКА] " + exception);
    }
}