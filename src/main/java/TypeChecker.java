import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//Класс проверки наличия типов данных
public class TypeChecker {
    private boolean intIn = false;
    private boolean stringIn = false;
    private boolean floatIn = false;

    public TypeChecker(List<String> inputFiles) throws IOException {
        for (String inputFile : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        if (!intIn && line.matches("-?\\d+")) {
                            intIn = true;
                        } else if (!floatIn && line.matches("-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?") && !line.matches("-?\\d+")) {
                            floatIn = true;
                        } else if (!stringIn) {
                            stringIn = true;
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    public boolean isFloatIn() {
        return floatIn;
    }

    public boolean isIntIn() {
        return intIn;
    }

    public boolean isStringIn() {
        return stringIn;
    }
}
