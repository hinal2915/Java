import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionProcessor {

    private BankService bank;

    public TransactionProcessor(BankService bank) {
        this.bank = bank;
    }

    public void processDeposits(List<Transaction> transactions) {

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        for (Transaction transaction : transactions) {

            executor.submit(() -> {

                try {

                    bank.deposit(
                            transaction.getAccountNumber(),
                            transaction.getAmount());

                } catch (Exception e) {

                    System.out.println(
                            "Transaction failed: " +
                            e.getMessage());
                }
            });
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            // Wait for all transactions to finish
        }
    }
}