import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 extends Task {
    public Task3() {
        super("task #3", """
                Необходимо выбрать три любых буквы и расстояние между ними. С помощью
                регулярного выражения нужно найти все слова (последовательность символов
                ограниченная пробелами), в которых встречаются эти буквы в заданной
                последовательности и расстояние (например, через один друг от друга).
                """);
    }

    @Override
    public void run() {
        write(operate(read()));
    }

    @Override
    public String operate(String val) {
        Matcher matcher = Pattern.compile("[Кк][^кКрРаА][Рр][^кКрРаА][Аа]").matcher(val);

        StringBuilder result = new StringBuilder();
        while (matcher.find())
            result.append(matcher.group()).append("\n");

        return result.toString();
    }
}
