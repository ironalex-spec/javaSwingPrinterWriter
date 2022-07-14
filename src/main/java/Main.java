package main.java;

import main.java.controller.HomeController;
import main.java.controller.MyFirstTextFieldActionController;
import main.java.ui.templates.window.ElementActionControler;
import main.java.ui.templates.window.ElementActionListener;
import main.java.controller.MyFirstButtonActionController;
import main.java.ui.templates.Window;

import java.io.IOException;

import javax.print.*;



public class Main {
        public static void main(String[] args) throws PrintException, IOException {
                HomeController mn = HomeController.getInstance();

                Window window = new Window(200,0,500,500);
                window.addLabel("MyFirstLabel","Label", 0,0,100,100);

                window.addButton("MyFirstButton","1", 0,100,100,100);
                window.addButtonListener("MyFirstButton", new ElementActionListener(new MyFirstButtonActionController()));

                window.addButton ("MySecondButton","2", 200,100,100,100);
                window.addButtonListener("MySecondButton", new ElementActionListener(new ElementActionControler() {
                        @Override
                        public void doMethod() {
                                System.out.println("Second controller");
                        }
                }));



                window.addTextField("MyFirstTextField","Text field", 0,200,100,100);
                window.addTextFieldListener("MyFirstTextField", new ElementActionListener(new MyFirstTextFieldActionController()));

                window.repaint();
        }
}
