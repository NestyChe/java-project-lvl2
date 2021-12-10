package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Point;
import java.util.StringJoiner;
import java.util.stream.Collector;

public class Json {

    public static final Collector<Point, StringJoiner, String> FORMATTER = Collector.of(
            () -> new StringJoiner("\n"),
            Json::makeResult,
            StringJoiner::merge,
            StringJoiner::toString
    );

    public static void makeResult(final StringJoiner joiner, final Point point) {
        try {
            joiner.add(new ObjectMapper().writeValueAsString(point));
        } catch (JsonProcessingException ex) {
            ex.getMessage();
        }
    }

}
