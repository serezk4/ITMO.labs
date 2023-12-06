package org.serezka.parser.parser;

import lombok.Getter;
import org.serezka.parser.formats.XML;
import org.serezka.parser.formats.YAML;

import java.util.*;
import java.util.stream.IntStream;

public class YAMLtoXMLConverter implements Converter<XML, YAML> {
    @Override
    public XML convert(YAML yaml) {
        return new XML(mapToXml(parse(yaml.get()), ""));
    }

    private static String mapToXml(List<Data> dataset, String parent) {
        StringBuilder xmlBuilder = new StringBuilder();

        for (Data data : dataset) {
            String key = data.getKey();
            Object value = data.getValue();

            if (value instanceof List) {
                xmlBuilder.append(mapToXml((List<Data>) value, key));
            } else {
                xmlBuilder.append("  <").append(key).append(">").append(value).append("</").append(key).append(">\n");
            }
        }
        return "<" + parent + ">\n" + xmlBuilder + "</" + parent + ">\n";
    }

    public static class Data {
        private final String key;
        private Object value = "";

        public Data(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
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
                while (indentations.size() > 1 && indentations.peek() > indentation) {
                    int closestIndentation = indentations.peek() - 1;
                    List<Data> glued = new ArrayList<>();

                    while (!indentations.isEmpty() && indentations.pop() > closestIndentation && !data.isEmpty()) {
                        IntStream.range(indentations.size() - data.peek().size(), indentations.size()).forEach(i -> indentations.pop());
                        glued.addAll(data.pop());
                    }

                    if (data.isEmpty()) data.push(glued);
                    else data.peek().get(data.peek().size()-1).setValue(glued);
                }

                data.push(new ArrayList<>(List.of(new Data(key, value))));
                indentations.push(indentation);

                continue;
            }
        }

        // glue all
        while (data.size() > 1 && indentations.size() > 1) {
            List<Data> currData = data.pop();
            int currIndentation = indentations.pop();

            if (!indentations.isEmpty() && indentations.peek() == currIndentation) data.peek().addAll(currData);
            else data.peek().get(data.peek().size()-1).setValue(currData);
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
