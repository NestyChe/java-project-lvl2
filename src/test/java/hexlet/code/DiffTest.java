package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiffTest {

    @Test
    public void stylishFormatterTest() throws IOException {
        generateDiffTest("stylish");
    }

    @Test
    public void plainFormatterTest() throws IOException {
        generateDiffTest("plain");
    }

    @Test
    public void  jsonFormatterTest() throws IOException {
        generateDiffTest("json");
    }

    public void generateDiffTest(String formatTest) throws IOException {
        String expectSimple = expected(formatTest + "_simple");
        String expectNested = expected(formatTest + "_nested");
        for (String[] file : files()) {
            String first = getFile(file[0]).getPath();
            String second = getFile(file[1]).getPath();
            if (file[0].startsWith("simple")) {
                assertEquals(expectSimple, Differ.generate(first, second, formatTest));
            } else {
                assertEquals(expectNested,  Differ.generate(first, second, formatTest));
            }
        }
    }

    public static File getFile(String name) {
        return new File(Differ.class.getClassLoader().getResource(name).getFile());

    }

    public String expected(String name) throws IOException {
        String path = "expectedfile/";
        return new BufferedReader(new FileReader(getFile(path + name)))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public List<String[]> files() {
        return Arrays.asList(
                new String[]{
                    "simple_first.json",
                    "simple_second.json"},
                new String[]{
                    "simple_first.yaml",
                    "simple_second.yaml"},
                new String[]{
                    "nested_first.json",
                    "nested_second.json"},
                new String[]{
                    "nested_first.yaml",
                    "nested_second.yaml"}
        );
    }
}