package project.gui.GeneralUseGUIs;

import javafx.event.Event;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.util.HashMap;

public class Menu {
    private HashMap<String, String> itemToResource;

    public TreeItem<String> getOptions() {
        return options;
    }

    private TreeItem<String> options;

    Menu() {
        itemToResource = new HashMap<>();
        options = new TreeItem<>();
        options.setExpanded(true);
    }

    public void switchScene(ApplicationController controller, Event event, String item) throws IOException {
        SceneSwitcher.switchScene(controller, event, itemToResource.get(item));
    }

    Menu addOption(String item, String resource) {
        itemToResource.put(item, resource);
        options.getChildren().add(new TreeItem<>(item));
        return this;
    }
}
