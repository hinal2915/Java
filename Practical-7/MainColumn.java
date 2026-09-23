import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;
}

public class MainColumn {

    public static void main(String[] args) throws Exception {

        String[] header = {"name", "age"};
        String[] data = {"Riya", "20"};

        Student s = new Student();

        Field[] fields = Student.class.getDeclaredFields();

        for (Field f : fields) {

            if (f.isAnnotationPresent(Column.class)) {

                Column c = f.getAnnotation(Column.class);

                for (int i = 0; i < header.length; i++) {

                    if (c.name().equals(header[i])) {

                        f.setAccessible(true);

                        if (f.getType() == String.class)
                            f.set(s, data[i]);

                        if (f.getType() == int.class)
                            f.set(s, Integer.parseInt(data[i]));
                    }
                }
            }
        }

        System.out.println(s.name);
        System.out.println(s.age);
    }
}