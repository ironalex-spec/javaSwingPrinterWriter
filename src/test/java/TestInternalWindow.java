package test.java;

import main.java.controller.HomeController;
import main.java.controller.MyFirstMenuI1tem1Controller;
import main.java.controller.MyFirstTextFieldActionController;
import main.java.ui.templates.InternalWindow;
import main.java.ui.templates.window.ElementActionControler;
import main.java.ui.templates.window.ElementActionListener;
import main.java.controller.MyFirstButtonActionController;
import main.java.ui.templates.BaseWindow;


public class TestInternalWindow {
    public static void main(String[] args) {
        HomeController mn = HomeController.getInstance();

        BaseWindow baseWindow = new BaseWindow(200,0,500,500, 0);
        InternalWindow internalWindow = new InternalWindow(baseWindow, "Internal1", 0,0,200,200);
        internalWindow.addLabel("MyFirstLabel","Label1", 0,0,100,100);

        InternalWindow internalWindow2 = new InternalWindow(baseWindow, "Internal2", 10,10,200,200);
        internalWindow2.addLabel("MyFirstLabel","Label2", 10,10,100,100);

        /*internalWindow.addButton("MyFirstButton","1", 0,100,100,100);
        internalWindow.addButtonListener("MyFirstButton", new ElementActionListener(new MyFirstButtonActionController()));

        internalWindow.addButton ("MySecondButton","2", 200,100,100,100);
        internalWindow.addButtonListener("MySecondButton", new ElementActionListener(new ElementActionControler() {
            @Override
            public void doMethod() {
                System.out.println("Second controller");
            }
        }));*/

        baseWindow.repaint();
    }
}
