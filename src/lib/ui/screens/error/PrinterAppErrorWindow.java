package lib.ui.screens.error;

import javax.swing.*;

public class PrinterAppErrorWindow extends JOptionPane {
    private static JFrame jFrame = new JFrame();
    public static boolean showWindow(String title, String text){

        JOptionPane.showMessageDialog(jFrame, text, title,
                JOptionPane.ERROR_MESSAGE);

        return false;
    }

}
