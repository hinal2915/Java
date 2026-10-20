import java.util.ArrayList;

public class BankService {

    private ArrayList<Account> accounts = new ArrayList<>();

    public void openAccount(Account account) {
        accounts.add(account);
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public void deposit(String accountNumber, long amount) {

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                account.deposit(amount);
                return;
            }
        }

        throw new IllegalArgumentException("Account not found");
    }

    public void withdraw(String accountNumber, long amount) {

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                account.withdraw(amount);
                return;
            }
        }

        throw new IllegalArgumentException("Account not found");
    }

    public void listAccounts() {

        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}