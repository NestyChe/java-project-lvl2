package hexlet.code;
import picocli.CommandLine;
import java.util.concurrent.Callable;
import static picocli.CommandLine.*;
import static picocli.CommandLine.Option;


@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filePath2;


    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format")
    private String format;

    @Option(names = {"-h", "--help"},  usageHelp = true, description = "Show this help message and exit.")
    private boolean printHelp;

    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean printVersion;

    public static void main(String[] args) {
        CommandLine exitCode = new CommandLine(new App());
        exitCode.execute(args);
        String result = exitCode.getExecutionResult();
        System.out.println(result);
    }

    @Override
    public String call() throws Exception {
        return Differ.generate(filePath1, filePath2, format);
    }
}
