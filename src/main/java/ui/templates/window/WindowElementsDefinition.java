package main.java.ui.templates.window;

public interface WindowElementsDefinition {

    void add(String key, String text, int x, int y, int width, int height);

    void addActionListener(String key, ElementActionListener elementActionListener);
}
