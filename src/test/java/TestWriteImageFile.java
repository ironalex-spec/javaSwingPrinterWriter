package test.java;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TestWriteImageFile {
    private JPanel contentPane;

    private void displayGUI()
    {
        JFrame frame = new JFrame("Snapshot Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        JLabel label = new JLabel("This JLabel will display"
                + " itself on the SNAPSHOT", JLabel.CENTER);
        contentPane.add(label);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        makePanelImage(contentPane);
    }

    private void makePanelImage(Component panel)
    {
        Dimension size = panel.getSize();
        BufferedImage image = new BufferedImage(
                size.width, size.height
                , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);

        try
        {
            ImageIO.write(image, "png", new File("snapshot.png"));
            System.out.println("Panel saved as Image.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TestWriteImageFile().displayGUI();
            }
        });
    }
}