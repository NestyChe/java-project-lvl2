package hexlet.code;

import hexlet.code.formatter.Json;
import hexlet.code.formatter.Plain;
import hexlet.code.formatter.Stylish;

import java.util.Map;


public class Formatter {
    
    public static String formatter(Map<String, Object> first, Map<String, Object> second, String format) {
        switch (format) {
            case "json":
                return Json.makeResponse(first, second);
            case "plain":
                return Plain.makeResponse(first, second);
            default:
                return Stylish.makeResponse(first, second);
        }
    }
}
