import java.nio.file.*;
import java.io.*;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) throws Exception {

        Path[] files = {
            Paths.get("log1.txt"),
            Paths.get("log2.txt")
        };

        String keyword = "ERROR";
        int totalLines = 0;
        int keywordLines = 0;

        for (Path file : files) {

            long size = Files.size(file);
            System.out.println(file.getFileName() + " Size: " + size + " bytes");

            BufferedReader reader = Files.newBufferedReader(file);

            String line;

            while ((line = reader.readLine()) != null) {
                totalLines++;

                if (line.contains(keyword)) {
                    keywordLines++;
                }
            }

            reader.close();
        }

        System.out.println("Total Lines: " + totalLines);
        System.out.println("Lines with ERROR: " + keywordLines);
    }
}