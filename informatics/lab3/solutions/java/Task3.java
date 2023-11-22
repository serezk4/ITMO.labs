import java.util.Arrays;
import java.util.stream.Collectors;

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
        return Arrays.stream(val.split(" "))
                .map(String::trim)
                .filter(token -> token.matches("[^(кКрРаА)]*[Кк][^(кКрРаА)][Рр][^(кКрРаА)][Аа][^(кКрРаА)]*"))
                .map(token -> token + "\n").collect(Collectors.joining());
    }
}
