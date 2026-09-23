import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
@Retention(RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)
@interface NotBlank{

}
@Retention(RetentionPolicy.RUNTIME)
@Target (ElementType.FIELD)
@interface MaxLength
{
    int value();
}
class SignupForm
{
    @NotBlank
    @MaxLength(20)
    String username;
    @NotBlank
    @MaxLength (50)
    String email;
    @NotBlank
    @MaxLength (15)
    String Password;

    SignupForm(String username,String email,String Password)
    {
        this.username=username;
        this.email=email;
        this.Password=Password;
    }
}
class FormValidator 
{
    public static List<String>validate(Object obj)
    {
        List<String> errors=new ArrayList<>();
        Class<?>classType=obj.getClass();
        for(Field field:classType.getDeclaredFields())
        {
            field.setAccessible(true);
            try
            {
                Object value=field.get(obj);
                if (field.isAnnotationPresent(NotBlank.class)) 
                {
                    if(value==null || value.toString().trim().isEmpty())
                    {
                        errors.add(
                            field.getName()+" must not be blank"
                        );
                    }
                    
                }
                if(field.isAnnotationPresent(MaxLength.class))
                {

                }
            }
            catch(IllegalAccessException e)
            {
                errors.add(
                    "Cannot access field:"+field.getName()
                );
            }
        }
        return errors;
    }
}
public class MainValidator
{
    public static void main(String[] args) 
    {
        SignupForm form=new SignupForm("Hinal", "hinal@gmail.com", "1234567890");    
        List<String>errors=
        FormValidator.validate(form);
        if(errors.isEmpty())
        {
            System.out.println("Form is Valid.");
        }
        else
        {
            System.out.println("Validation Errors: ");
            for(String error:errors)
            {
                System.out.println(error);
            }
        }
    }
}