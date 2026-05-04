package Package;
import java.util.Map;
import java.util.HashMap;

public class CartClass implements Cart
{
    String ID;
    int capacity;
    int counter;
    Map<String, Item> cartItems;

    public CartClass(String ID, int capacity)
    {
        this.ID = ID;
        this.capacity = capacity;
        this.cartItems = new HashMap<String, Item>();
        counter = 0;
    }

    public void addItemToCart(String ID, double price, int size)
    {
        if (cartItems.containsKey(ID)) {
            cartItems.get(ID).increaseCounter();
        } else {
            cartItems.put(ID,new ItemClass(ID,price,size));
            System.out.println("I hate niggas deluxe");
        }
    }

    public boolean isItemInCart(String ID)
    {
        return cartItems.containsKey(ID);
    }
}
