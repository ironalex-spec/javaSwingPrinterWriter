package test.java;

import main.java.controller.HomeController;
import main.java.controller.MyFirstTextFieldActionController;
import main.java.ui.templates.InternalWindow;
import main.java.ui.templates.window.ElementActionListener;
import main.java.controller.MyFirstButtonActionController;
import main.java.ui.templates.BaseWindow;

import javax.swing.*;


public class TestInternalWindow {
    public static void main(String[] args) {
        HomeController mn = HomeController.getInstance();

        internalWindowAddImageWithScaleTest();
    }

    public static void internalWindowWithSplitAndScroll(){
        BaseWindow baseWindow = new BaseWindow(200,0,500,500, 25);

        InternalWindow internalWindow = new InternalWindow(baseWindow, "Internal1", 0,0,200,200);
        internalWindow.addLabel("Pane1","MyFirstLabel","Label11", 10,10,100,10);
        internalWindow.addLabel("Pane1","MyFirstLabel2","Label12", 10,20,100,10);
        internalWindow.addLabel("Pane2","MyFirstLabel3","Label13", 10,30,100,10);
        internalWindow.addButton("Pane1", "MyFirstButton","1", 0,100,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane2", "Pane1", 100);
        internalWindow.addScrolPaneOneComponent( "ScrolPane1", "Pane1", false);

        internalWindow.addTextField("Pane2", "MyFirstTextField","1", 100,200,100,100);
  /*      internalWindow.addSplitPain(SwingConstants.HORIZONTAL, "Pane1", "Pane3", 50);*/
        internalWindow.addButtonListener("MyFirstButton", new ElementActionListener(new MyFirstButtonActionController()));
        internalWindow.addTextFieldListener("MyFirstTextField", new ElementActionListener(new MyFirstTextFieldActionController()));
        internalWindow.addLabel("Pane2","MyFirstLabel4","Label14", 10,20,100,100);

        internalWindow.saveComponentAsImage("Pane2", "bmp");

        InternalWindow internalWindow2 = new InternalWindow(baseWindow, "Internal2", 10,10,200,200);
        internalWindow2.addLabel("Pane1","MyFirstLabel","Label21", 10,10,100,10);
        internalWindow2.addLabel("Pane1","MyFirstLabel2","Label22", 10,20,100,10);
        internalWindow2.addLabel("Pane1","MyFirstLabel3","Label23", 10,30,100,10);
        internalWindow2.addScrolPaneOneComponent( "ScrolPane1", "Pane1", false);

        internalWindow.repaint();
        baseWindow.repaint();
    }

    public static void addScrollPane(){
        BaseWindow baseWindow = new BaseWindow(200,0,500,500, 25);
        InternalWindow internalWindow = new InternalWindow(baseWindow, "Internal1*", 0,0,200,200);
        internalWindow.addLabel("Pane1","MyFirstLabel","Label11__________________________________________________________________" +
                "_____________________________________________________________" +
                "_____________________________________________________________", 10,20,500,50);
        internalWindow.addLabel("Pane1","MyFirstLabel2","Label12", 10,10,500,50);
        internalWindow.addLabel("Pane1","MyFirstLabel3","Label13", 10,30,500,50);

        internalWindow.addScrolPaneOneComponent( "ScrolPane1", "Pane1", false);
        internalWindow.addLabel("Pane2","MyFirstLabel3","Label13", 10,30,100,10);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane2", "Pane1", 100);

        /*internalWindow.repaint();
        baseWindow.repaint();*/
    }

    public static void internalWindowAddImageWithScaleTest(){
        BaseWindow baseWindow = new BaseWindow(0,0,500,500, 25);
        baseWindow.updateWindowIcon("src/main/resources/img/printerLogo.png");
        baseWindow.addLabel("Pane2","MyFirstLabel3", 10,30,100,10);

        InternalWindow internalWindow = new InternalWindow(baseWindow, "Internal1*", 0,0,500,500);
        internalWindow.addButton("Pane2","MyFirstLabel3","Label13", 10,30,100,10);
        internalWindow.addLabelAsImage("Pane1","MyFirstLabel","smile.jpg", 0,10,100,100);
        internalWindow.addSplitPain(SwingConstants.VERTICAL, "Pane2", "Pane1", 100);

        internalWindow.updateLabelImage("MyFirstLabel", "smile.jpg");
        internalWindow.addScrolPaneOneComponent( "ScrolPane1", "Pane1", true);
    }
}
