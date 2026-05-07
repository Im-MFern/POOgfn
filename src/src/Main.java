/*
 * Made by:
 * Miguel Aleixo Fernandes Nº74449
 * Rodrigo Miguel Pires Paulo Nº74906
 */

import java.util.Iterator;
import java.util.Scanner;

import Package.Exceptions.*;
import Package.ShoppingMall;
import Package.ShoppingMallClass;
import Package.Item;

public class Main
{
    private final static String CART = "CART";
    private final static String ITEM = "ITEM";

    private final static String UNKNOWN_CMD = "Unknown Command.";
    private final static String CART_EXISTS = "Cart already exists!";
    private final static String ITEM_EXISTS = "Item already exists!";
    private final static String NO_CART = "Non-existing cart!";
    private final static String NO_ITEM = "Non-existing item!";
    private final static String CAPACITY_EXCEEDED = "Capacity exceeded!";
    private final static String CART_ADDED = "Cart created successfully.";
    private final static String NO_ITEM_IN_CART = "Item is not in cart!";
    private final static String EMPTY_CART = "Empty cart!";
    private final static String ITEM_ADDED = "Item added successfully.";
    private final static String ITEM_REMOVED = "Item successfully removed.";
    private final static String ITEM_CREATED = "Item created successfully.";
    private final static String LIST = "%s %d\n";
    private final static String EXITING = "Bye!";

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ShoppingMall SM = new ShoppingMallClass();
        processCommands(SM, in);
        in.close();
    }

    /**
     * Enumerate containing the commands used in this program
     */
    private enum Command
    {
        NEW, ADD, REMOVE, LIST, PAY, EXIT, UNKNOWN
    }

    /**
     * Reads a String and associates it with a command enum
     * @param cmd Scanner
     * @pre cmd != null
     */
    private static Command getCommand(String cmd)
    {
        try {
            return Command.valueOf(cmd.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /**
     * Reads an entire line written through the scanner
     * @param in Scanner
     * @pre in != null
     */
    private static String[] fullCommandReader(Scanner in)
    {
        String fullCmd = in.nextLine();
        return fullCmd.split(" ");
    }

    /**
     * Reads a command and puts it through the processCommand function to execute it
     * @param SM ShoppingMall
     * @param in Scanner
     * @pre SM != null && in != null
     */
    private static void processCommands(ShoppingMall SM, Scanner in)
    {
        Command command;
        do {
            String[] fullCmd = fullCommandReader(in);
            command = getCommand(fullCmd[0]);
            processCommand(SM, command, fullCmd);
        } while (!command.equals(Command.EXIT));
    }

    /**
     * Executes a given command
     * @param SM ShoppingMall
     * @param command Command
     * @param fullCmd String[]
     * @pre SM != null && command != null && fullCmd != null
     */
    private static void processCommand(ShoppingMall SM, Command command, String[] fullCmd)
    {
        switch(command){
            case NEW -> newCmdReader(SM, fullCmd);
            case ADD -> addCmd(SM, fullCmd);
            case REMOVE -> removeCmd(SM, fullCmd);
            case LIST -> listCmd(SM, fullCmd);
            case PAY -> payCmd(SM, fullCmd);
            case EXIT -> exitCmd();
            case UNKNOWN -> doNothing();
        }
    }

    /**
     * Creates a new ShoppingCart or Item in the system
     * @param SM ShoppingMall
     * @param fullCmd String[]
     * @pre SM != null && fullCmd != null
     */
    private static void addCmd(ShoppingMall SM, String[] fullCmd)
    {
        try {
            SM.addToCart(fullCmd[1],fullCmd[2]);
            System.out.println(ITEM_ADDED);
        }catch (NonExistingCart e){
            System.out.println(NO_CART);
        }catch (NonExistingItem e){
            System.out.println(NO_ITEM);
        }catch (CapacityExceeded e){
            System.out.println(CAPACITY_EXCEEDED);
        }
    }

    /**
     * Tallies up all of the items in a cart, deletes them from the cart, and returns their
     * value, if the cart has no items, prints EMPTY_CART
     * @param SM ShoppingMall
     * @param fullCmd String[]
     * @pre SM != null && fullCmd != null
     */
    private static void payCmd(ShoppingMall SM, String[] fullCmd)
    {
        String cartID = fullCmd[1];
        try {
            int pay = SM.pay(cartID);
            if (pay == 0) {
                System.out.println(EMPTY_CART);
            } else {
                System.out.println(pay);
            }
        } catch (NonExistingCart e){
            System.out.println(NO_CART);
        }
    }

    /**
     * List all the items in a given cart, if the cart if empty,
     * prints EMPTY_CART
     * @param SM ShoppingMall
     * @param fullCmd String[]
     * @pre SM != null && fullCmd != null
     */
    private static void listCmd(ShoppingMall SM, String[] fullCmd)
    {
        try {

            Iterator<Item> it = SM.itemsIterator(fullCmd[1]);
            do{
                Item item = it.next();
                System.out.printf(LIST,item.getID(),item.getPrice());
            }while (it.hasNext());
        }catch (NonExistingItem e){
            System.out.println(EMPTY_CART);
        } catch (NonExistingCart e) {
            System.out.println(NO_CART);
        }
    }

    /**
     * Attempts to remove a given item from a given cart, if the cart does not exist,
     * prints NO_CART, and if the item given is not in the given cart, prints NO_ITEM_IN_CART
     * @param SM ShoppingMall
     * @param fullCmd String[]
     * @pre SM != null && fullCmd != null
     */
    private static void removeCmd(ShoppingMall SM, String[] fullCmd)
    {
        try {
            SM.removeFromCart(fullCmd[1],fullCmd[2]);
            System.out.println(ITEM_REMOVED);
        }catch (NonExistingCart e){
            System.out.println(NO_CART);
        }catch (NonExistingItem e){
            System.out.println(NO_ITEM_IN_CART);
        }
    }

    /**
     * Reads if the variable given after the NEW command is either a cart or an item,
     * if not, prints UNKNOWN_CMD
     * @param SM ShoppingMall
     * @param fullCmd String[]
     * @pre SM != null && fullCmd != null
     */
    private static void newCmdReader(ShoppingMall SM, String[] fullCmd)
    {
        if (fullCmd[1].equals(CART)) {
            newCartCmd(SM, fullCmd[2], Integer.parseInt(fullCmd[3]));
        } else if (fullCmd[1].equals(ITEM)) {
            newItemCmd(SM, fullCmd[2], Integer.parseInt(fullCmd[3]),Integer.parseInt(fullCmd[4]));
        } else {
            System.out.println(UNKNOWN_CMD);
        }
    }

    /**
     * Prints EXITING
     */
    private static void exitCmd()
    {
        System.out.println(EXITING);
    }

    /**
     * Prints UNKNOWN_CMD
     */
    private static void doNothing()
    {
        System.out.println(UNKNOWN_CMD);
    }

    /**
     * Creates a new cart in the system, with it's own ID and capacity
     * @param SM ShoppingMall
     * @param ID String
     * @param capacity int
     * @pre SM != null && ID != null && capacity >= 0
     */
    private static void newCartCmd(ShoppingMall SM, String ID, int capacity)
    {
        try {
            SM.newCart(ID, capacity);
            System.out.println(CART_ADDED);
        } catch ( CartExists e ) {
            System.out.println(CART_EXISTS);
        }
    }

    /**
     * Creates a new item in the system, with it's ow ID, price, and size
     * @param SM ShoppingMall
     * @param ID String
     * @param price int
     * @param size int
     * @pre SM != null && ID != null && price >= 0 && size >= 0
     */
    private static void newItemCmd(ShoppingMall SM, String ID, int price, int size)
    {
        try {
            SM.newItem(ID, price, size);
            System.out.println(ITEM_CREATED);
        } catch ( ItemExists e ) {
            System.out.println(ITEM_EXISTS);
        }
    }
}