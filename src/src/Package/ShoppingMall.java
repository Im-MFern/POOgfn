package Package;

import java.util.Iterator;

public interface ShoppingMall
{
    /**
     * Adds a new cart to the HashMap of carts in the system, with it's
     * dedicated key (ID)
     * @param ID String
     * @param capacity int
     * @pre ID != null && capacity >= 0
     */
    void newCart(String ID, int capacity);

    /**
     * Adds a new item to the HashMap of items in the system, with it's
     * dedicated key (ID)
     * @param ID String
     * @param price int
     * @param quantity int
     * @pre ID != null && price >= 0 && quantity >= 0
     */
    void newItem(String ID, int price, int quantity);

    /**
     * Gets an item with the same ID (itemName) and adds it to the
     * List of items inside of the cart with the ID specified (ID)
     * @param itemName String
     * @param ID String
     * @pre itemName != null && ID != null
     */
    void addToCart(String itemName, String ID);

    /**
     * Removes an item with the same ID (itemName) from the List
     * of items inside of the cart with the ID specified (ID)
     * @param itemName String
     * @param ID String
     * @pre itemName != null && ID != null
     */
    void removeFromCart(String itemName, String ID);

    /**
     * Removes all the items from the List of items of the cart with the
     * ID specified (ID) and returns the amount of money the items were worth
     * @param cartID String
     * @pre cartID != null
     */
    int pay(String cartID);

    /**
     * Returns an iterator containing all the items present in the List of
     * items of the cart with the given ID (ID)
     * @param ID String
     * @pre ID != null
     */
    Iterator<Item> itemsIterator(String ID);
}
