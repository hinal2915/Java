import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StatePersister {

    private String fileName = "accounts.dat";

    public void save(Repository repository) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(fileName))) {

            out.writeObject(repository.findAll());

            System.out.println("Accounts saved.");

        } catch (Exception e) {

            System.out.println(
                    "Save error: " + e.getMessage());
        }
    }

    public Repository load() {

        Repository repository = new Repository();

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(fileName))) {

            java.util.List<Account> accounts =
                    (java.util.List<Account>) in.readObject();

            for (Account account : accounts) {
                repository.save(account);
            }

            System.out.println("Accounts loaded.");

        } catch (Exception e) {

            System.out.println(
                    "No previous data found. Starting new bank.");
        }

        return repository;
    }
}