package main.java.ui.templates.window.menu;

import main.java.ui.templates.window.ElementActionListener;


import javax.swing.*;
import javax.swing.event.MenuListener;
import java.util.HashMap;

public class MenuBarDefinition {
    private JMenuBar jMenuBar;
    private HashMap<String, MenuDefinition> menus = new HashMap<String, MenuDefinition>();

    public MenuBarDefinition(JMenuBar jMenuBar){
        this.jMenuBar = jMenuBar;
    }

    public void addMenu(String key, String name){
        JMenu menu = new JMenu(name);
        MenuDefinition menusDefinition = new MenuDefinition(menu);

        jMenuBar.add(menu);
        menus.put(key, menusDefinition);
    }

    public void addMenuItem(String keyMenu, String keyItem, String name){
        MenuDefinition menuDefinition = menus.get(keyMenu);
        menuDefinition.addMenuItem(keyItem, name);
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

    public void addMenuItemActionListener(String keyMenu, String keyItem, ElementActionListener elementActionListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);
        menuDefinition.addMenuItemActionListener(keyItem, elementActionListener);
    }

    public void addSubMenuListener(String keyMenu, String keySubMenu, MenuListener menuListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);

        menuDefinition.addSubMenuListener(keySubMenu, menuListener);
    }

    public void addSubMenuItemActionListener(String keyMenu, String keySubMenu, String keySubMenuItem, ElementActionListener elementActionListener){
        MenuDefinition menuDefinition = menus.get(keyMenu);

        menuDefinition.addSubMenuItemActionListener(keySubMenu, keySubMenuItem, elementActionListener);
    }
}
