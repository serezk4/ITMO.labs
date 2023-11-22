import java.io.*;
import java.util.List;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        final List<Task> tasks = List.of(new Task2(), new Task3());

        String menu = "_- MENU -_\nSelect Task:\n";
        for (int i = 0; i < tasks.size(); i++)
            menu += "\t" + String.join(" ", i + ".", tasks.get(i).getName()) + "\n";
        menu += " info - info about commands\n end - stop program\n";

        String info = "_- INFO ABOUT COMMANDS -_\n";
        for (int i = 0; i < tasks.size(); i++)
            info += "\t" + String.join(" ", i + ".", tasks.get(i).getName(), tasks.get(i).getDescription()) + "\n";

        while (true) {
            writer.append("\n").append(menu).append("[console] $ ").flush();

            String line = reader.readLine();

            if (line.matches("end")) {
                writer.append("stopping...").flush();
                break;
            }

            if (line.matches("info")) {
                writer.append(info).append("\n").flush();
                continue;
            }

            if (!line.matches("-{0,1}\\d+")) {
                writer.append("\n").append("[ERR] you did not enter a number or command").append("\n").flush();
                continue;
            }

            final int selectedTask = Integer.parseInt(line);

            if (selectedTask >= tasks.size() || selectedTask < 0) {
                writer.append("\n").append("[ERR] task doesn't exists").append("\n").flush();
                continue;
            }

            writer.append("SELECTED ").append(String.valueOf(selectedTask)).append(" > enter value: ").flush();
            tasks.get(selectedTask).run();
        }

        reader.close();
        writer.close();
    }
}
