package Package;

public class ItemClass implements Item
{
    String ID;
    int price;
    int size;

    /**
     * Constructor for the Item Class
     * @param ID
     * @param price
     * @param size
     * @pre ID != null && price >= 0 && size >= 0
     */
    public ItemClass(String ID, int price, int size)
    {
        this.ID = ID;
        this.price = price;
        this.size = size;
    }

    /**
     * Returns this itemClass' ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns this itemClass' size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns this itemClass' price
     */
    public int getPrice() { return price; }
}
