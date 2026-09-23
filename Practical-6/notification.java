@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class EmailNotifier implements Notifier, Urgent {

    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSNotifier implements Notifier {

    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class notification {

    public static void main(String[] args) {

        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {
            email,
            sms
        };

        for (Notifier n : senders) {
            n.send("Bank notification");
        }

        Notifier urgentEmail = new Notifier() {
            public void send(String message) {
                System.out.println("URGENT Email: " + message);
            }
        };

        urgentEmail.send("Important message");
    }
}