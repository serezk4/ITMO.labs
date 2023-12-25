import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 extends Task {
    public Task2() {
        super("task #2", """
                Дан текст. Необходимо найти в нём каждый фрагмент, где сначала идёт слово «ВТ»,
                затем не более 4 слов, и после этого идёт слово «ИТМО».
                """);
    }

    @Override
    public void run()  {
        write(operate(read()));
    }

    public String operate(String val) {
        Matcher matcher = Pattern.compile("ВТ( [а-яА-Я\\w–]+){0,4} ИТМО").matcher(val);

        StringBuilder result = new StringBuilder();
        while (matcher.find())
            result.append(matcher.group()).append("\n");

        return result.toString();
    }
}