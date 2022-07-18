package test.java;

import javax.swing.*;
import java.awt.*;

public class TestSplitWindow {
    public static void main(String[] args){
        JFrame frame = new JFrame("Test internal divide");
        JInternalFrame jInternalFrame = new JInternalFrame("1 Internal frame", true, true, true, true);
        jInternalFrame.setSize(100,100);
        jInternalFrame.setPreferredSize(new Dimension(10,10));
        jInternalFrame.setVisible(true);

        JInternalFrame jInternalFrame2 = new JInternalFrame("2 Internal frame", true, true, true, true);
        jInternalFrame2.setSize(100,100);
        jInternalFrame2.setPreferredSize(new Dimension(10,10));
        jInternalFrame2.setVisible(true);

        // create a panel
        JDesktopPane pBase = new JDesktopPane();
        JDesktopPane pInter = new JDesktopPane();
        JDesktopPane pInter2 = new JDesktopPane();

        // create text areas
        JLabel t1 = new JLabel("10, 10");
        JLabel t2 = new JLabel("20, 10");

        t1.setBounds(0,0,100, 10);
        t2.setBounds(0,0,100, 100);
        // set texts
        t1.setText("this is first text area");
        t2.setText("this is second text area");

        JButton btn = new JButton("Test button");

        // add text area to panel
        jInternalFrame.add(t1);
        jInternalFrame2.add(t2);

        pInter.add(t1);
        JSplitPane sl = new JSplitPane(SwingConstants.VERTICAL, pInter, pInter2);
        sl.setDividerLocation(100);

        jInternalFrame.setContentPane(sl);
        pBase.add(jInternalFrame);
        pBase.add(jInternalFrame2);
        frame.add(pBase);

        // set the size of frame
        frame.setSize(300, 300);
        frame.setVisible(true);
/*        frame.repaint();*/
    }
}
