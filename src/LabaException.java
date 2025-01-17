
abstract class LabaException extends Exception {
    public LabaException(String msg) {
        super("Ошибка: " + msg);
    }
}

class GreaterThanAllowedException extends LabaException {
    public GreaterThanAllowedException(int maxValue) {
        super("Элемент больше, чем разрешённое число " + maxValue);
    }
}

class TooManyElementsException extends LabaException {
    public TooManyElementsException(int max) {
        super("Число элементов больше максимального предела " + max);
    }
}

class LessThanAllowedException extends LabaException {
    public LessThanAllowedException(int minValue) {
        super("Элемент меньше, чем разрешённое число " + minValue);
    }
}
