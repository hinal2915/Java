import java.util.Scanner;

public class MiniBank {

    public static void main(String[] args) {

        BankService bank = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("===== MiniBank =====");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. List Accounts");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter account number: ");
                    String number = scanner.next();

                    System.out.print("Enter owner name: ");
                    String name = scanner.next();

                    System.out.print("Enter opening balance: ");
                    long balance = scanner.nextLong();

                    Account account =
                            new Account(number, name, balance);

                    bank.openAccount(account);

                    System.out.println("Account opened successfully.");
                    break;

                case 2:

                    System.out.print("Enter account number: ");
                    String depositNumber = scanner.next();

                    System.out.print("Enter amount: ");
                    long depositAmount = scanner.nextLong();

                    try {
                        bank.deposit(depositNumber, depositAmount);
                        System.out.println("Deposit successful.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    System.out.print("Enter account number: ");
                    String withdrawNumber = scanner.next();

                    System.out.print("Enter amount: ");
                    long withdrawAmount = scanner.nextLong();

                    try {
                        bank.withdraw(withdrawNumber, withdrawAmount);
                        System.out.println("Withdrawal successful.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    bank.listAccounts();
                    break;

                case 5:

                    System.out.println("Thank you for using MiniBank.");
                    scanner.close();
                    return;

                default:

                    System.out.println("Invalid choice.");
            }
        }
    }
}