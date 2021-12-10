package hexlet.code.formatter;

import hexlet.code.Point;

import java.util.*;
import java.util.stream.Collector;

import static hexlet.code.Status.*;
import static hexlet.code.Status.CHANGED;

public class Plain {

    public static final Collector<Point, StringJoiner, String> FORMATTER = Collector.of(
            () -> new StringJoiner("\n"),
            Plain::makeResult,
            StringJoiner::merge,
            StringJoiner::toString
    );

    public static void makeResult(final StringJoiner joiner, final Point point) {
        var coll = "";
        if (UNCHANGED.equals(point.getStatus())) {
            return;
        } else if (ADDED.equals(point.getStatus())) {
            coll = String.format("Property '%s' was added with value: %s",
                    point.getKey(), handlerComplex(point.getLastValue()));
        } else if (REMOVED.equals(point.getStatus())) {
            coll = String.format("Property '%s' was removed", point.getKey());
        } else if (CHANGED.equals(point.getStatus())) {
            coll = String.format("Property '%s' was updated. From %s to %s",
                    point.getKey(), handlerComplex(point.getPreviousValue()), handlerComplex(point.getLastValue()));
        }

        joiner.add(coll);
    }

    public static Object handlerComplex(Object point) {
        return (point instanceof ArrayList || point instanceof Map) ? "[complex value]" : point;
    }
}

