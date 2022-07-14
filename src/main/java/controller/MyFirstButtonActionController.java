package main.java.controller;

import main.java.ui.templates.window.ElementActionControler;

public class MyFirstButtonActionController implements ElementActionControler {
    @Override
    public void doMethod() {
        System.out.println("MyFirstButtonActionListener");
    }
}
