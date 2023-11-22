import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 extends Task{
    public Task1() {
        super("task #1", """
                Программа должна считать число смайликов определённого вида (вид смайлика описан в таблице
                вариантов) в предложенном тексте. Все смайлики имеют такую структуру:
                [глаза][нос][рот].
                """);
    }

//    2 2 4
//    X -{\

    @Override
    public void run() {
        write(operate(read()));
    }

    @Override
    public String operate(String val) {
        Matcher matcher = Pattern.compile("X-\\{\\.{0}").matcher(val);
        int count = 0;

        while (matcher.find()) count++;

        return String.valueOf(count);
    }
}
