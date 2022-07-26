package main.java.ui.templates.window;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface WindowInternalElementsDefinition{
    void add(JComponent jDesktopPane, String key, String text, int x, int y, int width, int height);

    void addActionListener(String key, ActionListener actionListener);
}
