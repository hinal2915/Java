import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
class Account implements Serializable 
{
    private static final long serialVersionUID = 1L;
    String accountNumber;
    String ownerName;
    long balance;
    Account(String accountNumber, String ownerName, long balance) 
    {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public String toString() 
    {
        return accountNumber + " | " + ownerName + " | Balance: " + balance;
    }
}
class Customer implements Serializable 
{
    private static final long serialVersionUID = 1L;
    String name;
    Customer(String name) 
    {
        this.name = name;
    }
}
class StatePersister 
{
    public static void save(Account[] accounts, Path file) throws Exception 
    {
        ObjectOutputStream out =new ObjectOutputStream(Files.newOutputStream(file));
        out.writeObject(accounts);
        out.close();
    }
    public static Account[] load(Path file) throws Exception 
    {
        ObjectInputStream in =new ObjectInputStream(Files.newInputStream(file));
        Account[] accounts = (Account[]) in.readObject();
        in.close();
        return accounts;
    }
}
class TransactionLog 
{
    public static void append(Path file, String line) throws Exception 
    {
        Files.write(file,(line + System.lineSeparator()).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }
}
class ReportGenerator 
{
    public static void generate(Path folder, Path report) throws Exception 
    {
        long deposits = 0;
        long withdrawals = 0;
        StringBuilder result = new StringBuilder();
        try (DirectoryStream<Path> stream =Files.newDirectoryStream(folder)) 
        {
            for (Path file : stream) 
            {
                if (Files.isRegularFile(file)) 
                {
                    BufferedReader reader =Files.newBufferedReader(file);
                    String line;
                    while ((line = reader.readLine()) != null) 
                    {
                        String[] parts = line.split(" ");
                        if (parts.length == 2) 
                        {
                            long amount = Long.parseLong(parts[1]);
                            if (parts[0].equals("DEPOSIT")) 
                            {
                                deposits += amount;
                            }
                            if (parts[0].equals("WITHDRAW")) 
                            {
                                withdrawals += amount;
                            }
                        }
                    }
                    reader.close();
                    BasicFileAttributes a =Files.readAttributes(file,BasicFileAttributes.class);
                    result.append(file.getFileName()).append(" Size: ").append(a.size()).append(" Last Modified: ").append(a.lastModifiedTime()).append("\n");
                }
            }
        }
        result.append("Total Deposits: ").append(deposits).append("\n");
        result.append("Total Withdrawals: ").append(withdrawals).append("\n");
        Files.write(report,result.toString().getBytes(),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println(result);
    }
}
public class MiniBank 
{
    public static void main(String[] args) throws Exception 
    {
        Account[] accounts = 
        {
            new Account("AC001", "Riya", 5000),
            new Account("AC002", "Krisha", 8000)
        };
        Path saveFile = Paths.get("accounts.dat");
        StatePersister.save(accounts, saveFile);
        Account[] loaded = StatePersister.load(saveFile);
        System.out.println("Loaded Accounts:");
        for (Account a : loaded) 
        {
            System.out.println(a);
        }
        Path logs = Paths.get("logs");
        Files.createDirectories(logs);
        Path logFile = logs.resolve("transactions.txt");
        TransactionLog.append(logFile, "DEPOSIT 1000");
        TransactionLog.append(logFile, "WITHDRAW 500");
        TransactionLog.append(logFile, "DEPOSIT 2000");
        TransactionLog.append(logFile, "WITHDRAW 300");
        System.out.println("\n----- Report -----");
        ReportGenerator.generate(logs,Paths.get("report.txt"));
    }
}