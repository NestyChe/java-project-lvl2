package hexlet.code;

import hexlet.code.formatter.Json;
import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.StringJoiner;
import java.util.stream.Collector;


public class Formatter {

    public static Collector<Point, StringJoiner, String> formatter(String format) {
        switch (format) {
            case "json":
                return Json.FORMATTER;
            case "plain":
                return Plain.FORMATTER;
            default:
                return Stylish.FORMATTER;
        }
    }
}
