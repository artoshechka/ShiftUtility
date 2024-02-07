import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            //Получаем содержимое командной строки
            CmdReader cmdArgs = new CmdReader(args);

            //Проверяем аргументы команды
            if (cmdArgs.isInvalid()) {
                System.out.println("invalid utility start command");
                return;
            }

            //Получаем аргументы из командной строки
            String outPath = cmdArgs.getOutFlagResult();
            String prefix = cmdArgs.getPrefixFlagResult();
            boolean append = cmdArgs.isAppend();
            boolean shortStatistic = cmdArgs.isShortStatistic();
            boolean fullStatistic = cmdArgs.isFullStatistic();
            List<String> inputFiles = cmdArgs.getInputFiles();

            //Проверка на наличие типов данны
            TypeChecker types = new TypeChecker(inputFiles);
            boolean floatIn = types.isFloatIn();
            boolean intIn = types.isIntIn();
            boolean stringIn = types.isStringIn();

            //создаем файлы для вывода
            File floatsFile = null;
            File integersFile = null;
            File stringsFile = null;

            if (floatIn) {
                floatsFile = new File(outPath, prefix + "floats.txt");
                try {
                    if (floatsFile.createNewFile()) {
                        System.out.println("Floats file created");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating floats file: " + e.getMessage());
                }
            }

            if (intIn) {
                integersFile = new File(outPath, prefix + "integers.txt");
                try {
                    // создание файла
                    if (integersFile.createNewFile()) {
                        System.out.println("Integers file created");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating integers file: " + e.getMessage());
                }
            }

            if (stringIn) {
                stringsFile = new File(outPath, prefix + "strings.txt");
                try {
                    // создание файла
                    if (stringsFile.createNewFile()) {
                        System.out.println("Strings file created");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating strings file: " + e.getMessage());
                }
            }

            //Распределение по файлам
            Sorting.sorting(inputFiles, floatsFile, integersFile, stringsFile, floatIn, intIn, stringIn, append);

            //Вывод статисики
            if (shortStatistic || fullStatistic) {
                Statistics.printStatistics(integersFile, floatsFile, stringsFile, shortStatistic, fullStatistic, intIn, floatIn, stringIn);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
