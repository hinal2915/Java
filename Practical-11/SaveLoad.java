import java.io.*;

class Student implements Serializable {
    String name;
    int age;
    transient String password;

    Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String toString() {
        return name + " " + age + " " + password;
    }
}

public class SaveLoad {
    public static void main(String[] args) throws Exception {

        Student[] students = {
            new Student("Riya", 20, "abc123"),
            new Student("Krisha", 21, "xyz456")
        };

        ObjectOutputStream out =
            new ObjectOutputStream(new FileOutputStream("students.dat"));

        out.writeObject(students);
        out.close();

        ObjectInputStream in =
            new ObjectInputStream(new FileInputStream("students.dat"));

        Student[] loaded = (Student[]) in.readObject();
        in.close();

        System.out.println("Loaded objects:");

        for (Student s : loaded) {
            System.out.println(s);
        }
    }
}