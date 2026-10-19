import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;
public class FolderReport {
    public static void main(String[] args) throws Exception {
        Path folder = Paths.get("myfolder");
        Path report = Paths.get("folder-report.txt");
        BufferedWriter writer =Files.newBufferedWriter(report);
        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() 
        {
            public FileVisitResult visitFile(Path file,BasicFileAttributes attributes) throws IOException 
            {
                writer.write("File Name: " + file.getFileName()+ " | Size: " + attributes.size() + " bytes"+ " | Modified: " + attributes.lastModifiedTime());
                writer.newLine();
                return FileVisitResult.CONTINUE;
            }
        });
        writer.close();
        System.out.println("Folder report created successfully.");
    }
}