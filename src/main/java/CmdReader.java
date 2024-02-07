import java.util.ArrayList;
import java.util.List;

//класс для работы с консолью и обработке
public class CmdReader {
    private String outFlagResult = "";
    private String prefixFlagResult = "";
    private boolean append = false;
    private boolean shortStatistic = false;
    private boolean fullStatistic = false;
    private final List<String> inputFiles = new ArrayList<>();

    public CmdReader(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-o":
                    if (i + 1 < args.length) {
                        outFlagResult = args[++i];
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefixFlagResult = args[++i];
                    }
                    break;
                case "-a":
                    append = true;
                    break;
                case "-s":
                    shortStatistic = true;
                    break;
                case "-f":
                    fullStatistic = true;
                    break;
                default:
                    inputFiles.add(args[i]);
                    break;
            }
        }
    }

    public boolean isInvalid() {
        return inputFiles.isEmpty();
    }

    public String getOutFlagResult() {
        if (outFlagResult.isEmpty()) {
            return ".";
        } else {
            return outFlagResult;
        }
    }

    public String getPrefixFlagResult() {
        return prefixFlagResult;
    }

    public boolean isAppend() {
        return append;
    }

    public boolean isShortStatistic() {
        return shortStatistic;
    }

    public boolean isFullStatistic() {
        return fullStatistic;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
