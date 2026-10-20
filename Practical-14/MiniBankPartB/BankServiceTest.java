import java.util.ArrayList;
import java.util.List;

public class BankServiceTest {

    public static void main(String[] args) {

        Repository repository = new Repository();

        BankService bank = new BankService(repository);

        // Test 1: Deposit increases balance

        bank.openAccount("AC001", "Riya", 5000);

        bank.deposit("AC001", 2000);

        Account account = repository.findById("AC001");

        if (account != null && account.getBalance() == 7000) {
            System.out.println("Deposit test PASSED");
        } else {
            System.out.println("Deposit test FAILED");
        }

        // Test 2: Over-limit withdrawal

        try {

            bank.withdraw("AC001", 10000);

            System.out.println("Withdrawal test FAILED");

        } catch (Exception e) {

            System.out.println("Withdrawal test PASSED");
        }

        // Test 3: Concurrent deposits

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            transactions.add(
                    new Transaction("AC001", 1000)
            );
        }

        TransactionProcessor processor =
                new TransactionProcessor(bank);

        processor.processDeposits(transactions);

        account = repository.findById("AC001");

        if (account != null && account.getBalance() == 17000) {

            System.out.println(
                    "Concurrent deposit test PASSED");

        } else {

            System.out.println(
                    "Concurrent deposit test FAILED");

            System.out.println(
                    "Final balance: " + account.getBalance());
        }
    }
}