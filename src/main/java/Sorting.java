import java.io.*;
import java.util.List;

public class Sorting {
    public static void sorting(List<String> inputFiles, File floatsFile, File integersFile, File stringsFile, boolean isFloat, boolean isInt, boolean isString, boolean append) throws IOException {
        PrintWriter integersWriter = null;
        PrintWriter floatsWriter = null;
        PrintWriter stringsWriter = null;

        if (isInt) {
            integersWriter = new PrintWriter(new FileWriter(integersFile, append));
        }

        if (isFloat) {
            floatsWriter = new PrintWriter(new FileWriter(floatsFile, append));
        }

        if (isString) {
            stringsWriter = new PrintWriter(new FileWriter(stringsFile, append));
        }

        try {
            for (String inputFile : inputFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        try {
                            if (isInt && line.matches("-?\\d+")) {
                                integersWriter.println(line);
                            } else if (isFloat && line.matches("-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) {
                                floatsWriter.println(line);
                            } else if (isString) {
                                stringsWriter.println(line);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        } finally {
            // Закрываем все PrintWriter
            if (isInt) {
                integersWriter.close();
            }

            if (isFloat) {
                floatsWriter.close();
            }

            if (isString) {
                stringsWriter.close();
            }
        }
    }
}