package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiffTest {

    @Test
    public void generateDiffTest() throws IOException {
        for (String[] d : files()) {
            assertEquals(expected(d[2]), Differ.generate(d[0], d[1],"stylish"));
        }
    }

    public String expected(String name) throws IOException {
        String path = "/home/main/java-project-lvl2/src/test/resources";
        return Files.newBufferedReader(Paths.get(path + name))
                .lines()
                .collect(Collectors.joining());
    }

    public List<String[]> files() {
        return Arrays.asList(
                new String[]{
                    "first.json",
                    "second.json",
                    "/expectedfile/json_diff"},
                new String[]{
                    "first.yaml",
                    "second.yaml",
                    "/expectedfile/yaml_diff"}
        );
    }
}