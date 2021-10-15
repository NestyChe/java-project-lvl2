package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> first = getContent(filePath1);
        Map<String, Object> second = getContent(filePath2);
        return Formatter.formatter(first, second, formatName);
    }


    public static Map<String, Object> getContent(String path) throws IOException {
        String file = Files.readString(getFileByName(path)); //Paths.get().toAbsolutePath()
        String type = path.substring(path.indexOf('.') + 1);
        return Parser.fileToMap(type, file);
    }

    //???????
    public static Path getFileByName(String name) {
        File f = new File(Differ.class.getClassLoader().getResource(name).getFile());
        return f.toPath();
        /*ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(name);
        return new File(resource.toURI()).toPath();*/
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
