package Package;

import Package.Exceptions.*;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class ShoppingMallClass implements ShoppingMall
{
    Map<String, Cart> carts;
    Map<String, Item> items;

    /**
     * Constructor for the ShoppingMall Class
     */
    public ShoppingMallClass()
    {
        carts = new HashMap<String, Cart>();
        items = new HashMap<String, Item>();
    }

    /**
     * Adds a new cart to the HashMap of carts in the system, with it's
     * dedicated key (ID)
     * @param ID String
     * @param capacity int
     * @pre ID != null && capacity >= 0
     */
    public void newCart(String ID, int capacity)
    {
        if (doesCartExist(ID)) {
            throw new CartExists();
        } else {
            carts.put(ID, new CartClass(ID, capacity));
        }
    }

    /**
     * Adds a new item to the HashMap of items in the system, with it's
     * dedicated key (ID)
     * @param ID String
     * @param price int
     * @param size int
     * @pre ID != null && price >= 0 && size >= 0
     */
    public void newItem(String ID, int price, int size) throws ItemExists
    {
        if (doesItemExist(ID)) {
            throw new ItemExists();
        } else {
            items.put(ID, new ItemClass(ID, price, size));
        }
    }

    /**
     * Gets an item with the same ID (itemName) and adds it to the
     * List of items inside of the cart with the ID specified (ID)
     * @param itemName String
     * @param ID String
     * @pre itemName != null && ID != null
     */
    public void addToCart(String itemName, String ID) throws NonExistingCart, NonExistingItem, CapacityExceeded
    {
        if (!doesCartExist(ID)){
            throw new NonExistingCart();
        } else if (!doesItemExist(itemName)) {
            throw new NonExistingItem();
        } else if (exceedsCapacity(ID, itemName)) {
            throw new CapacityExceeded();
        } else{
            carts.get(ID).addItemToCart(items.get(itemName));
        }
    }

    /**
     * Removes an item with the same ID (itemName) from the List
     * of items inside of the cart with the ID specified (ID)
     * @param itemName String
     * @param ID String
     * @pre itemName != null && ID != null
     */
    public void removeFromCart(String itemName, String ID) throws NonExistingCart, NonExistingItem
    {
        if (!doesCartExist(ID)){
            throw new NonExistingCart();
        } else if (!doesItemExistInCart(itemName,ID)) {
            throw new NonExistingItem();
        } else{
            carts.get(ID).removeItemFromCart(items.get(itemName));
        }
    }

    /**
     * Removes all the items from the List of items of the cart with the
     * ID specified (ID) and returns the amount of money the items were worth
     * @param ID String
     * @pre ID != null
     */
    public int pay(String ID) throws  NonExistingCart
    {
        if (doesCartExist(ID)){
            return carts.get(ID).payAmount();
        } else{
            throw new NonExistingCart();
        }
    }

    /**
     * Returns an iterator containing all the items present in the List of
     * items of the cart with the given ID (ID)
     * @param ID String
     * @pre ID != null
     */
    public Iterator<Item> itemsIterator(String ID) throws NonExistingCart, NonExistingItem
    {
        if (!doesCartExist(ID)){
            throw new NonExistingCart();
        }else if(isCartEmpty(ID)){
            throw new NonExistingItem();
        }
        return carts.get(ID).itemIterator();
    }

    /**
     * Returns true if the cart with the given ID (ID) is empty, if not,
     * returns false
     * @param ID String
     * @pre ID != null
     */
    private boolean isCartEmpty(String ID)
    {
        return carts.get(ID).isEmpty();
    }

    /**
     * Returns true if there is an item with the given ID (itemName) in the
     * List of items of a cart with the given ID (ID), if not, returns false
     * @param itemName String
     * @param ID String
     * @pre itemName != null && ID != null
     */
    private boolean doesItemExistInCart(String itemName, String ID)
    {
        return carts.get(ID).itemInCart(items.get(itemName));
    }

    /**
     * Returns true if the cart is in the HashMap of carts in the
     * system, if not, returns false
     * @param ID String
     * @pre ID != null
     */
    private boolean doesCartExist(String ID)
    {
        return carts.containsKey(ID);
    }

    /**
     * Returns true if the item is in the HashMap of items in the
     * system, if not, returns false
     * @param ID String
     * @pre ID != null
     */
    private boolean doesItemExist(String ID)
    {
        return items.containsKey(ID);
    }

    /**
     * Returns true if the size of the item with the ID (itemID) given, when
     * added onto the current capacity of a cart with the ID (ID) given,
     * exceeds it's maximum capacity, if not, returns false
     * @param ID String
     * @param itemID String
     * @pre ID != null && itemID != null
     */
    private boolean exceedsCapacity(String ID, String itemID)
    {
        boolean exceeds = false;
        int size = items.get(itemID).getSize();
        size -= carts.get(ID).getCapacity();
        if(size > 0){
            exceeds = true;
        }
        return exceeds;
    }
}
