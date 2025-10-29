#include <iostream>
#include <vector>
#include <string>

class TVMenuItem {
public:
    std::string name;
    TVMenuItem(const std::string& n) : name(n) {}
};

class TVUI {
private:
    std::vector<TVMenuItem> menu;
    int selectedIndex = 0;

public:
    TVUI() {
        menu.push_back(TVMenuItem("Live TV"));
        menu.push_back(TVMenuItem("Apps"));
        menu.push_back(TVMenuItem("Settings"));
        menu.push_back(TVMenuItem("About CXXDROID"));
    }

    void render() {
        std::cout << "\n=== CXXDROID TV Interface ===\n";
        for (int i = 0; i < menu.size(); ++i) {
            if (i == selectedIndex)
                std::cout << " > " << menu[i].name << " <\n";
            else
                std::cout << "   " << menu[i].name << "\n";
        }
        std::cout << "=============================\n";
    }

    void navigate(const std::string& direction) {
        if (direction == "up" && selectedIndex > 0)
            selectedIndex--;
        else if (direction == "down" && selectedIndex < (int)menu.size() - 1)
            selectedIndex++;
    }

    void select() {
        std::cout << "Opening: " << menu[selectedIndex].name << "\n";
    }
};

int main() {
    TVUI ui;
    std::string cmd;

    ui.render();
    std::cout << "Use commands: up / down / select / exit\n";

    while (true) {
        std::cout << "> ";
        std::cin >> cmd;
        if (cmd == "exit") break;
        if (cmd == "up" || cmd == "down") ui.navigate(cmd);
        else if (cmd == "select") ui.select();
        ui.render();
    }

    std::cout << "Exiting TV UI.\n";
    return 0;
}
