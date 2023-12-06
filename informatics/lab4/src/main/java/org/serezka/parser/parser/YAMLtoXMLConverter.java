package org.serezka.parser.parser;

import org.serezka.parser.formats.XML;
import org.serezka.parser.formats.YAML;

import java.util.*;
import java.util.stream.IntStream;

public class YAMLtoXMLConverter implements Converter<XML, YAML> {
    @Override
    public XML convert(YAML yaml) {
        return new XML(mapToXml(parse(yaml.get()), ""));
    }

    private static String mapToXml(Map<String, Object> map, String parent) {
        StringBuilder xmlBuilder = new StringBuilder();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                xmlBuilder.append(mapToXml((Map<String, Object>) value, key));
            } else {
                xmlBuilder.append("  <").append(key).append(">").append(value).append("</").append(key).append(">\n");
            }
        }
        return "<" + parent + ">\n" + xmlBuilder + "</" + parent + ">\n";
    }

    public Map<String, Object> parse(String yaml) {

        if (yaml.isEmpty()) return Collections.emptyMap();

        // append raw data
        yaml += "\n ";

        // init storage
        Stack<Integer> indentations = new Stack<>();
        Stack<Map<String, Object>> data = new Stack<>();

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
                data.push(new HashMap<>(Map.of(key, value)));
                indentations.push(indentation);
                continue;
            }

            if (indentation == indentations.peek()) {
                data.peek().put(key, value);
                indentations.push(indentation);

                continue;
            }

            if (indentation < indentations.peek()) {
                while (indentations.peek() > indentation) {
                    int closestIndentation = indentations.peek() - 1;
                    Map<String, Object> glued = new HashMap<>();

                    while (indentations.pop() > closestIndentation && !data.isEmpty()) {
                        IntStream.range(indentations.size() - data.peek().size() + 1, indentations.size()).forEach(i -> indentations.pop());
                        glued.putAll(data.pop());
                    }

                    if (data.isEmpty()) data.push(glued);
                    else data.peek().forEach((k, v) -> data.peek().put(k, glued));
                }

                data.push(new HashMap<>(Map.of(key, value)));
                indentations.push(indentation);

                continue;
            }
        }

        // glue all

        while (data.size() > 1 && indentations.size() > 1) {
            Map<String, Object> currData = data.pop();
            IntStream.range(indentations.size() - currData.size() + 1, indentations.size()).forEach(i -> indentations.pop());

            System.out.println(data + " " + currData + " " + indentations);
            int currIndentation = indentations.pop();

            if (indentations.peek() == currIndentation) data.peek().putAll(currData);
            else
                data.peek().entrySet()
                        .stream()
                        .filter(e -> e.getValue() instanceof Map || (e.getValue() instanceof String s && (s.isEmpty() || s.isBlank())))
                        .forEach(e -> data.peek().put(e.getKey(), currData));
        }


        while (data.size() > 1) {
            Map<String, Object> popped = data.pop();
            data.peek().putAll(popped);
        }

        // return result
        return data.pop();
    }

    private static int getIndentationLevel(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ')
            count++;
        return count / 2;
    }
}
