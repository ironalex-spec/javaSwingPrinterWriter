package main.java.ui.templates.window;

import main.java.ui.templates.window.ElementActionControler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementActionListener implements ActionListener {
    private ElementActionControler ap;

    public ElementActionListener(ElementActionControler ap){
        this.ap = ap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ap.doMethod();
    }
}
