import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(101, -2, 3, 4, 5, -2);
        try {
            new Var10Impl().runTask(numbers);
        } catch (LabaException e) {
            e.printStackTrace();
        }
    }
}
