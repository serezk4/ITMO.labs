package org.serezka.parser.parser;


import org.serezka.parser.formats.XML;
import org.serezka.parser.formats.YAML;

import java.util.*;

public class YAMLtoXMLConverter implements Converter<XML, YAML> {
    @Override
    public XML convert(YAML yaml) {
        return new XML(mapToXml(parse(yaml.get())));
    }

    private static String mapToXml(Data dataset) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + helper(dataset.getValue(), dataset.getKey()).replaceAll("=\\[]|\\[|]", "");
    }

    private static String helper(List<Data> dataset, String parent) {
        StringBuilder xmlBuilder = new StringBuilder();

        for (Data data : dataset) {
            String key = data.getKey();
            List<Data> value = data.getValue();

            if (key.isEmpty() && value.isEmpty()) continue;

            if (!value.isEmpty() && value.size() > 1) {
                xmlBuilder.append(helper(value, key));
            } else {
                xmlBuilder.append("  <").append(key).append(">").append(value).append("</").append(key).append(">\n");
            }
        }
        return "<" + parent + ">\n" + xmlBuilder + "</" + parent + ">\n";
    }

    public static class Data {
        private final String key;
        private List<Data> value = Collections.emptyList();

        public Data(String key) {
            this.key = key;
        }

        public Data(String key, Data value) {
            this.key = key;
            this.value = new ArrayList<>(List.of(value));
        }

        public Data(String key, List<Data> value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public List<Data> getValue() {
            return this.value;
        }

        public void add(Data data) {
            this.value.add(data);
        }

        public void addAll(List<Data> dataset) {
            this.value.addAll(dataset);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public static Data parse(String yaml) {
        if (yaml.isEmpty()) return new Data("");

        // append raw data
        yaml = "body\n" + yaml+  "\n ";

        // init storage
        Stack<Integer> indentations = new Stack<>();
        Stack<Data> data = new Stack<>();

        indentations.push(Integer.MIN_VALUE);

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


            if (indentation >= indentations.peek()) {
                data.push(new Data(key, new Data(value)));
                indentations.push(indentation);

                continue;
            }

            if (indentation < indentations.peek()) {
                List<Data> buffer = new ArrayList<>();
                int bufferLevel = indentations.peek();

                while (bufferLevel > indentation) {
                    buffer.add(data.pop());
                    indentations.pop();

                    if (indentations.peek() != bufferLevel) {
                        Collections.reverse(buffer);
                        data.peek().addAll(buffer);
                        buffer.clear();
                        bufferLevel = indentations.peek();
                        continue;
                    }

                }

                if (!buffer.isEmpty()) {
                    Collections.reverse(buffer);
                    data.peek().addAll(buffer);
                }

                indentations.push(indentation);
                data.push(new Data(key, new Data(value)));

                continue;
            }
        }

        List<Data> buffer = new ArrayList<>();
        int bufferLevel = indentations.pop();

        while (indentations.size() > 1) {
            buffer.add(data.pop());

            if (indentations.peek() != bufferLevel) {
                Collections.reverse(buffer);
                data.peek().addAll(buffer);

                buffer.clear();
                bufferLevel = indentations.pop();

                continue;
            }

            indentations.pop();
        }



        if (!buffer.isEmpty()) {
            Collections.reverse(buffer);
            data.peek().addAll(buffer);
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
