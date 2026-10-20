import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StatePersister persister =
                new StatePersister();

        Repository repository =
                persister.load();

        BankService bank =
                new BankService(repository);

        TransactionProcessor processor =
                new TransactionProcessor(bank);

        ReportGenerator report =
                new ReportGenerator();

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("===== MiniBank =====");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Batch Deposits");
            System.out.println("8. Generate Report");
            System.out.println("9. Save Accounts");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:

                        System.out.print(
                                "Account Number: ");
                        String accNo = sc.nextLine();

                        System.out.print(
                                "Owner Name: ");
                        String name = sc.nextLine();

                        System.out.print(
                                "Opening Balance: ");
                        long balance = sc.nextLong();
                        sc.nextLine();

                        bank.openAccount(
                                accNo,
                                name,
                                balance);

                        System.out.println(
                                "Account opened.");

                        break;

                    case 2:

                        System.out.print(
                                "Account Number: ");
                        String depositAcc =
                                sc.nextLine();

                        System.out.print(
                                "Amount: ");
                        long depositAmount =
                                sc.nextLong();
                        sc.nextLine();

                        bank.deposit(
                                depositAcc,
                                depositAmount);

                        System.out.println(
                                "Deposit successful.");

                        break;

                    case 3:

                        System.out.print(
                                "Account Number: ");
                        String withdrawAcc =
                                sc.nextLine();

                        System.out.print(
                                "Amount: ");
                        long withdrawAmount =
                                sc.nextLong();
                        sc.nextLine();

                        bank.withdraw(
                                withdrawAcc,
                                withdrawAmount);

                        System.out.println(
                                "Withdrawal successful.");

                        break;

                    case 4:

                        System.out.print(
                                "From Account: ");
                        String from =
                                sc.nextLine();

                        System.out.print(
                                "To Account: ");
                        String to =
                                sc.nextLine();

                        System.out.print(
                                "Amount: ");
                        long transferAmount =
                                sc.nextLong();
                        sc.nextLine();

                        bank.transfer(
                                from,
                                to,
                                transferAmount);

                        System.out.println(
                                "Transfer successful.");

                        break;

                    case 5:

                        System.out.print(
                                "Account Number: ");
                        String statementAcc =
                                sc.nextLine();

                        bank.statement(statementAcc);

                        break;

                    case 6:

                        bank.listAccounts();

                        break;

                    case 7:

                        System.out.print(
                                "Account Number: ");
                        String batchAccount =
                                sc.nextLine();

                        System.out.print(
                                "Number of deposits: ");
                        int count =
                                sc.nextInt();

                        List<Transaction> transactions =
                                new ArrayList<>();

                        for (int i = 1;
                             i <= count;
                             i++) {

                            System.out.print(
                                    "Deposit " + i +
                                    " amount: ");

                            long amount =
                                    sc.nextLong();

                            transactions.add(
                                    new Transaction(
                                            batchAccount,
                                            amount));
                        }

                        sc.nextLine();

                        processor.processDeposits(
                                transactions);

                        System.out.println(
                                "Batch completed.");

                        break;

                    case 8:

                        report.generate(repository);

                        break;

                    case 9:

                        persister.save(repository);

                        break;

                    case 10:

                        persister.save(repository);

                        System.out.println(
                                "Thank you for using MiniBank.");

                        running = false;

                        break;

                    default:

                        System.out.println(
                                "Invalid choice.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}