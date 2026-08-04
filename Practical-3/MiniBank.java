class Customer implements Cloneable {

    private String name;
    private String email;
    private String mobile;
    private Address address;

    public Customer(String name, String email, String mobile, Address address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public static class Address {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

class Account {

    private final String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // toString()
    @Override
    public String toString() {
        return "Account No: " + accountNumber +
               ", Owner: " + ownerName +
               ", Balance: " + balance;
    }

    // equals()
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Account))
            return false;

        Account a = (Account) obj;

        return accountNumber.equals(a.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}

public class MiniBank {

    public static void main(String[] args) {

        Customer.Address addr =
                new Customer.Address("Street 1", "Navsari", "396433");

        Customer c1 = new Customer("Riya", "riya@gmail.com",
                "9876543210", addr);

        Customer c2 = c1.clone();

        Account a1 = new Account("AC001", "Rahul", 5000);
        Account a2 = new Account("AC001", "Rahul", 8000);
        Account a3 = new Account("AC002", "Priya", 6000);

        System.out.println(a1);
        System.out.println(a3);

        System.out.println("a1 equals a2: " + a1.equals(a2));
        System.out.println("a1 equals a3: " + a1.equals(a3));
        if (a1 instanceof Account) {
            System.out.println("a1 is an Account object.");
        }

        if (c1 instanceof Customer) {
            System.out.println("c1 is a Customer object.");
        }
        System.out.println("Clone City: " +
                c2.getAddress().getCity());
    }
}