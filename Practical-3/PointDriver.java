public class PointDriver
{
    public static void main(String[] args) 
    {
        Point[] point={
            new Point(1,2),
            new Point(1,2),
            new Point(3,4),
            new Point(5,6),
            new Point(3,4),
        };
        int distinct=0;
        for(int i=0;i<point.length;i++)
        {
            boolean found=false;
            for(int j=0;j<i;j++)
            {
                if(point[i].equals(point[j]))
                {
                    found=true;
                    break;
                }
            }
            if(!found)
            {
               distinct++;
            }
        }
        System.out.println("District: "+distinct);
    }
}