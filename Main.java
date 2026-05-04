/*
 * Made by:
 * Miguel Aleixo Fernandes Nº74449
 * Rodrigo Miguel Pires Paulo Nº74906
 */

import java.util.Scanner;
import Package.ShoppingMall;
import Package.ShoppingMallClass;
import Package.Exceptions.*;

public class Main
{
    private final static String CART = "CART";
    private final static String ITEM = "ITEM";

    private final static String UNKNOWN_CMD = "Unknown Command.";
    private final static String CART_EXISTS = "Cart already exists!";
    private final static String ITEM_EXISTS = "Item already exist!";

    private final static String CART_ADDED = "Cart added successfully.";
    private final static String ITEM_ADDED = "Item added successfully.";
    private final static String EXITING = "Bye!";

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ShoppingMall SM = new ShoppingMallClass();
        processCommands(SM, in);
        in.close();
    }

    private enum Command
    {
        NEW, ADD, REMOVE, LIST, PAY, EXIT, UNKNOWN
    }

    private static Command getCommand(String cmd)
    {
        try {
            return Command.valueOf(cmd.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }
    
    private static String[] fullCommandReader(Scanner in)
    {
        String fullCmd = in.nextLine();
        return fullCmd.split(" ");
    }

    private static void processCommands(ShoppingMall SM, Scanner in) {
        Command command;
        do {
            String[] fullCmd = fullCommandReader(in);
            command = getCommand(fullCmd[0]);
            processCommand(SM, command, fullCmd);
        } while (!command.equals(Command.EXIT));
    }

    private static void processCommand(ShoppingMall SM, Command command, String[] fullCmd)
    {
        switch(command){
            case NEW -> newCmdReader(SM, fullCmd);
            case ADD -> addCmd(fullCmd);
            case REMOVE -> removeCmd(fullCmd);
            case LIST -> listCmd(fullCmd);
            case PAY -> payCmd(fullCmd);
            case EXIT -> exitCmd();
            case UNKNOWN -> doNothing();
        }
    }

    private static void addCmd(String[] fullCmd)
    {
        
    }

    private static void payCmd(String[] fullCmd)
    {

    }

    private static void listCmd(String[] fullCmd)
    {

    }

    private static void removeCmd(String[] fullCmd)
    {

    }

    private static void newCmdReader(ShoppingMall SM, String[] fullCmd)
    {
        if (fullCmd[1].equals("CART")) {
            newCartCmd(SM, fullCmd[2], Integer.parseInt(fullCmd[3]));
        } else if (fullCmd[1].equals("ITEM")) {
            newItemCmd(SM, fullCmd[2], Double.parseDouble(fullCmd[3]),Integer.parseInt(fullCmd[4]));
        } else {
            System.out.println(UNKNOWN_CMD);
        }
    }

    private static void exitCmd()
    {
        System.out.println(EXITING);
    }

    private static void doNothing()
    {
        System.out.println(UNKNOWN_CMD);
    }

    private static void newCartCmd(ShoppingMall SM, String ID, int capacity)
    {
        try {
            SM.addCart(ID, capacity);
            System.out.println(CART_ADDED);
        } catch ( CartExists e ) {
            System.out.println(CART_EXISTS);
        }
    }

    private static void newItemCmd(ShoppingMall SM, String ID, double price, int size)
    {
        try {
            SM.addItem(ID, price, size);
            System.out.println(ITEM_ADDED);
        } catch ( ItemExists e ) {
            System.out.println(ITEM_EXISTS);
        }
    }
}