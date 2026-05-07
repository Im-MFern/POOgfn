package Package;

import java.util.Iterator;

public interface Cart
{
    /**
     * Adds the item given into the List of items, and reduces the
     * capacity by it's size
     * @param item
     * @pre item != null
     */
    void addItemToCart(Item item);

    /**
     * Returns this CartClass' capacity
     */
    int getCapacity();

    /**
     * Returns true if the item given is contained within the List of items
     * if not, returns false
     * @param item
     * @pre item != null
     */
    boolean itemInCart(Item item);

    /**
     * Removes the item given from the List of items, one at a time
     * @param item
     * @pre item != null
     */
    void removeItemFromCart(Item item);

    /**
     * Returns an Iterator with all the items in this cart
     */
    Iterator<Item> itemIterator();

    /**
     * Adds up the prices of all the items in the List of items, then
     * returns it, while also emptying the List of items
     */
    int payAmount();

    /**
     * Returns true if the List of items is empty, if not,
     * returns false
     */
    boolean isEmpty();
}

