import java.util.HashMap;
import java.util.Map;

public class YamlToXmlConverter {
    public static void main(String[] args) {
        String yamlData = "person:\n" +
                "  name: Nikita\n" +
                "  age: 15\n" +
                "  daun: true\n" +
                "tisha:\n" +
                "  age: 16";

        String xmlData = convertYamlToXml(yamlData);
        System.out.println(xmlData);
    }

    public static String convertYamlToXml(String yamlData) {
        Map<String, Object> yamlMap = parseYAML(yamlData);
        return mapToXml(yamlMap, "");
    }

    private static int getIndentationLevel(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ') {
            count++;
        }
        return count / 2; // Предполагается, что каждый уровень вложенности - это 2 пробела
    }

    private static Map<String, Object> parseYAML(String yamlContent) {
        Map<String, Object> yamlMap = new HashMap<>();
        String[] lines = yamlContent.split("\n");

        for (String line : lines) {
            processLine(line.trim(), yamlMap);
        }

        return yamlMap;
    }

    private static void processLine(String line, Map<String, Object> currentMap) {
        if (line.contains(":")) {
            String[] parts = line.split(":");
            String key = parts[0].trim();
            String value = parts[1].trim();

            if (value.endsWith(":")) {
                // Вложенная структура
                Map<String, Object> nestedMap = new HashMap<>();
                currentMap.put(key, nestedMap);
                processLine(value.substring(0, value.length() - 1).trim(), nestedMap);
            } else {
                currentMap.put(key, value);
            }
        }
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
}
