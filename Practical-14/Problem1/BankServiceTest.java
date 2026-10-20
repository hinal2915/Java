public class BankServiceTest {

    public static void main(String[] args) {

        BankService bank = new BankService();

        Account account = new Account("AC001", "Riya", 5000);

        // Test 1
        bank.openAccount(account);

        if (bank.getAccountCount() == 1) {
            System.out.println("Test 1 Passed");
        } else {
            System.out.println("Test 1 Failed");
        }

        // Test 2
        bank.deposit("AC001", 2000);

        if (account.getBalance() == 7000) {
            System.out.println("Test 2 Passed");
        } else {
            System.out.println("Test 2 Failed");
        }

        // Test 3
        try {

            bank.withdraw("AC001", 10000);

            System.out.println("Test 3 Failed");

        } catch (IllegalArgumentException e) {

            System.out.println("Test 3 Passed");
        }
    }
}