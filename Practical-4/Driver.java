public class Driver {

    public static void main(String[] args) {

        String[] passwords = {
            "abc",
            "Password",
            "Pass1234",
            "Abcd1234!"
        };

        for (String pw : passwords) {

            System.out.println("Password: " + pw);

            System.out.println("Length: " + (pw.length() >= 8));
            System.out.println("Uppercase: " + pw.matches(".*[A-Z].*"));
            System.out.println("Digit: " + pw.matches(".*[0-9].*"));
            System.out.println("Special: " + pw.matches(".*[^a-zA-Z0-9].*"));

            System.out.println("Strength: " +
                    PasswordChecker.strength(pw));

            System.out.println();
        }
    }
}