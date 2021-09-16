package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DiffTest {

    @Test
    public  void generateTest() throws IOException {
        String first = "/home/main/java-project-lvl2/src/test/resources/first.json";
        String second = "/home/main/java-project-lvl2/src/test/resources/second.json";
        Differ.generate(first, second);
    }
}