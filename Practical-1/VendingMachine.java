import java.util.Scanner;
public class VendingMachine 
{
    enum Coin
    {
        ONE,TWO,FIVE,TEN;
    }
    
    public static void main(String args[])
    {
        System.out.println("--------WELCOME TO VENDING MACHINE--------");
        Scanner s=new Scanner(System.in);
        int price=15;
        int total=0;
        while(total<price)
        {
            System.out.println("Enter Coin(ONE,TWO,FIVE,TEN;)");
            Coin coin = Coin.valueOf(s.next().toUpperCase());
            int value=0;
            switch(coin)
            {
                case ONE:
                    value=1;
                    break;
                case TWO:
                    value=2;
                    break;
                case FIVE:
                    value=5;
                    break;
                case TEN:
                    value=10;
                    break;
            }
            total+=value;
            System.out.println("Total value= "+total);
        }
        System.out.println("Change Coins= "+(total-price));
        s.close();
    }  
}
