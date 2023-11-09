import java.util.Map;

public class Task3 {
    public static void main(String[] args) {
        Map<String, String> tests = Map.of(
                "20 + 22 = 42", "1593 + 1929 = 7049",
                "20, 20", "20, 1593",
                "20 20 fewefwef 20 20", "1593 1593 fewefwef 1593 1593",
                "22 42 _ f we 20", "1929 7049 _ f we 1593"
        );



        tests.entrySet().stream()
                .map(test -> operate(test.getKey()).equals(test.getValue()))
                .forEach(System.out::println);
    }

    private static String operate(String val) {
        StringBuilder result = new StringBuilder();
        for (String item : val.split(" ")) {
            if (!item.matches("^\\d+$")) {
                result.append(item).append(" ");
                continue;
            }

            result.append(function(Integer.parseInt(item))).append(" ");
        }

        return result.toString().trim();
    }

    private static int function(int x) {
        return (int) (4 * Math.pow(x, 2) - 7);
    }
}
