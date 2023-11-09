import java.util.Map;

public class Task2 {
    public static void main(String[] args) {
        Map<String, Boolean> tests = Map.of(
                "ВТ ахаха ахахха ИТМО", true,
                "ВТ ИТМО", true,
                "ахахха хахахах Итмо", false,
                "В ИТМО", false,
                "бобобо", false
        );

        tests.entrySet().stream()
                .map(test -> operate(test.getKey()).equals(test.getValue()))
                .forEach(System.out::println);
    }

    private static String operate(String val) {


        return " ";
    }
}
