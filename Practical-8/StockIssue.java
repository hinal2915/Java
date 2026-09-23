import java.util.*;
class OutOfStockException extends Exception 
{
    int shortfall;
    OutOfStockException(int shortfall) 
    {
        super("Not enough stock");
        this.shortfall = shortfall;
    }
    int getShortfall() 
    {
        return shortfall;
    }
}
class InvalidQuantityException extends Exception 
{
    InvalidQuantityException(String message) 
    {
        super(message);
    }
}
class Warehouse 
{
    int stock;
    Warehouse(int stock) 
    {
        this.stock = stock;
    }
    void issue(String item, int qty)
            throws OutOfStockException,InvalidQuantityException 
    {
        if (qty <= 0) 
        {
            throw new InvalidQuantityException("Quantity must be greater than 0");
        }
        if (qty > stock) 
        {
            int shortfall = qty - stock;
            throw new OutOfStockException(shortfall);
        }
        stock = stock - qty;
        System.out.println(item + " issued: " + qty);
        System.out.println("Remaining stock: " + stock);
    }
}
public class StockIssue 
{
    public static void main(String[] args) 
    {
        Warehouse warehouse =new Warehouse(10);
        int[] requests = {4, 8, 0, 3, -2};
        for (int qty : requests) {
            try 
            {
                warehouse.issue("Laptop", qty);
            }
            catch (OutOfStockException e) 
            {
                System.out.println("Out of stock. Shortfall: "+ e.getShortfall());
            }
            catch (InvalidQuantityException e) 
            {
                System.out.println("Invalid quantity: "+ e.getMessage());
            }
        }
    }
}