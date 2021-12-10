package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static ObjectMapper parse(String type) {
        switch (type.toLowerCase()) {
            case "json":
                return new ObjectMapper();
            case "yaml":
                return new ObjectMapper(new YAMLFactory());
            default:
                throw new IllegalStateException("Unexpected type " + type);
        }
    }

    public static Map<String, Object> fileToMap(String type, String file) throws IOException {
        return parse(type).readValue(file, new TypeReference<>() { });
    }

}
