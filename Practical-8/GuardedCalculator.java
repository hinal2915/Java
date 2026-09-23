import java.util. *;
class DivideByZeroException extends Exception
{
    DivideByZeroException(String message)
    {
        super(message);
    }
}
public class GuardedCalculator {

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        boolean success=false;
        while (!success) {
            try{
                System.out.println("Enter first Number: ");;
                double a=sc.nextDouble();
                System.out.println("Enter operator (+,-,*,/): ");
                char op=sc.next().charAt(0);
                System.out.println("Enter second Number: ");;
                double b=sc.nextDouble();
                double result;

                if(op=='/' && b==0)
                {
                    throw new DivideByZeroException("Can not divide by zero");
                }
                if(op=='+')
                {
                    result=a+b;
                }
                else if(op=='-')
                {
                    result=a-b;
                }
                else if(op=='*')
                {
                    result=a*b;
                }
                else if(op=='/')
                {
                    result=a/b;
                }
                else
                {
                    System.out.println("Invalid operator ");
                    continue;
                }
                System.out.println("Result: "+result);
                success=true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid number input/");
                sc.nextLine();
            }
            catch(DivideByZeroException e)
            {
                System.out.println(e.getMessage());
            }
            finally
            {
                System.out.println("Calculation attempt completed");
            }
        }
        sc.close();
    }
}
