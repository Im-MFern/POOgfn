package Package;

import Package.Exceptions.*;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class ShoppingMallClass implements ShoppingMall
{
    Map<String, Cart> carts;
    Map<String, Item> items;

    public ShoppingMallClass()
    {
        carts = new HashMap<String, Cart>();
        items = new HashMap<String, Item>();
    }

    public void newCart(String ID, int capacity)
    {
        if (doesCartExist(ID)) {
            throw new CartExists();
        } else {
            carts.put(ID, new CartClass(ID, capacity));
        }
    }

    public void newItem(String ID, int price, int size) throws ItemExists
    {
        if (doesItemExist(ID)) {
            throw new ItemExists();
        } else {
            items.put(ID, new ItemClass(ID, price, size));
        }
    }

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

    public int pay(String ID) throws  NonExistingCart
    {
        if (doesCartExist(ID)){
            return carts.get(ID).payAmount();
        } else{
            throw new NonExistingCart();
        }
    }

    public Iterator<Item> itemsIterator(String ID) throws NonExistingCart, NonExistingItem
    {
        if (!doesCartExist(ID)){
            throw new NonExistingCart();
        }else if(isCartEmpty(ID)){
            throw new NonExistingItem();
        }
        return carts.get(ID).itemIterator();
    }

    private boolean isCartEmpty(String ID)
    {
        return carts.get(ID).isEmpty();
    }

    private boolean doesItemExistInCart(String itemName, String ID)
    {
        return carts.get(ID).itemInCart(items.get(itemName));
    }

    private boolean doesCartExist(String ID)
    {
        return carts.containsKey(ID);
    }

    private boolean doesItemExist(String ID)
    {
        return items.containsKey(ID);
    }

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
