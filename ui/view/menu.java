package ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CXXDROID Menu
 * --------------
 * Provides a simple interactive menu system for the CXXDROID UI.
 * Supports item selection and navigation via console input.
 *
 * Original code for CXXDROID.
 */
public class Menu {

    private List<String> items;
    private int selectedIndex;

    public Menu() {
        items = new ArrayList<>();
        selectedIndex = 0;
    }

    /**
     * Add an item to the menu
     * @param item The name of the menu item
     */
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * Display the menu in the console
     */
    public void display() {
        System.out.println("\n====== CXXDROID Menu ======");
        for (int i = 0; i < items.size(); i++) {
            if (i == selectedIndex) {
                System.out.println(" > " + items.get(i) + " <");
            } else {
                System.out.println("   " + items.get(i));
            }
        }
        System.out.println("============================");
    }

    /**
     * Navigate the menu
     * @param direction "up" or "down"
     */
    public void navigate(String direction) {
        if (direction.equals("up") && selectedIndex > 0) {
            selectedIndex--;
        } else if (direction.equals("down") && selectedIndex < items.size() - 1) {
            selectedIndex++;
        }
    }

    /**
     * Get the currently selected item
     * @return The selected menu item
     */
    public String getSelectedItem() {
        if (items.isEmpty()) return "";
        return items.get(selectedIndex);
    }

    /**
     * Run an interactive menu loop (console-based)
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        display();
        System.out.println("Use commands: up / down / select / exit");

        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine();
            if (cmd.equals("exit")) break;
            if (cmd.equals("up") || cmd.equals("down")) {
                navigate(cmd);
            } else if (cmd.equals("select")) {
                System.out.println("[Menu] Selected: " + getSelectedItem());
            }
            display();
        }

        System.out.println("[Menu] Exiting menu.");
    }

    // Example usage
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addItem("Live TV");
        menu.addItem("Apps");
        menu.addItem("Settings");
        menu.addItem("About CXXDROID");
        menu.run();
    }
}
