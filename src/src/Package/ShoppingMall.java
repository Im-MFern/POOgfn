package Package;

import java.util.Iterator;

public interface ShoppingMall
{
    void newCart(String ID, int capacity);

    void newItem(String ID, int price, int quantity);

    void addToCart(String itemName, String ID);

    void removeFromCart(String itemName, String ID);

    int pay(String cartID);

    Iterator<Item> itemsIterator(String ID);
}
