package main.java.ui.templates.window.menu;


import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MenuBarDefinition {
    private JMenuBar jMenuBar;
    private HashMap<String, MenuDefinition> menus = new HashMap<String, MenuDefinition>();

    public MenuBarDefinition(JMenuBar jMenuBar){
        this.jMenuBar = jMenuBar;
    }

    public void addMenu(String key, String name, int width, int height){
        JMenu menu = new JMenu("<html><p style='align-items: center;justify-content: center;'>" + name + "</p></html>");
        menu.setMaximumSize(new Dimension(width, height));
        menu.setMinimumSize(new Dimension(width, height));
        menu.setPreferredSize(new Dimension(width, height));
        menu.setHorizontalAlignment(SwingConstants.RIGHT);

        MenuDefinition menusDefinition = new MenuDefinition(menu);

        jMenuBar.add(menu);
        menus.put(key, menusDefinition);
    }

    public void addMenuHorizontalGlue(){
        jMenuBar.add(Box.createHorizontalGlue());
    }

    public void addMenuItem(String keyMenu, String keyItem, String name, int width, int height){
        MenuDefinition menuDefinition = menus.get(keyMenu);
        menuDefinition.addMenuItem(keyItem, name, width, height);
    }

    public void addSubMenu(String keyMenu, String keySubmenu, String name){
        MenuDefinition menuDefinition = menus.get(keyMenu);
        menuDefinition.addSubMenu(keySubmenu, name);
    }

    public void addSubMenuItem(String keyMenu, String keySubmenu, String keySubmenuItem, String name){
        MenuDefinition menuDefinition = menus.get(keyMenu);
        menuDefinition.addSubMenuItem(keySubmenu, keySubmenuItem, name);
    }

    public void addMenuListener(String keyMenu, MenuListener menuListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);

        menuDefinition.addMenuListener(menuListener);
    }

    public void addMenuItemActionListener(String keyMenu, String keyItem, ActionListener actionListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);
        menuDefinition.addMenuItemActionListener(keyItem, actionListener);
    }

    public void addSubMenuListener(String keyMenu, String keySubMenu, MenuListener menuListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);

        menuDefinition.addSubMenuListener(keySubMenu, menuListener);
    }

    public void addSubMenuItemActionListener(String keyMenu, String keySubMenu, String keySubMenuItem, ActionListener actionListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);

        menuDefinition.addSubMenuItemActionListener(keySubMenu, keySubMenuItem, actionListener);
    }
}
