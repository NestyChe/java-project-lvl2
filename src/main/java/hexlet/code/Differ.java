package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Differ {

    public static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath();
    }

    public static Map<String, Object> readFile(Path paths) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String file = Files.readString(paths);
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    public static String diff(Map<String, Object> first, Map<String, Object> second) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> node : first.entrySet()) {
            if (second.containsKey(node.getKey()) && second.get(node.getKey()).equals(node.getValue())) {
                result.put("  " + node.getKey(), node.getValue());
            } else if (second.containsKey(node.getKey()) && !second.get(node.getKey()).equals(node.getValue())) {
                result.put("- " + node.getKey(), node.getValue());
                result.put("+ " + node.getKey(), second.get(node.getKey()));
            } else if (!second.containsKey(node.getKey())) {
                result.put("- " + node.getKey(), node.getValue());
            }
        }
        for (Map.Entry<String, Object> node : second.entrySet()) {
            if (!first.containsKey(node.getKey())) {
                result.put("+ " + node.getKey(), node.getValue());
            }
        }
        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt((String one) -> one.charAt(2))))
                .collect(LinkedHashMap::new,
                        (a, e) -> a.put(e.getKey(), e.getValue()),
                        LinkedHashMap::putAll)
                .toString().replace("=", ": ");
    }

    /*public static String diff(Map<String, Object> first, Map<String, Object> second) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Object> node : first.entrySet()) {
            if (second.containsKey(node.getKey()) && second.get(node.getKey()).equals(node.getValue())) {
                result.add(" " + node.getKey() + ": " + node.getValue());
            } else if (second.containsKey(node.getKey()) && !second.get(node.getKey()).equals(node.getValue())) {
                result.add("- " + node.getKey() + ": " + node.getValue());
                result.add("+ " + node.getKey() + ": " + second.get(node.getKey()));
            } else if (!second.containsKey(node.getKey())) {
                result.add("- " + node.getKey() + ": " + node.getValue());
            }
        }
        for (Map.Entry<String, Object> node : second.entrySet()) {
            if (!first.containsKey(node.getKey())) {
                result.add("+ " + node.getKey() + ": " + node.getValue());
            }
        }
        result.sort(Comparator.comparingInt((String one) -> one.charAt(2)));
        return result.toString();
    }*/

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> first = readFile(getPath(filePath1));
        Map<String, Object> second = readFile(getPath(filePath2));
        return "{ " + diff(first, second) + " }";
    }
}


/*public class Differ {
    public static String generate(String filePath1, String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        JsonNode file1 = objectMapper.readTree(new File(filePath1));
        JsonNode file2 = objectMapper.readTree(new File(filePath2));
        JsonNode patch = JsonDiff.asJson(file1, file2);
        String diffs = patch.toString();
        System.out.println(diffs);
        return diffs;
    }
}*/
