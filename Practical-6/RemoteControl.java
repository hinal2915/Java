interface Switchable {

    void on();

    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan ON");
    }

    public void off() {
        System.out.println("Fan OFF");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
    }
}

interface SwitchRule {
    boolean canSwitchOn(Switchable device, int hour);
}

public class RemoteControl {

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        for (Switchable d : devices) {
            d.toggle();
        }

        SwitchRule rule1 = new SwitchRule() {
            public boolean canSwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        System.out.println("Anonymous: " +
                rule1.canSwitchOn(devices[0], 10));

        SwitchRule rule2 =
                (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("Lambda: " +
                rule2.canSwitchOn(devices[1], 23));
    }
}