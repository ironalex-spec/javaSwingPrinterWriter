package lib.ui.templates.window;

import java.awt.event.ActionListener;

public interface WindowElementsDefinition {

    void add(String key, String text, int x, int y, int width, int height);

    void addActionListener(String key,  ActionListener actionListener);
}
