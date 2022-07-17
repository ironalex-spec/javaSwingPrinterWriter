package main.java.ui.templates;

import main.java.ui.templates.window.ElementActionListener;
import main.java.ui.templates.window.button.WindowButtonsDefinition;
import main.java.ui.templates.window.label.WindowLabelsDefinition;
import main.java.ui.templates.window.menu.MenuBarDefinition;
import main.java.ui.templates.window.textField.WindowTextFieldsDefinition;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;

public class BaseWindow {
    private JFrame frame;
    private JMenuBar jMenuBar;

    JDesktopPane jDesktopPane = new JDesktopPane();

    /*Item definition base window*/
    private MenuBarDefinition menuBarDefinition;
    private WindowButtonsDefinition windowButtonDefinition;
    private WindowLabelsDefinition windowLabelDefinition;
    private WindowTextFieldsDefinition windowTextFieldsDefinition;

    public BaseWindow(int x, int y, int width, int height, int menuHeight) {
        frame = new JFrame("Base window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(width, height);
        frame.setPreferredSize(new Dimension(10,10));
        frame.setLocation(x, y);

        if (menuHeight != 0) {
            jMenuBar = new JMenuBar();
            jMenuBar.add(Box.createRigidArea(new Dimension(0, menuHeight)));

            menuBarDefinition = new MenuBarDefinition(jMenuBar);
            frame.setJMenuBar(jMenuBar);
        }

        /*frame.setLayout(null);*/
        frame.add(jDesktopPane);
        frame.setContentPane(jDesktopPane);
        jDesktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        frame.setVisible(true);


        windowButtonDefinition = new WindowButtonsDefinition(frame);
        windowLabelDefinition = new WindowLabelsDefinition(frame);
        windowTextFieldsDefinition = new WindowTextFieldsDefinition(frame);
    }

    public JFrame getJFrame(){
        return frame;
    }

    public JDesktopPane getDesktop(){
        return jDesktopPane;
    }

    public BaseWindow(){
        this(0,0,100,100,0);
    }

    public void addButton(String key, String text, int x, int y, int width, int height){
        windowButtonDefinition.add(key, text, x, y,width, height);
    }

    public void addButtonListener(String key, ElementActionListener elementActionListener){
        windowButtonDefinition.addActionListener(key, elementActionListener);
    }

    public void addLabel(String key, String text, int x, int y, int width, int height){
        windowLabelDefinition.add(key, text, x, y,width, height);
    }

    public void addTextField(String key, String text, int x, int y, int width, int height){
        windowTextFieldsDefinition.add(key, text, x, y,width, height);
    }

    public void addTextFieldListener(String key, ElementActionListener elementActionListener){
        windowTextFieldsDefinition.addActionListener(key, elementActionListener);
    }

    public void addTextFieldToolTip(String key, String text){
        windowTextFieldsDefinition.addToolTipText(key, text);
    }

    public void repaint(){
        frame.repaint();
    }
    public void setWindowVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public void addMenu(String keyMenu, String name){
        menuBarDefinition.addMenu(keyMenu, name);

        //repaint Menu bar
        frame.setJMenuBar(jMenuBar);
    }
    public void addInternalFrame(JInternalFrame jInternalFrame){
        jDesktopPane.add(jInternalFrame);
    }
    public void addMenuItem(String keyMenu, String keyItem, String name){
        menuBarDefinition.addMenuItem(keyMenu, keyItem, name);
    }

    public void addSubMenu(String keyMenu, String keySubmenu, String name){
        menuBarDefinition.addSubMenu(keyMenu, keySubmenu, name);
    }

    public void addSubMenuItem(String keyMenu, String keySubmenu, String keySubmenuItem, String name){
        menuBarDefinition.addSubMenuItem(keyMenu, keySubmenu, keySubmenuItem, name);
    }

    public void addMenuListener(String keyMenu, MenuListener menuListener){
        menuBarDefinition.addMenuListener(keyMenu, menuListener);
    }

    public void addMenuItemActionListener(String keyMenu, String keyItem, ElementActionListener elementActionListener){
        menuBarDefinition.addMenuItemActionListener(keyMenu, keyItem, elementActionListener);
    }

    public void addSubMenuListener(String keyMenu, String keySubMenu, MenuListener menuListener){
        menuBarDefinition.addSubMenuListener(keyMenu, keySubMenu, menuListener);
    }

    public void addSubMenuItemActionListener(String keyMenu, String keySubMenu, String keySubMenuItem, ElementActionListener elementActionListener){
        menuBarDefinition.addSubMenuItemActionListener(keyMenu, keySubMenu, keySubMenuItem, elementActionListener);
    }
}