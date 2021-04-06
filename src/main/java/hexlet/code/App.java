package hexlet.code;

import picocli.CommandLine;
import static picocli.CommandLine.*;
import static picocli.CommandLine.Option;
import java.io.File;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App {

    @Parameters(description = "path to first file", paramLabel = "filepath1")
    private File file1;

    @Parameters(description = "path to second file", paramLabel = "filepath2")
    private File file2;


    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private File format;

    @Option(names = {"-h", "--help"},  usageHelp = true, description = "Show this help message and exit.")
    private boolean printHelp;

    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean printVersion;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
