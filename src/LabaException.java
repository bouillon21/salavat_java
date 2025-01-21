
abstract class LabaException extends Exception {
    public LabaException(String msg, TaskListener listener) {
        super("Ошибка: " + msg);
        listener.onError(msg);
    }
}

class GreaterThanAllowedException extends LabaException {
    public GreaterThanAllowedException(int maxValue, TaskListener listener) {
        super("Элемент больше, чем разрешённое число " + maxValue, listener);
    }
}

class TooManyElementsException extends LabaException {
    public TooManyElementsException(int max, TaskListener listener) {
        super("Число элементов больше максимального предела " + max, listener);
    }
}

class LessThanAllowedException extends LabaException {
    public LessThanAllowedException(int minValue, TaskListener listener) {
        super("Элемент меньше, чем разрешённое число " + minValue, listener);
    }
}
