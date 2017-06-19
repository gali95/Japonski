import GUI.DrawingPanel;
import GUI.ImagePanel;
import alphabet_logic.CharacterRandomizer;
import alphabet_logic.Hiragana;
import alphabet_logic.Katakana;
import tests.ExampleKanjiTest;
import tests.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Created by gali95 on 12.08.16.
 */


public class mainApp {
    private JTextField textField1;
    private JButton hiraganaButton;
    private JButton katakanaButton;
    private JPanel elo;
    private JTextArea textArea1;
    private JButton kaiji20Button;
    private JPanel custome;
    private JPanel imgPanel;
    private JPanel drawPanel;
    private JButton generujButton;
    private JButton openButton;
    private JButton saveButton;
    private JLabel fragmLabel;
    private JButton AdnoButton;
    private JLabel adnoLabel;

    private ActionListener fajny;
    private MouseListener mousy;

    private ImagePanel obrazek;

    private Test[] partialTest;

    public void PrintAdno() {
        adnoLabel.setText(partialTest[0].adno);
    }

    public void AdnoButton() {
        partialTest[0].adno = textField1.getText();
        PrintAdno();
    }

    public void SavePartialButton() {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("partial");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(partialTest);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OpenPartialButton() {
        ObjectInputStream in;
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("partial");
            in = new ObjectInputStream(fileIn);
            partialTest = (Test[]) in.readObject();

            PrintPartialLenght();
            PrintAdno();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

    public void GeneratePartialButton() {
        partialTest = ExampleKanjiTest.GetPartedTest(Integer.valueOf(textField1.getText()));
        PrintPartialLenght();
    }

    public void PrintPartialLenght() {
        fragmLabel.setText(Integer.toString(partialTest.length));
    }

    public void VisualTestWithPartialButton() {
        JFrame frame = new JFrame("tescik");
        visualTest anotherTest = new visualTest(partialTest[Integer.valueOf(textField1.getText())], frame);
        frame.setContentPane(anotherTest.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //moj.custome = new GUI.CustomJPanel();
        frame.pack();

        frame.setVisible(true);
    }

    public mainApp() {
        $$$setupUI$$$();
        fajny = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                JButton sourceBtn = (JButton) actionEvent.getSource();

                if (sourceBtn == hiraganaButton) {

                    textArea1.setText(CharacterRandomizer.GetExampleAll((new Hiragana()).content));
                    obrazek.SetImage("images.png");
                } else if (sourceBtn == katakanaButton) {

                    textArea1.setText(CharacterRandomizer.GetExampleAll((new Katakana()).content));
                    obrazek.SetImage("images2.png");

                } else if (sourceBtn == kaiji20Button) {

                    /*
                    JFrame frame = new JFrame("tescik");
                    visualTest anotherTest = new visualTest(tests.ExampleKanjiTest.GetTest(),frame);
                    frame.setContentPane(anotherTest.mainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //moj.custome = new GUI.CustomJPanel();
                    frame.pack();

                    frame.setVisible(true);
                    */
                    VisualTestWithPartialButton();


                } else if (sourceBtn == generujButton) {
                    GeneratePartialButton();
                } else if (sourceBtn == saveButton) {
                    SavePartialButton();
                } else if (sourceBtn == openButton) {
                    OpenPartialButton();
                } else if (sourceBtn == AdnoButton) {
                    AdnoButton();
                }
            }


        };

        hiraganaButton.addActionListener(fajny);
        katakanaButton.addActionListener(fajny);
        kaiji20Button.addActionListener(fajny);
        generujButton.addActionListener(fajny);
        saveButton.addActionListener(fajny);
        openButton.addActionListener(fajny);
        AdnoButton.addActionListener(fajny);


    }


    public static void main(String[] args) {
        mainApp moj = new mainApp();
        JFrame frame = new JFrame("menu");
        frame.setContentPane(moj.elo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //moj.custome = new GUI.CustomJPanel();
        frame.pack();

        frame.setVisible(true);


    }

    private void createUIComponents() {

        drawPanel = new DrawingPanel();

        obrazek = new ImagePanel();
        obrazek.SetImage("images.png");
        imgPanel = obrazek;
        //imgPanel.setBackground(new java.awt.Color(255, 255, 255));
        //imgPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        elo = new JPanel();
        elo.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        elo.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        hiraganaButton = new JButton();
        hiraganaButton.setText("alphabet_logic.Hiragana");
        panel1.add(hiraganaButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        katakanaButton = new JButton();
        katakanaButton.setText("alphabet_logic.Katakana");
        panel1.add(katakanaButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        kaiji20Button = new JButton();
        kaiji20Button.setText("alphabet_logic.Kaiji (20)");
        panel1.add(kaiji20Button, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        custome = new JPanel();
        custome.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(custome, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField1 = new JTextField();
        custome.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        generujButton = new JButton();
        generujButton.setText("Generuj");
        panel1.add(generujButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        openButton = new JButton();
        openButton.setText("Open");
        panel1.add(openButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel1.add(saveButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fragmLabel = new JLabel();
        fragmLabel.setText("fragm: ");
        panel1.add(fragmLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AdnoButton = new JButton();
        AdnoButton.setText("SetAdno");
        panel1.add(AdnoButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        elo.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textArea1 = new JTextArea();
        panel2.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 300), new Dimension(600, 300), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imgPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(imgPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1, 1), new Dimension(300, 300), new Dimension(300, 300), 0, false));
        adnoLabel = new JLabel();
        adnoLabel.setText("adnotation:");
        imgPanel.add(adnoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3.add(drawPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1, 1), new Dimension(300, 300), new Dimension(300, 300), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return elo;
    }
}
