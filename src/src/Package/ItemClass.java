package Package;

public class ItemClass implements Item
{
    String ID;
    int price;
    int size;

    public ItemClass(String ID, int price, int size)
    {
        this.ID = ID;
        this.price = price;
        this.size = size;
    }

    public String getID() {
        return ID;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() { return price; }
}
