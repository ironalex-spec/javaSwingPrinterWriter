package main.java.ui.templates.window.menu;

import main.java.ui.templates.window.ElementActionListener;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.util.HashMap;

public class SubMenuDefinition {
    private JMenu jSubMenu;
    private HashMap<String, JMenuItem> subMenuItems = new HashMap<String, JMenuItem>();

    public SubMenuDefinition(JMenu jSubMenu){
        this.jSubMenu = jSubMenu;
    }

    public void addSubMenuItem(String key, String name){
        JMenuItem jMenuItem = new JMenuItem(name);

        jSubMenu.add(jMenuItem);
        subMenuItems.put(key, jMenuItem);
    }

    public void addSubMenuListener(MenuListener menuListener){
        jSubMenu.addMenuListener(menuListener);
    }

    public void addSubMenuItemActionListener(String keySubMenuItem, ElementActionListener elementActionListener){
        JMenuItem jMenuItem = subMenuItems.get(keySubMenuItem);

        jMenuItem.addActionListener(elementActionListener);
    }
}
