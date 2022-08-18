package lib.ui.templates;


import lib.ui.templates.window.button.WindowButtonsDefinition;
import lib.ui.templates.window.label.WindowLabelsDefinition;
import lib.ui.templates.window.menu.MenuBarDefinition;
import lib.ui.templates.window.menu.MenuDefinition;
import lib.ui.templates.window.textField.WindowTextFieldsDefinition;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BaseWindow extends  JFrame{
    private JMenuBar jMenuBar;
    JDesktopPane jDesktopPane = new JDesktopPane();

    /*Item definition base window*/
    private MenuBarDefinition menuBarDefinition;
    private WindowButtonsDefinition windowButtonDefinition;
    private WindowLabelsDefinition windowLabelDefinition;
    private WindowTextFieldsDefinition windowTextFieldsDefinition;

    private HashMap<String, JPanel> panelHashMap = new HashMap<String, JPanel>();

    public BaseWindow(String title, int x, int y, int width, int height, int menuHeight) {
        this.setTitle(title);
        /*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(true);
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(10,10));
        this.setLocation(x, y);

        if (menuHeight != 0) {
            jMenuBar = new JMenuBar();
            jMenuBar.add(Box.createRigidArea(new Dimension(0, menuHeight)));

            menuBarDefinition = new MenuBarDefinition(jMenuBar);
            this.setJMenuBar(jMenuBar);
        }

        /*frame.setLayout(null);*/
        this.setContentPane(jDesktopPane);
        /*jPanel.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);*/
        this.setVisible(true);


        windowButtonDefinition = new WindowButtonsDefinition(this);
        windowLabelDefinition = new WindowLabelsDefinition(this);
        windowTextFieldsDefinition = new WindowTextFieldsDefinition(this);
    }

    public void updateWindowIcon(String filename){
        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(filename));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JFrame getJFrame(){
        return this;
    }

    public JDesktopPane getDesktopPane(){
        return jDesktopPane;
    }

    public BaseWindow(){
        this("BaseWindow",0,0,100,100,0);
    }

    public void addButton(String key, String text, int x, int y, int width, int height){
        windowButtonDefinition.add(key, text, x, y,width, height);
    }

    public void addButtonListener(String key, ActionListener actionListener){
        windowButtonDefinition.addActionListener(key, actionListener);
    }

    public void addLabel(String key, String text, int x, int y, int width, int height){
        windowLabelDefinition.add(key, text, x, y,width, height);
    }

    public void addTextField(String key, String text, int x, int y, int width, int height){
        windowTextFieldsDefinition.add(key, text, x, y,width, height);
    }

    public void addTextFieldListener(String key, ActionListener actionListener){
        windowTextFieldsDefinition.addActionListener(key, actionListener);
    }

    public void addTextFieldToolTip(String key, String text){
        windowTextFieldsDefinition.addToolTipText(key, text);
    }

    public void addMenu(String keyMenu, String name, int width, int height){
        menuBarDefinition.addMenu(keyMenu, name, width, height);

        //repaint Menu bar
        this.setJMenuBar(jMenuBar);
    }

    public void addMenuHorizontalGlue(){
        menuBarDefinition.addMenuHorizontalGlue();
    }

    public void addInternalFrame(JInternalFrame jInternalFrame){
        jDesktopPane.add(jInternalFrame);

        this.setContentPane(jDesktopPane);
    }
    public void addMenuItem(String keyMenu, String keyItem, String name, int width, int height){
        menuBarDefinition.addMenuItem(keyMenu, keyItem, name, width, height);
    }

    public void setMenuItemEnable(String keyMenu, String keyMenuItem, boolean enable){
        menuBarDefinition.setMenuItemEnable(keyMenu, keyMenuItem, enable);
    }

    public void addSubMenu(String keyMenu, String keySubmenu, String name, int width, int height){
        menuBarDefinition.addSubMenu(keyMenu, keySubmenu, name, width, height);
    }

    public void addSubMenuItem(String keyMenu, String keySubmenu, String keySubmenuItem, String name){
        menuBarDefinition.addSubMenuItem(keyMenu, keySubmenu, keySubmenuItem, name);
    }

    public String getSubMenuItemName(String keyMenu, String keySubMenu, String keySubMenuItem){
        return menuBarDefinition.getSubMenuItemName(keyMenu, keySubMenu, keySubMenuItem);
    }

    public void setMenuEnable(String keyMenu, boolean enable){
        menuBarDefinition.setMenuEnable(keyMenu, enable);
    }

    public void setMenuText(String keyMenu, String text){
        menuBarDefinition.setMenuText(keyMenu, text);
    }

    public void addMenuListener(String keyMenu, MenuListener menuListener){
        menuBarDefinition.addMenuListener(keyMenu, menuListener);
    }

    public void addMenuItemActionListener(String keyMenu, String keyItem, ActionListener actionListener){
        menuBarDefinition.addMenuItemActionListener(keyMenu, keyItem, actionListener);
    }

    public void addSubMenuListener(String keyMenu, String keySubMenu, MenuListener menuListener){
        menuBarDefinition.addSubMenuListener(keyMenu, keySubMenu, menuListener);
    }

    public void addSubMenuItemActionListener(String keyMenu, String keySubMenu, String keySubMenuItem, ActionListener actionListener){
        menuBarDefinition.addSubMenuItemActionListener(keyMenu, keySubMenu, keySubMenuItem, actionListener);
    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("Garbage collector in action! Deleted one BaseWindow object;");
    }
}