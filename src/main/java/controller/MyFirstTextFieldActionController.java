package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFirstTextFieldActionController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("My first text field controller");
    }
}
