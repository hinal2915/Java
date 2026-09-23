import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Positive {
    String message() default "must be > 0";
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLenth {
    int value();
}
@FunctionalInterface
interface WithdrawRule {
    boolean allow(Account account, long amount);
}

abstract class Account {

    @Id
    String accountNumber;

    @Positive
    long balance;

    @MaxLength(20)
    String ownerName;

    Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    abstract double interestRate();

    @Override
    public String toString() {
        return "Account: " + accountNumber +
               ", Owner: " + ownerName +
               ", Balance: " + balance;
    }
}

class SavingsAccount extends Account {

    SavingsAccount(String accountNumber, String ownerName, long balance) {
        super(accountNumber, ownerName, balance);
    }

    @Override
    double interestRate() {
        return 4.0;
    }
}
class AnnotationValidator {

    public static String[] validate(Object obj) {

        String[] errors = new String[10];
        int count = 0;

        try {

            Class<?> currentClass = obj.getClass();
            while (currentClass != null) {

                Field[] fields =
                    currentClass.getDeclaredFields();

                for (Field field : fields) {

                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Positive.class)) {

                        Positive positive =
                            field.getAnnotation(Positive.class);

                        long value = field.getLong(obj);

                        if (value <= 0) {

                            errors[count++] = field.getName() + positive.message();
                        }
                    }
                    if (field.isAnnotationPresent(MaxLength.class)) {

                        MaxLength maxLength =
                            field.getAnnotation(MaxLength.class);

                        Object value = field.get(obj);

                        if (value != null &&
                            value.toString().length()
                            > maxLength.value()) {

                            errors[count++] =
                                field.getName()
                                + " length is greater than "
                                + maxLength.value();
                        }
                    }
                }

                currentClass =
                    currentClass.getSuperclass();
            }

        } catch (Exception e) {

            System.out.println("Error: " + e);
        }
        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = errors[i];
        }

        return result;
    }
}

public class MiniBank {

    public static void main(String[] args) {
        Account account =
            new SavingsAccount(
                "AC001",
                "Riya",
                -500
            );

        System.out.println(account);

        System.out.println("\nValidation Errors:");

        String[] errors =
            AnnotationValidator.validate(account);

        if (errors.length == 0) {

            System.out.println("No errors");

        } else {

            for (String error : errors) {
                System.out.println(error);
            }
        }

        WithdrawRule rule =
            (a, amount) -> a.balance >= amount;

        System.out.println(
            "\nCan withdraw 200: "
            + rule.allow(account, 200)
        );
    }
}