package hexlet.code.formatter;

import java.util.*;
import java.util.stream.Collectors;

public class Stylish {

    public static String makeResponse(Map<String, Object> first, Map<String, Object> second) {
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
        return result.keySet().stream()
                .sorted(Comparator.comparingInt((String one) -> one.charAt(2)))
                .map(key -> key + ": " + result.get(key))
                .collect(Collectors.joining(" ", "{ ", " }"));
    }
}
/*result.entrySet().stream()
                .sorted()//Map.Entry.comparingByKey(Comparator.comparingInt((String one) -> one.charAt(2)))
                .collect(LinkedHashMap::new,
                        (a, e) -> a.put(e.getKey(), e.getValue()),
                        LinkedHashMap::putAll)
                .toString().replace("=", ": "));
        return result.keySet().stream()
                .sorted(Comparator.comparingInt((String one) -> one.charAt(2)))
                .map(key -> key + ": " + result.get(key))
                .collect(Collectors.joining(" ","{ ", " }")));
        first.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().equals(second.get(e.getKey()))));*/