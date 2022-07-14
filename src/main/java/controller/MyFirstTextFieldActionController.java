package main.java.controller;

import main.java.ui.templates.window.ElementActionControler;

public class MyFirstTextFieldActionController implements ElementActionControler {
    @Override
    public void doMethod() {
        System.out.println("My first text field controller");
    }
}
