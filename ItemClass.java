package Package;

public class ItemClass implements Item
{
    String ID;
    double price;
    int size;
    int counter;

    public ItemClass(String ID, double price, int size)
    {
        this.ID = ID;
        this.price = price;
        this.size = size;
        this.counter = 1;
    }

    public void increaseCounter()
    {
        this.counter++;
    }

    public int getCounter()
    {
        return counter;
    }
}
