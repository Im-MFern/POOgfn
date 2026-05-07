package Package;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class CartClass implements Cart
{
    String ID;
    int capacity;
    List<Item> cartItems;

    /**
     * Constructor for the Cart Class
     * @param ID
     * @param capacity
     * @pre ID != null && capacity >= 0
     */
    public CartClass(String ID, int capacity)
    {
        this.ID = ID;
        this.capacity = capacity;
        this.cartItems = new ArrayList<Item>();
    }

    /**
     * Adds the item given into the List of items, and reduces the
     * capacity by it's size
     * @param item
     * @pre item != null
     */
    public void addItemToCart(Item item)
    {
        cartItems.add(item);
        capacity -= item.getSize();
    }

    /**
     * Returns this CartClass' capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns true if the item given is contained within the List of items
     * if not, returns false
     * @param item
     * @pre item != null
     */
    public boolean itemInCart(Item item) {
        return cartItems.contains(item);
    }

    /**
     * Removes the item given from the List of items, one at a time
     * @param item
     * @pre item != null
     */
    public void removeItemFromCart(Item item) {
        cartItems.remove(item);
        capacity += item.getSize();
    }

    /**
     * Returns an Iterator with all the items in this cart
     */
    public Iterator<Item> itemIterator()
    {
        return cartItems.iterator();
    }

    /**
     * Adds up the prices of all the items in the List of items, then
     * returns it, while also emptying the List of items
     */
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

    /**
     * Returns true if the List of items is empty, if not,
     * returns false
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
