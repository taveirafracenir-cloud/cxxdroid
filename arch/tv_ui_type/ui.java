package arch.tv_ui_type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TVMenuItem {
    String name;
    TVMenuItem(String name) {
        this.name = name;
    }
}

public class UI {
    private List<TVMenuItem> menu = new ArrayList<>();
    private int selectedIndex = 0;

    public UI() {
        menu.add(new TVMenuItem("Live TV"));
        menu.add(new TVMenuItem("Apps"));
        menu.add(new TVMenuItem("Settings"));
        menu.add(new TVMenuItem("About CXXDROID"));
    }

    public void render() {
        System.out.println("\n=== CXXDROID TV Interface ===");
        for (int i = 0; i < menu.size(); i++) {
            if (i == selectedIndex)
                System.out.println(" > " + menu.get(i).name + " <");
            else
                System.out.println("   " + menu.get(i).name);
        }
        System.out.println("=============================");
    }

    public void navigate(String direction) {
        if (direction.equals("up") && selectedIndex > 0)
            selectedIndex--;
        else if (direction.equals("down") && selectedIndex < menu.size() - 1)
            selectedIndex++;
    }

    public void select() {
        System.out.println("Opening: " + menu.get(selectedIndex).name);
    }

    public static void main(String[] args) {
        UI ui = new UI();
        Scanner scanner = new Scanner(System.in);
        ui.render();
        System.out.println("Use commands: up / down / select / exit");

        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine();
            if (cmd.equals("exit")) break;
            if (cmd.equals("up") || cmd.equals("down"))
                ui.navigate(cmd);
            else if (cmd.equals("select"))
                ui.select();
            ui.render();
        }

        System.out.println("Exiting TV UI.");
        scanner.close();
    }
}
