package hexlet.code.formatter;

import hexlet.code.Point;
import java.util.*;
import java.util.stream.Collector;

import static hexlet.code.Status.*;

public class Stylish {

    public static final Collector<Point, StringJoiner, String> FORMATTER = Collector.of(
            () -> new StringJoiner("\n", "{ ", " }"),
            Stylish::makeResult,
            StringJoiner::merge,
            StringJoiner::toString
    );


    public static void makeResult(final StringJoiner joiner, final Point point) {
        var coll = "";
        if (ADDED.equals(point.getStatus())) {
            coll = String.format("+ %s: %s", point.getKey(), point.getLastValue());
        } else if (REMOVED.equals(point.getStatus())) {
            coll = String.format("- %s: %s", point.getKey(), point.getPreviousValue());
        } else if (UNCHANGED.equals(point.getStatus())) {
            coll = String.format("  %s: %s", point.getKey(), point.getLastValue());
        } else if (CHANGED.equals(point.getStatus())) {
            coll = String.format("- %s: %s\n+ %s: %s",
                    point.getKey(), point.getPreviousValue(), point.getKey(), point.getLastValue());
        }

        joiner.add(coll);
    }
}

    /*public static String make(List<Point> list) {
        StringBuilder coll = new StringBuilder();
        for (Point point : list) {
            if (ADDED.equals(point.getStatus())) {
                coll.append(String.format(" + %s: %s", point.getKey(), point.getLastValue()));
            } else if (REMOVED.equals(point.getStatus())) {
                coll.append(String.format(" - %s: %s", point.getKey(), point.getPreviousValue()));
            } else if (UNCHANGED.equals(point.getStatus())) {
                coll.append(String.format("   %s: %s", point.getKey(), point.getLastValue()));
            } else if (CHANGED.equals(point.getStatus())) {
                coll.append(String.format(" - %s: %s + %s: %s", point.getKey(),
                 point.getPreviousValue(), point.getKey(), point.getLastValue()));
            }
        }
      return coll.toString();*/





/*return result.keySet().stream()
                .sorted(Comparator.comparingInt((String one) -> one.charAt(2)))
                .map(key -> key + ": " + result.get(key))
                .collect(Collectors.joining(" ", "{ ", " }"));
result.entrySet().stream()
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