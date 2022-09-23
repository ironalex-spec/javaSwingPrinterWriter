package lib.ui.screens.information;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrinterAppInfoWindow extends JOptionPane {
    private static JFrame jFrame = new JFrame();
    public static boolean showErrorWindow(String title, String text){

        JOptionPane.showMessageDialog(jFrame, text, title,
                JOptionPane.ERROR_MESSAGE);

        return true;
    }

    public static boolean showAppInfoWindow(String title, String text) {
        try {
            Image image = ImageIO.read(new File("src/resources/img/myimage.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);

            ImageIcon icon =  new ImageIcon(image);

            JOptionPane.showMessageDialog(jFrame, text, title,
                    JOptionPane.PLAIN_MESSAGE, icon);

        } catch (IOException ioException){
            ioException.printStackTrace();
        }

        return false;
    }

}
