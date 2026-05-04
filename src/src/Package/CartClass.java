package Package;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class CartClass implements Cart
{
    String ID;
    int capacity;
    List<Item> cartItems;

    public CartClass(String ID, int capacity)
    {
        this.ID = ID;
        this.capacity = capacity;
        this.cartItems = new ArrayList<Item>();
    }

    public void addItemToCart(Item item)
    {
        cartItems.add(item);
        capacity -= item.getSize();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean itemInCart(Item item) {
        return cartItems.contains(item);
    }

    public void removeItemFromCart(Item item) {
        cartItems.remove(item);
        capacity += item.getSize();
    }

    public Iterator<Item> itemIterator()
    {
        return cartItems.iterator();
    }

    public int payAmount()
    {
        int amount = 0;

        for (int i = 0; i < cartItems.size(); i++)
        {
            amount += cartItems.get(i).getPrice();
        }

        cartItems.clear();

        return amount;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
