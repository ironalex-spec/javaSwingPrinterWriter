package test.java;

import main.java.controller.HomeController;
import main.java.controller.MyFirstMenuI1tem1Controller;
import main.java.controller.MyFirstTextFieldActionController;
import main.java.ui.templates.InternalWindow;
import main.java.ui.templates.window.ElementActionControler;
import main.java.ui.templates.window.ElementActionListener;
import main.java.controller.MyFirstButtonActionController;
import main.java.ui.templates.BaseWindow;

import javax.swing.*;


public class TestInternalWindow {
    public static void main(String[] args) {
        HomeController mn = HomeController.getInstance();

        BaseWindow baseWindow = new BaseWindow(200,0,500,500, 25);

        InternalWindow internalWindow = new InternalWindow(baseWindow, "Internal1*", 0,0,200,200);
        internalWindow.addLabel("Pane1","MyFirstLabel","Label11", 10,10,100,10);
        internalWindow.addLabel("Pane1","MyFirstLabel2","Label12", 10,20,100,10);
        internalWindow.addLabel("Pane2","MyFirstLabel3","Label13", 10,30,100,10);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane1", "Pane2", 100);

        InternalWindow internalWindow2 = new InternalWindow(baseWindow, "Internal2*", 10,10,200,200);
        internalWindow2.addLabel("Pane1","MyFirstLabel","Label21", 10,10,100,10);
        internalWindow2.addLabel("Pane1","MyFirstLabel2","Label22", 10,20,100,10);
        internalWindow2.addLabel("Pane1","MyFirstLabel3","Label23", 10,30,100,10);

        baseWindow.repaint();
    }
}
