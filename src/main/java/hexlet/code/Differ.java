package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static hexlet.code.Status.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Stream.concat;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> first = getContent(filePath1);
        Map<String, Object> second = getContent(filePath2);

        return concat(first.keySet().stream(), second.keySet().stream())
                .distinct()
                .map(pointName -> new Point(pointName,
                                first.get(pointName),
                                second.get(pointName),
                                getStatus(first, second, pointName)
                        )
                ).sorted(comparing(Point::getKey))
                .collect(Formatter.formatter(formatName));

    }


    public static Map<String, Object> getContent(String path) throws IOException {
        String file = Files.readString(Paths.get(path));
        String type = path.substring(path.indexOf('.') + 1);
        return Parser.fileToMap(type, file);
    }


    public static Status getStatus(final Map<String, Object> content1,
                                   final Map<String, Object> content2,
                                   final String pointName) {
        return !content1.containsKey(pointName) ? ADDED
                : !content2.containsKey(pointName) ? REMOVED
                : Objects.equals(content1.get(pointName), content2.get(pointName)) ? UNCHANGED
                : CHANGED;
    }
}
