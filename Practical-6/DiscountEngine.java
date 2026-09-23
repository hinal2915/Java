import java.util.*;

interface DiscountRule {
    double apply(double price);
}

public class DiscountEngine {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Double> prices = new ArrayList<>();

        prices.add(1000.0);
        prices.add(2000.0);
        prices.add(500.0);
        prices.add(1500.0);

        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat 100 Discount");

        System.out.print("Choose discount: ");
        int choice = sc.nextInt();

        DiscountRule rule;

        if (choice == 1) {
            rule = price -> price - (price * 10 / 100);
        }
        else if (choice == 2) {
            rule = price -> price - (price * 20 / 100);
        }
        else if (choice == 3) {
            rule = price -> price - 100;
        }
        else {
            System.out.println("Invalid choice");
            return;
        }

        System.out.println("\nPrices after discount:");

        for (double price : prices) {

            double finalPrice = rule.apply(price);

            System.out.println(
                "Original: " + price +
                "  Final: " + finalPrice
            );
        }

        sc.close();
    }
}