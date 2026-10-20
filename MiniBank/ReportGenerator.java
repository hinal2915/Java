import java.io.FileWriter;
import java.io.PrintWriter;
public class ReportGenerator 
{
    public void generate(Repository repository) 
    {
        try (PrintWriter writer =new PrintWriter(new FileWriter("bank-report.txt"))) 
        {
            writer.println("===== MiniBank Report =====");
            for (Account account : repository.findAll()) 
            {
                writer.println(account);
            }
            writer.println();
            writer.println("Total Accounts: " + repository.size());
            writer.close();
            System.out.println("Report generated: bank-report.txt");
        } 
        catch (Exception e) 
        {
            System.out.println("Report error: " + e.getMessage());
        }
    }
}