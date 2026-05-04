package Package;

import Package.Exceptions.CartExists;
import Package.Exceptions.ItemExists;

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

    public void addCart(String ID, int capacity)
    {
        if (doesCartExist(ID)) {
            throw new CartExists();
        } else {
            carts.put(ID, new CartClass(ID, capacity));
        }
    }

    public void addItem(String ID, double price, int size)
    {
        if (doesItemExist(ID)) {
            throw new ItemExists();
        } else {
            items.put(ID, new ItemClass(ID, price, size));
        }
    }

    private boolean doesCartExist(String ID)
    {
        return carts.containsKey(ID);
    }

    private boolean doesItemExist(String ID)
    {
        return items.containsKey(ID);
    }
}
