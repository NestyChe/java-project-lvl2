package hexlet.code;

import picocli.CommandLine;

import static picocli.CommandLine.*;
import static picocli.CommandLine.Option;
import java.util.concurrent.Callable;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file")
    private String filePath1 = "/home/main/java-project-lvl2/first.json";

    @Parameters(index = "1", description = "path to second file")
    private String filePath2 = "/home/main/java-project-lvl2/second.json";


    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @Option(names = {"-h", "--help"},  usageHelp = true, description = "Show this help message and exit.")
    private boolean printHelp;

    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean printVersion;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public String call() throws Exception {
        System.out.println(Differ.generate(filePath1, filePath2));
        return Differ.generate(filePath1, filePath2);
    }
}
