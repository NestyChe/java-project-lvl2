package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DiffTest {

    @Test
    public  void generateDiffTest() throws IOException {
        String first = "/home/main/java-project-lvl2/src/test/resources/first.json";
        String second = "/home/main/java-project-lvl2/src/test/resources/second.json";
        String pathExpectFile = "/home/main/java-project-lvl2/src/test/resources/expectedFile/json_diff";
        assertEquals(expected(pathExpectFile), Differ.generate(first, second));
    }

    public String expected(String name) throws IOException {
        return Files.newBufferedReader(Paths.get(name))
                .lines()
                .collect(Collectors.joining());
    }

    /*public Stream<String> files() {

    }*/
}