package main.java.ui.libClass.Swing.ScrollPane;

import java.awt.*;
import javax.swing.*;

/* Corner.java is used by ScrollDemo.java. */

public class Corner extends JComponent {
    protected void paintComponent(Graphics g) {
        // Fill me with dirty brown/orange.
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
