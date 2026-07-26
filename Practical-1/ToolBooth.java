import java.util.Scanner;
public class ToolBooth
{
    record Vehicle(String num,String type){}
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int total=0;
        int carCount=0;
        int bikeCount=0;
        int truckCount=0;

        while(true)
        {
            System.out.println("Enter Vehicle Number (or done to stop)");
            String num=s.next();
            if(num.equalsIgnoreCase("done"))
            {
                break;  
            }
            System.out.println("Enter Vehicle type (bike,car,truck)");
            String type=s.next().toLowerCase();
            Vehicle v=new Vehicle(num,type);
            int toll=0;
            switch(v.type())
            {
                case "bike":
                    toll=20;
                    bikeCount++;
                    break;
                case "car":
                    toll=50;
                    carCount++;
                    break;
                case "truck":
                    toll=150;
                    truckCount++;
                    break;
                default:
                    System.out.println("Invalid vehicle type");
                    continue;
            }
            total+=toll;
        }
         System.out.println("Total toll: " + total);

        if (bikeCount >= carCount && bikeCount >= truckCount) 
        {
            System.out.println("Most frequent: bike");
        } 
        else if (carCount >= bikeCount && carCount >= truckCount) 
        {
            System.out.println("Most frequent: car");
        } 
        else 
        {
            System.out.println("Most frequent: truck");
        }

        s.close();
    }
}