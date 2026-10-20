public class Validator {

    public static void validateAccountNumber(String accountNumber) {

        if (accountNumber == null ||
            accountNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Account number cannot be empty");
        }
    }

    public static void validateName(String name) {

        if (name == null ||
            name.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Owner name cannot be empty");
        }
    }

    public static void validateAmount(long amount) {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than zero");
        }
    }
}