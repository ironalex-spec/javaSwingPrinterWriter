package lib.ui.templates.window.menu;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.event.ActionListener;
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

    public void addSubMenuItemActionListener(String keySubMenuItem, ActionListener actionListener){
        JMenuItem jMenuItem = subMenuItems.get(keySubMenuItem);

        jMenuItem.addActionListener(actionListener);
    }

    public String getSubMenuItemName(String keySubMenuItem){
        JMenuItem jMenuItem = subMenuItems.get(keySubMenuItem);
        return jMenuItem.getName();
    }
}
