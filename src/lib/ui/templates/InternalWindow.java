package lib.ui.templates;

import lib.ui.libClass.Swing.ScrollPane.Corner;
import lib.ui.libClass.Swing.ScrollPane.Rule;
import lib.ui.libClass.Swing.ScrollPane.ScrollablePicture;
import lib.ui.templates.window.button.WindowInternalButtonsDefinition;
import lib.ui.templates.window.combobox.WindowInternalComboBoxDefinition;
import lib.ui.templates.window.label.WindowInternalLabelsDefinition;
import lib.ui.templates.window.progresBar.WindowInternalProgresBarDefinition;
import lib.ui.templates.window.slider.WindowInternalSlidersDefinition;
import lib.ui.templates.window.textField.WindowInternalTextFieldsDefinition;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class InternalWindow extends JInternalFrame{
    private BaseWindow baseWindow;
    private JSplitPane jSplitPane;
    private JComponent jComponent = new JDesktopPane();

    private HashMap<String, JComponent> componentHashMap = new HashMap<String, JComponent>();

    private WindowInternalButtonsDefinition windowInternalButtonsDefinition;
    private WindowInternalLabelsDefinition windowInternalLabelsDefinition;
    private WindowInternalTextFieldsDefinition windowInternalTextFieldsDefinition;

    private WindowInternalProgresBarDefinition windowInternalProgresBarDefinition;

    private WindowInternalComboBoxDefinition windowInternalComboBoxDefinition;

    private WindowInternalSlidersDefinition windowInternalSlidersDefinition;
    public InternalWindow(BaseWindow baseWindow, String title, int x, int y, int width, int height) {
        this.baseWindow = baseWindow;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setTitle(title);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);

        this.setSize(width, height);
        this.setPreferredSize(new Dimension(10,10));
        this.setLocation(x, y);

        this.setLayout(null);
        this.setVisible(true);

        baseWindow.addInternalFrame(this);

        windowInternalLabelsDefinition = new WindowInternalLabelsDefinition(this);
        windowInternalButtonsDefinition = new WindowInternalButtonsDefinition(this);
        windowInternalTextFieldsDefinition = new WindowInternalTextFieldsDefinition(this);
        windowInternalComboBoxDefinition = new WindowInternalComboBoxDefinition(this);
        windowInternalSlidersDefinition = new WindowInternalSlidersDefinition(this);
        windowInternalProgresBarDefinition = new WindowInternalProgresBarDefinition(this);

        this.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                clearAllComponentsInternalWindow();
            }
        });
    }

    public InternalWindow(BaseWindow baseWindow, String title){
        this(baseWindow, title, 0,0,100,100);
    }


    public void addSlider(String keyDesktopPane, String key, int x, int y, int width, int height, int minVal, int maxVal, int initValue){
        JComponent jComponent = getComponentPane(keyDesktopPane);
        if (jComponent == null) {jComponent = createDesktopPaneComponent(keyDesktopPane);}

        windowInternalSlidersDefinition.add(jComponent, key, x, y,width, height, minVal, maxVal, initValue);

        addComponentToFrame(jComponent, false);
    }

    public void setSliderEnable(String key, boolean enable){
        windowInternalSlidersDefinition.setSliderEnable(key, enable);
    }

    public void setSliderValue(String key, int value){
        windowInternalSlidersDefinition.setValue(key, value);
    }

    public int getSliderValue(String key){
        return windowInternalSlidersDefinition.getValue(key);
    }

    public void addSliderListener(String key, ChangeListener changeListener){
        windowInternalSlidersDefinition.addChangeListener(key, changeListener);
    }

    public void addProgressBar(String keyDesktopPane, String key, int maxValue, int x, int y, int width, int height){
        JComponent jComponent = getComponentPane(keyDesktopPane);
        if (jComponent == null) {jComponent = createDesktopPaneComponent(keyDesktopPane);}

        windowInternalProgresBarDefinition.add(jComponent, key, maxValue, x, y,width, height);

        addComponentToFrame(jComponent, false);
    }

    public void setProgressBarValue(String keyProgressBar, int value){
        windowInternalProgresBarDefinition.setValue(keyProgressBar, value);
    }

    public void addButton(String keyDesktopPane, String key, String text, int x, int y, int width, int height){
        JComponent jComponent = getComponentPane(keyDesktopPane);
        if (jComponent == null) {jComponent = createDesktopPaneComponent(keyDesktopPane);}

        windowInternalButtonsDefinition.add(jComponent, key, text, x, y,width, height);

        addComponentToFrame(jComponent, false);
    }

    public void setButtonEnable(String key, boolean enable){
        windowInternalButtonsDefinition.setButtonEnable(key, enable);
    }

    public void addButtonListener(String key, ActionListener actionListener){
        windowInternalButtonsDefinition.addActionListener(key, actionListener);
    }

    public void addLabel(String keyDesktopPane, String key, String text, int x, int y, int width, int height){
        JComponent jComponent = getComponentPane(keyDesktopPane);
        if (jComponent == null) {jComponent = createDesktopPaneComponent(keyDesktopPane);}
        windowInternalLabelsDefinition.add(jComponent, key, text, x, y,width, height);

        addComponentToFrame(jComponent, false);
    }

    public void addLabelAsImage(String keyDesktopPane, String keyLabel, String imagePath, int x, int y, int width, int height){
        JComponent jComponent = getComponentPane(keyDesktopPane);
        if (jComponent == null) {jComponent = createDesktopPaneComponent(keyDesktopPane);}
        windowInternalLabelsDefinition.addLabelAsImage(jComponent, keyLabel, imagePath, x, y,width, height);

        addComponentToFrame(jComponent, false);
    }

    public void updateLabelImage(String keyComponent, String keyLabel, String imagePath){
        windowInternalLabelsDefinition.updateLabelImage(keyLabel, imagePath);

        JComponent jComponent = componentHashMap.get(keyComponent);

        if (jComponent instanceof JScrollPane) {
            JScrollPane jScrollPaneNew = (JScrollPane) jComponent;

            try {
                    BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
                    ImageIcon icon = new ImageIcon(bufferedImage);

                    Rule columnView = new Rule(Rule.HORIZONTAL, true);

                    if (icon != null) {
                        ScrollablePicture picture = new ScrollablePicture(icon, columnView.getIncrement());

                        JViewport viewport = jScrollPaneNew.getViewport();

                        viewport.setView(picture);
                    }

                    icon = null;
                    bufferedImage = null;
                    System.gc();

            } catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }



    public void addSplitPain(int swingConstant, String keyDesktoppane1, String keyDesktoppane2, int dividerPosition){
        JComponent jComponent1 = getComponentPane(keyDesktoppane1);
        if (jComponent1 == null) {jComponent1 = createDesktopPaneComponent(keyDesktoppane1);}

        JComponent jComponent2 = getComponentPane(keyDesktoppane2);
        if (jComponent2 == null) {jComponent2 = createDesktopPaneComponent(keyDesktoppane2);}

        if (jSplitPane == null) {
            jSplitPane = new JSplitPane(swingConstant, jComponent1, jComponent2);
        } else {
            jSplitPane = new JSplitPane(swingConstant, jSplitPane, jComponent2);
        }
        jSplitPane.setDividerLocation(dividerPosition);
        this.setContentPane(jSplitPane);
    }

    public void addTextField(String keyDesktopPane, String keyTextField, String text, int x, int y, int width, int height){
        JComponent jDesktopPane = getComponentPane(keyDesktopPane);
        if (jDesktopPane == null) {jDesktopPane = createDesktopPaneComponent(keyDesktopPane);}

        windowInternalTextFieldsDefinition.add(jDesktopPane, keyTextField, text, x, y,width, height);

        addComponentToFrame(jDesktopPane, false);
    }

    public void setTextFieldFormat(String keyTextField, byte format){
        windowInternalTextFieldsDefinition.setTextFieldFormat(keyTextField, format);
    }

    public void setTextFieldEnable(String key, boolean enable){
        windowInternalTextFieldsDefinition.setTextFieldEnable(key, enable);
    }

    public String getTextFieldData(String keyTextField){
        return windowInternalTextFieldsDefinition.getText(keyTextField);
    }

    public void setTextFieldData(String keyTextField, Object object){
        windowInternalTextFieldsDefinition.setTextFieldData(keyTextField, object);
    }

    public void addScrolTextField(String keyScrolPane, String keyTextField, String text, int x, int y, int width, int height){
        JComponent jScrolPane = getComponentPane(keyScrolPane);
        if (jScrolPane == null) {jScrolPane = createDesktopPaneComponent(keyScrolPane);}

        windowInternalTextFieldsDefinition.add(jScrolPane, keyTextField, text, x, y,width, height);

        addComponentToFrame(jScrolPane, false);
    }

    public void addTextFieldListener(String keyTextField, ActionListener actionListener){
        windowInternalTextFieldsDefinition.addActionListener(keyTextField, actionListener);
    }

    public void addTextFieldDocumentListener(String keyTextField, DocumentListener documentListener){
        windowInternalTextFieldsDefinition.addDocumentListener(keyTextField, documentListener);
    }

    public void addTextFieldFocusListener(String keyTextField, FocusAdapter focusAdapter){
        windowInternalTextFieldsDefinition.addFocusListener(keyTextField, focusAdapter);
    }

    public void addTextFieldKeyListener(String keyTextField, KeyListener keyListener){
        windowInternalTextFieldsDefinition.addKeyListener(keyTextField, keyListener);
    }

    public void addTextFieldToolTip(String key, String text){
        /*windowTextFieldsDefinition.addToolTipText(key, text);*/
    }

    public void setWindowVisible(boolean isVisible) {
        this.setVisible(isVisible);
    }

    public void addComboBox(String keyDesktopPane, String keyComboBox, String[] texts, int x, int y, int width, int height){
        JComponent jDesktopPane = getComponentPane(keyDesktopPane);
        if (jDesktopPane == null) {jDesktopPane = createDesktopPaneComponent(keyDesktopPane);}

        windowInternalComboBoxDefinition.add(jDesktopPane, keyComboBox, texts, x, y,width, height);

        addComponentToFrame(jDesktopPane, false);
    }

    public void chooseComboBoxItem(String keyComboBox, Object object){
        windowInternalComboBoxDefinition.chooseComboBoxItem(keyComboBox, object);
    }

    public void updateComboBoxItems(String keyComboBox, String[] data) {
        windowInternalComboBoxDefinition.clearComboBoxItems(keyComboBox);

        for(String s : data){
            addComboBoxItem(keyComboBox, s);
        }
    }

    public void addComboBoxItem(String keyComboBox, Object object) {
        windowInternalComboBoxDefinition.addComboBoxItem(keyComboBox, object);
    }

    public void addComboBoxActionListener(String keyTComboBox, ActionListener actionListener) {
        windowInternalComboBoxDefinition.addActionListener(keyTComboBox, actionListener);
    }

    public Object getComboBoxSelectedItem(String keyTComboBox) {
        return windowInternalComboBoxDefinition.getComboBoxSelectedItem(keyTComboBox);
    }

    public void setComboBoxEnable(String key, boolean enable){
        windowInternalComboBoxDefinition.setComboBoxEnable(key, enable);
    }

    public void saveComponentAsImage(String componentKey, String formatName)
    {
        Component component = getComponentPane(componentKey);

        if (component instanceof JScrollPane)  {
            //isn't possible get size scrolpane component
        } else {

            Dimension size = component.getSize();
            BufferedImage image = new BufferedImage(
                    size.width, size.height
                    , BufferedImage.TYPE_INT_RGB);

            Graphics2D g2 = image.createGraphics();
            jComponent.paint(g2);

            try {
                ImageIO.write(image, formatName, new File("snapshot." + formatName));
                System.out.println("Panel saved as Image.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addScrolPaneComponent(String keyScrollPane, String keyDesktopPane, boolean addScale){
        JComponent jDesktopPane = getComponentPane(keyDesktopPane);
        if (jDesktopPane == null) {jDesktopPane = createDesktopPaneComponent(keyDesktopPane);}

        JPanel pContainer = new JPanel();

        JComponent jScrollPane = getComponentPane(keyScrollPane);
        if (jScrollPane == null) {jScrollPane = createScrollPaneComponent(keyDesktopPane, pContainer);}


        componentHashMap.put(keyScrollPane, jScrollPane);

        boolean isRight = getLocationComponentSplitPane(jDesktopPane);
        addComponentToFrame(jScrollPane, isRight);
    }

    public void addScrolPaneOneComponent(String keyScrollPane, String keyDesktopPane, boolean addScale){
        JComponent jDesktopPane = getComponentPane(keyDesktopPane);
        if (jDesktopPane == null) {jDesktopPane = createDesktopPaneComponent(keyDesktopPane);}


        JPanel pContainer = new JPanel(new GridBagLayout());

        ImageIcon icon = null;
        for (Component component : jDesktopPane.getComponents()) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = component.getBounds().x;
            c.gridy = component.getBounds().y;

            pContainer.add(component, c);

            if (component instanceof JLabel) { // possibly jscroll for picture
                JLabel lbl = (JLabel) component;

                icon = (ImageIcon) lbl.getIcon();
            }
        }

        JComponent jScrollPane = getComponentPane(keyScrollPane);
        if (jScrollPane == null) {jScrollPane = createScrollPaneComponent(keyDesktopPane, pContainer);}

        JScrollPane jScrollPaneNew = null;

        if (addScale) {
            Rule columnView = new Rule(Rule.HORIZONTAL, true);
            Rule rowView = new Rule(Rule.VERTICAL, true);


            if (icon != null) {
                ScrollablePicture picture = new ScrollablePicture(icon, columnView.getIncrement());
                jScrollPaneNew = new JScrollPane(picture);

                if (icon != null) {
                    columnView.setPreferredWidth(icon.getIconWidth());
                    rowView.setPreferredHeight(icon.getIconHeight());
                } else {
                    columnView.setPreferredWidth(320);
                    rowView.setPreferredHeight(480);
                }
            } else {
                jScrollPaneNew = (JScrollPane) jScrollPane;
            }



            jScrollPaneNew.setColumnHeaderView(columnView);
            jScrollPaneNew.setRowHeaderView(rowView);

            jScrollPaneNew.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                    new Corner());
            jScrollPaneNew.setCorner(JScrollPane.LOWER_LEFT_CORNER,
                    new Corner());
            jScrollPaneNew.setCorner(JScrollPane.UPPER_RIGHT_CORNER,
                    new Corner());

            jScrollPaneNew.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        } else {
            jScrollPaneNew = (JScrollPane) jScrollPane;
        }

        jScrollPaneNew.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneNew.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);



        componentHashMap.put(keyScrollPane, jScrollPaneNew);

        boolean isRight = getLocationComponentSplitPane(jDesktopPane);
        addComponentToFrame(jScrollPaneNew, isRight);

        icon = null;
        System.gc();

    }



    private void clearAllComponentsInternalWindow(){
        baseWindow = null;
        jSplitPane = null;
        jComponent = null;

        componentHashMap = null;

        windowInternalButtonsDefinition = null;
        windowInternalLabelsDefinition = null;
        windowInternalTextFieldsDefinition = null;
        windowInternalComboBoxDefinition = null;
        windowInternalSlidersDefinition = null;

        this.removeAll();
        this.dispose();
    }



    private void addComponentToFrame(JComponent jComponent, boolean isSplitRight){
        if (jSplitPane == null && !this.jComponent.equals(jComponent)) {
            this.add(jComponent);
            this.setContentPane(jComponent);
            this.jComponent = jComponent;
        } else if (jSplitPane != null) {
            int dividerPosition = jSplitPane.getDividerLocation();

            if (isSplitRight) {
                jSplitPane.setRightComponent(jComponent);
            } else {
                jSplitPane.setLeftComponent(jComponent);
            }
            jSplitPane.setDividerLocation(dividerPosition);
        }
    }

    private JComponent getComponentPane(String paneKey){
        return componentHashMap.get(paneKey);
    }

    private JDesktopPane initDesktopPaneAsFileBackground(JDesktopPane mydesktop, final String imagePath/*, final int scalx, final int scaly*/) {

        // A specialized layered pane to be used with JInternalFrames
        mydesktop = new JDesktopPane() {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        return mydesktop;
    }

    private JComponent createDesktopPaneComponent(String paneKey){
        JDesktopPane jDesktopPane = new JDesktopPane();
        /*jDesktopPane = initDesktopPaneAsFileBackground(jDesktopPane, "smile.jpg");*/

        JComponent jComponent = (JComponent) jDesktopPane;

        componentHashMap.put(paneKey, jComponent);
        return jComponent;
    }

    private JComponent createScrollPaneComponent(String paneKey, JPanel pContainer){
        JComponent jScrolPane = new JScrollPane(pContainer);
        componentHashMap.put(paneKey, jScrolPane);
        return jScrolPane;
    }

    /*false - left, true - right*/
    private boolean getLocationComponentSplitPane(JComponent component){
        int i = 0;
        if (isSplitPaneExist()) {
            for (Component c : jSplitPane.getComponents()) {
                i++;
                if (component.equals(c)) {
                    break;
                }
            }
        }

        return i != 1;
    }

    private boolean isSplitPaneExist(){
        return jSplitPane != null;
    }

    @Override
    protected void finalize() throws Throwable
    {
        /*System.out.println("Garbage collector in action! Deleted InternalWindow object!");*/
    }
}