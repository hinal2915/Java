public class BankService {

    private Repository repository;

    public BankService(Repository repository) {
        this.repository = repository;
    }

    public void openAccount(
            String accountNumber,
            String ownerName,
            long openingBalance) {

        Validator.validateAccountNumber(accountNumber);
        Validator.validateName(ownerName);

        if (openingBalance < 0) {
            throw new InvalidAmountException(
                    "Opening balance cannot be negative");
        }

        if (repository.findById(accountNumber) != null) {
            throw new BankException("Account already exists");
        }

        Account account =
                new Account(accountNumber, ownerName, openingBalance);

        repository.save(account);
    }

    public void deposit(String accountNumber, long amount) {

        Validator.validateAmount(amount);

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found");
        }

        account.deposit(amount);
    }

    public void withdraw(String accountNumber, long amount) {

        Validator.validateAmount(amount);

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found");
        }

        account.withdraw(amount);
    }

    public void transfer(
            String fromAccount,
            String toAccount,
            long amount) {

        Validator.validateAmount(amount);

        Account from = repository.findById(fromAccount);
        Account to = repository.findById(toAccount);

        if (from == null) {
            throw new AccountNotFoundException(
                    "Source account not found");
        }

        if (to == null) {
            throw new AccountNotFoundException(
                    "Destination account not found");
        }

        synchronized (from) {
            synchronized (to) {

                if (from.getBalance() < amount) {
                    throw new InsufficientFundsException(
                            "Insufficient balance");
                }

                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public void statement(String accountNumber) {

        Account account = repository.findById(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found");
        }

        System.out.println(account);
    }

    public void listAccounts() {

        for (Account account : repository.findAll()) {
            System.out.println(account);
        }
    }
}