public class Transaction {

    private String accountNumber;
    private long amount;

    public Transaction(String accountNumber, long amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public long getAmount() {
        return amount;
    }
}