import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Statistics {
    public static void printStatistics(File integersFile, File floatsFile, File stringsFile, boolean shortStatistic, boolean fullStatistic, boolean isInt, boolean isFloat, boolean isString) {

        StatsCollector integersStats = new StatsCollector();
        StatsCollector floatsStats = new StatsCollector();
        StatsCollector stringsStats = new StatsCollector();

        processFile(integersFile, integersStats, isInt);
        processFile(floatsFile, floatsStats, isFloat);
        processFile(stringsFile, stringsStats, isString);

        if (shortStatistic) {
            printShortStatistics(integersStats, floatsStats, stringsStats, isInt, isFloat, isString);
        }
        if (fullStatistic) {
            printFullStatistics(integersStats, floatsStats, stringsStats, isInt, isFloat, isString);
        }
    }

    //Вызываем обработку статистики по файлу
    private static void processFile(File file, StatsCollector collector, boolean shouldProcess) {
        if (shouldProcess) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    collector.collect(line);
                }
            } catch (IOException e) {
                // Пропускаем ошибку, если файл не существует или не удалось прочитать
            }
        }
    }

    //выводим короткую статистику
    private static void printShortStatistics(StatsCollector integersStats, StatsCollector floatsStats, StatsCollector stringsStats, boolean isInt, boolean isFloat, boolean isString) {
        if (isInt) {
            System.out.println("Integers count: " + integersStats.getCount());
        }
        if (isFloat) {
            System.out.println("Floats count: " + floatsStats.getCount());
        }
        if (isString) {
            System.out.println("Strings count: " + stringsStats.getCount());
        }
    }

    //выводим полную статистику
    private static void printFullStatistics(StatsCollector integersStats, StatsCollector floatsStats, StatsCollector stringsStats, boolean isInt, boolean isFloat, boolean isString) {
        if (isInt) {
            System.out.println("Integers count: " + integersStats.getCount());
            System.out.println("Integers Min: " + integersStats.getMinInt());
            System.out.println("Integers Max: " + integersStats.getMaxInt());
            System.out.println("Integers Sum: " + integersStats.getSumInt());
            System.out.println("Integers Average: " + integersStats.getAverageInt());
        }

        if (isFloat) {
            System.out.println("Floats count: " + floatsStats.getCount());
            System.out.println("Floats Min: " + floatsStats.getMinFloat());
            System.out.println("Floats Max: " + floatsStats.getMaxFloat());
            System.out.println("Floats Sum: " + floatsStats.getSumFloat());
            System.out.println("Floats Average: " + floatsStats.getAverageFloat());
        }

        if (isString) {
            System.out.println("Strings count: " + stringsStats.getCount());
            System.out.println("Shortest String Length: " + stringsStats.getMinLength());
            System.out.println("Longest String Length: " + stringsStats.getMaxLength());
        }
    }

    private static class StatsCollector {
        private double minFloat;
        private double maxFloat;
        private double sumFloat;
        private int count;
        private int minInt;
        private int maxInt;
        private long sumInt;
        private int minLength;
        private int maxLength;

        public StatsCollector() {
            this.count = 0;
            this.minInt = Integer.MAX_VALUE;
            this.maxInt = Integer.MIN_VALUE;
            this.sumInt = 0;
            this.minFloat = Double.MAX_VALUE;
            this.maxFloat = Double.MIN_VALUE;
            this.sumFloat = 0;
            this.minLength = Integer.MAX_VALUE;
            this.maxLength = Integer.MIN_VALUE;
        }

        public void collect(String line) {
            count++;
            if (line.length() < minLength) {
                minLength = line.length();
            }
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
            if (line.matches("-?\\d+")) {
                int value = Integer.parseInt(line);
                minInt = Math.min(minInt, value);
                maxInt = Math.max(maxInt, value);
                sumInt += value;
            } else if (line.matches("-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?") && !line.matches("-?\\d+")) {
                double value = Double.parseDouble(line);
                minFloat = Math.min(minFloat, value);
                maxFloat = Math.max(maxFloat, value);
                sumFloat += value;

            }

        }

        public int getCount() {
            return count;
        }

        public int getMinInt() {
            return minInt;
        }

        public int getMaxInt() {
            return maxInt;
        }

        public long getSumInt() {
            return sumInt;
        }

        public double getMinFloat() {
            return minFloat;
        }

        public double getMaxFloat() {
            return maxFloat;
        }

        public double getSumFloat() {
            return maxFloat;
        }

        public double getAverageInt() {
            return count > 0 ? (double) sumInt / count : 0;
        }

        public double getAverageFloat() {
            return count > 0 ? sumFloat / count : 0;
        }

        public int getMinLength() {
            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }

        public int getMaxLength() {
            return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
        }
    }
}
