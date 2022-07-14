package main.java;

import main.java.controller.HomeController;
import main.java.controller.MyFirstMenuI1tem1Controller;
import main.java.controller.MyFirstTextFieldActionController;
import main.java.ui.templates.window.ElementActionControler;
import main.java.ui.templates.window.ElementActionListener;
import main.java.controller.MyFirstButtonActionController;
import main.java.ui.templates.BaseWindow;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.io.IOException;



public class Main {
        public static void main(String[] args) {
                HomeController mn = HomeController.getInstance();

                BaseWindow baseWindow = new BaseWindow(200,0,500,500, 25);
                baseWindow.addLabel("MyFirstLabel","Label", 0,0,100,100);

                baseWindow.addButton("MyFirstButton","1", 0,100,100,100);
                baseWindow.addButtonListener("MyFirstButton", new ElementActionListener(new MyFirstButtonActionController()));

                baseWindow.addButton ("MySecondButton","2", 200,100,100,100);
                baseWindow.addButtonListener("MySecondButton", new ElementActionListener(new ElementActionControler() {
                        @Override
                        public void doMethod() {
                                System.out.println("Second controller");
                        }
                }));



                baseWindow.addTextField("MyFirstTextField","Text field", 100,200,100,100);
                baseWindow.addTextFieldListener("MyFirstTextField", new ElementActionListener(new MyFirstTextFieldActionController()));
                baseWindow.addTextFieldToolTip("MyFirstTextField", "Tooltip");

                baseWindow.addMenu("MyMenu1", "First menu");
                baseWindow.addMenu("MyMenu2", "Second menu");
                baseWindow.addMenuItem("MyMenu1", "MyMenu1Item1", "Item 1");
                baseWindow.addMenuItemActionListener("MyMenu1", "MyMenu1Item1", new ElementActionListener(new MyFirstMenuI1tem1Controller()));
                baseWindow.addMenuItem("MyMenu1", "MyMenu1Item2", "Item 2");
                baseWindow.addSubMenu("MyMenu1", "MyMenu1Sub1", "Submenu1");
                baseWindow.addSubMenuItem("MyMenu1", "MyMenu1Sub1","MyMenu1Sub1Item1","Item 1");
                baseWindow.addSubMenuItemActionListener("MyMenu1", "MyMenu1Sub1","MyMenu1Sub1Item1", new ElementActionListener(new MyFirstMenuI1tem1Controller()));

                baseWindow.repaint();
        }
}
