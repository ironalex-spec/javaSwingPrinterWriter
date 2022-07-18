package main.java.ui.templates.window;

import javax.swing.*;

public interface WindowInternalElementsDefinition{
    void add(JDesktopPane jDesktopPane, String key, String text, int x, int y, int width, int height);

    void addActionListener(String key, ElementActionListener elementActionListener);
}
