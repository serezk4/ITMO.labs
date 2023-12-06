import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class YAMLParser {

    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\serezk4\\IdeaProjects\\ITMO.labs\\programming\\ITMO.labs\\informatics\\lab4\\src\\main\\resources\\saturday.yaml";
            String yamlContent = readFile(filePath);
            long start = System.currentTimeMillis();
            Map<String, Object> yamlMap = YAMLParser.parse(yamlContent);
            System.out.println(System.currentTimeMillis() - start);
            System.out.println(yamlMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }


    public static class Data {
        private final String key;
        private Object value = "";

        public Data(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Data setValue(Object value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public static List<Data> parse(String yaml) {
        if (yaml.isEmpty()) return Collections.emptyList();

        // append raw data
        yaml += "\n ";

        // init storage
        Stack<Integer> indentations = new Stack<>();
        Stack<List<Data>> data = new Stack<>();

        indentations.push(0);

        // init cache
        String cachedKey = "";
        int cachedIndentation = -1;
        StringBuilder cachedValue = new StringBuilder();
        boolean nextLine = false, block = false;

        // parse
        StringTokenizer tokenizer = new StringTokenizer(yaml, "\n", false);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (token.contains("#")) token = token.substring(0, token.indexOf('#'));

            if ((token.isBlank() || token.isEmpty()) && block) {
                token = String.join(":", cachedKey, cachedValue.toString());

                cachedKey = "";
                cachedValue = new StringBuilder();

                block = false;
            }

            if (block) {
                cachedValue.append(token.trim()).append(" ").append(nextLine ? "\n" : "");
                continue;
            }

            if ((token.isBlank() || token.isEmpty())) continue;

            final int indentation = cachedIndentation != -1 ? cachedIndentation : getIndentationLevel(token);
            String[] split = token.split(":", 2);

            if (split.length == 0)
                throw new IllegalArgumentException(token);

            String key = split[0].trim(), value = split.length == 2 ? split[1].trim() : "";

            if (value.startsWith(">")) {
                block = true;
                nextLine = !value.matches(" *>-.*");

                cachedKey = key;
                cachedIndentation = indentation;

                cachedValue.append(value.replaceAll("( *>-)|( *>)", "").trim()).append(" ").append(nextLine ? "\n" : "");

                continue;
            }

            if (indentation > indentations.peek() || (indentation == indentations.peek() && data.isEmpty())) {
                data.push(new ArrayList<>(List.of(new Data(key, value))));
                indentations.push(indentation);
                continue;
            }

            if (indentation == indentations.peek()) {
                data.peek().add(new Data(key, value));
                indentations.push(indentation);

                continue;
            }

            if (indentation < indentations.peek()) {
                while (indentations.peek() > indentation) {
                    int closestIndentation = indentations.peek() - 1;
                    List<Data> glued = new ArrayList<>();

                    while (indentations.pop() > closestIndentation && !data.isEmpty()) {
                        IntStream.range(indentations.size() - data.peek().size() + 1, indentations.size()).forEach(i -> indentations.pop());
                        glued.addAll(data.pop());
                    }

                    if (data.isEmpty()) data.push(glued);
                    else data.peek().stream()
                            .filter(currentData -> currentData.getValue() instanceof Map || (currentData.getValue() instanceof String str && (str.isBlank() || str.isEmpty())))
                            .forEach(filteredData -> filteredData.setValue(glued));
                }

                data.push(new ArrayList<>(List.of(new Data(key, value))));
                indentations.push(indentation);

                continue;
            }
        }

        // glue all

        while (data.size() > 1 && indentations.size() > 1) {
            List<Data> currData = data.pop();
            IntStream.range(indentations.size() - currData.size() + 1, indentations.size()).forEach(i -> indentations.pop());

            int currIndentation = indentations.pop();

            if (indentations.peek() == currIndentation) data.peek().addAll(currData);
            else data.peek()
                    .stream()
                    .filter(currentData -> currentData.getValue() instanceof Map || (currentData.getValue() instanceof String str && (str.isBlank() || str.isEmpty())))
                    .forEach(filteredData -> filteredData.setValue(currData));
        }

        while (data.size() > 1) {
            List<Data> popped = data.pop();
            data.peek().addAll(popped);
        }

        // return result
        return data.get(0);
    }

    private static int getIndentationLevel(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ')
            count++;
        return count / 2;
    }
}

