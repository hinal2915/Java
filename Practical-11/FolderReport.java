import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;
public class FolderReport 
{
    public static void main(String[] args) throws Exception 
    {
        Path folder = Paths.get("myfolder");
        Path subfolder = folder.resolve("documents");
        Files.createDirectories(subfolder);
        Files.write(folder.resolve("a.txt"),"Hello from file A".getBytes());
        Files.write(folder.resolve("b.txt"),"Hello from file B".getBytes());
        Files.write(subfolder.resolve("notes.txt"),"These are some notes".getBytes());
        Files.write(subfolder.resolve("data.txt"),"Some data is stored here".getBytes());
        Path report = Paths.get("folder-report.txt");
        BufferedWriter writer =Files.newBufferedWriter(report);
        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() 
        {
            public FileVisitResult visitFile(Path file,BasicFileAttributes attributes)throws IOException 
            {
                writer.write("File Name: " + file.getFileName()+ " | Size: " + attributes.size() + " bytes"+ " | Modified: " + attributes.lastModifiedTime());
                writer.newLine();
                return FileVisitResult.CONTINUE;
            }
        });
        writer.close();
        System.out.println("Folder created successfully.");
        System.out.println("Text files created successfully.");
        System.out.println("Report created: folder-report.txt");
    }
}
