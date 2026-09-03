abstract class Media {
    String title;
    int daysLate;

    Media(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    abstract double lateFee();
}

class Book extends Media {

    Book(String title, int daysLate) {
        super(title, daysLate);
    }

    double lateFee() {
        return daysLate * 2;
    }
}

class DVD extends Media {

    DVD(String title, int daysLate) {
        super(title, daysLate);
    }

    double lateFee() {
        return daysLate * 5;
    }
}

class Magazine extends Media {

    Magazine(String title, int daysLate) {
        super(title, daysLate);
    }

    double lateFee() {
        return daysLate * 1.5;
    }
}

public class MediaDriver {
    public static void main(String[] args) {

        Media[] returned = {
            new Book("Java Basics", 3),
            new DVD("Avengers", 2),
            new Magazine("Tech Today", 4)
        };

        double total = 0;

        for (Media m : returned) {

            double fee = m.lateFee();

            System.out.println(m.title + " Late Fee: " + fee);

            total = total + fee;
        }

        System.out.println("Total Late Fees: " + total);
    }
}