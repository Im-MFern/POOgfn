package Package;

import java.util.Iterator;

public interface Cart
{
    void addItemToCart(Item item);

    int getCapacity();

    boolean itemInCart(Item item);

    void removeItemFromCart(Item item);

    Iterator<Item> itemIterator();

    int payAmount();

    boolean isEmpty();
}

