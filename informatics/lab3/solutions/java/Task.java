import java.io.*;

public abstract class Task {
    private final String name;
    private final String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run();

    public abstract String operate(String val);

    protected String read() {
        try {
            StringBuilder input = new StringBuilder();
            String line;
            while (!(line = Main.reader.readLine()).matches("end|к|e"))
                input.append(line).append(" ");

            return input.toString();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "READ_ERROR";
        }
    }

    protected void write(String str) {
        try {
            Main.writer.append(str).flush();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
