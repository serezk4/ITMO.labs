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
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            StringBuilder input = new StringBuilder();
            String line;
            while (!(line = reader.readLine()).matches("end|к|e"))
                input.append(line).append(" ");

            reader.close();

            return input.toString();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "READ_ERROR";
        }
    }

    protected void write(String str) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            writer.append(str).flush();
            writer.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
