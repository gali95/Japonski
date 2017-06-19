import GUI.DrawingPanel;
import GUI.ImagePanel;
import main.Line;
import tests.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gali95 on 18.09.16.
 */
public class visualTest {
    private JButton poprzedniButton;
    private JButton nastepnyButton;
    private JButton poprawnoscButton;
    private JButton koniecButton;
    private JPanel ImgPanel;
    private JPanel DrawPanel;
    private JButton pokazUkryjRozwiazanieButton;
    private JLabel correctLabel;
    private JLabel nameLabel;
    private JLabel countLabel;
    public JPanel mainPanel;
    private JTextPane wrongOnesLabel;
    private JLabel scoreLabel;

    public JMenuBar menuBar;

    private ActionListener defaultListener;

    private ImagePanel img;

    private Test actualTest;

    public void setActualTest(Test actualTest) {
        if (actualTest.chars.length < 1) return;
        this.actualTest = actualTest;
        actualField = 0;
    }

    private int actualField;
    private Boolean showAnswers;

    public visualTest(Test actualTest, JFrame frame) {

        showAnswers = false;

        $$$setupUI$$$();
        defaultListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JButton source = (JButton) actionEvent.getSource();

                if (source == poprzedniButton) {
                    PoprzedniButtonPressed();
                } else if (source == nastepnyButton) {
                    NastepnyButtonPressed();
                } else if (source == pokazUkryjRozwiazanieButton) {
                    PokazUkryjRozwiazanieButton();
                } else if (source == poprawnoscButton) {
                    PoprawnoscButtonPressed();
                } else if (source == koniecButton) {
                    KoniecButtonPressed();
                }

            }
        };

        poprzedniButton.addActionListener(defaultListener);
        nastepnyButton.addActionListener(defaultListener);
        pokazUkryjRozwiazanieButton.addActionListener(defaultListener);
        poprawnoscButton.addActionListener(defaultListener);
        koniecButton.addActionListener(defaultListener);

        NastepnyPressedAction npa = new NastepnyPressedAction();
        PoprzedniPressedAction ppa = new PoprzedniPressedAction();
        PokazUkryjPressedAction pupa = new PokazUkryjPressedAction();
        PoprawnoscPressedAction poppa = new PoprawnoscPressedAction();
        KoniecPressedAction kpa = new KoniecPressedAction();

        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("D"), "nastepnyButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("A"), "poprzedniButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("Z"), "poprawnoscButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("X"), "pokazUkryjButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("C"), "koniecButtonKeyboardShortcut");

        mainPanel.getActionMap().put("nastepnyButtonKeyboardShortcut", npa);
        mainPanel.getActionMap().put("poprzedniButtonKeyboardShortcut", ppa);
        mainPanel.getActionMap().put("poprawnoscButtonKeyboardShortcut", pupa);
        mainPanel.getActionMap().put("pokazUkryjButtonKeyboardShortcut", poppa);
        mainPanel.getActionMap().put("koniecButtonKeyboardShortcut", kpa);

        setActualTest(actualTest);
        ImportLines();
        SetField();

        menuBar = new JMenuBar();

        JMenu tmp = new JMenu();
        JMenuItem tmpItem = new JMenuItem();
        tmpItem.setText("boop");
        tmp.add(tmpItem);
        menuBar.add(tmp);
        frame.setJMenuBar(menuBar);

    }

    private void PoprzedniButtonPressed() {
        ExportLines();
        PrevField();
        ImportLines();
        SetField();

    }

    private void NastepnyButtonPressed() {
        ExportLines();
        NextField();
        ImportLines();
        SetField();

    }

    private void PoprawnoscButtonPressed() {
        actualTest.chars[actualField].success = !actualTest.chars[actualField].success;
        ExportLines();
        SetField();
    }

    private void PokazUkryjRozwiazanieButton() {
        showAnswers = !showAnswers;
        SetField();
    }

    private void KoniecButtonPressed() {
        String zle = "";
        int poprawne = 0;
        for (int i = 0; i < actualTest.chars.length; i++) {
            if (!actualTest.chars[i].success) {
                zle += actualTest.chars[i].tested.engLook;
                zle += " ";
            } else {
                poprawne++;
            }
        }
        wrongOnesLabel.setText(zle);
        scoreLabel.setText(poprawne + "/" + actualTest.chars.length);
    }

    public void ImportLines() {
        if (actualTest.chars[actualField].lines != null)
            ((DrawingPanel) DrawPanel).lines = new ArrayList<Line>(Arrays.asList(actualTest.chars[actualField].lines));
        else ((DrawingPanel) DrawPanel).lines = new ArrayList<Line>();
    }

    public void ExportLines() {
        actualTest.chars[actualField].lines = new Line[((DrawingPanel) DrawPanel).lines.size()];
        actualTest.chars[actualField].lines = ((DrawingPanel) DrawPanel).lines.toArray(actualTest.chars[actualField].lines);

    }

    public void NextField() {
        if (actualField < actualTest.chars.length - 1) actualField++;
    }

    public void PrevField() {
        if (actualField > 0) actualField--;
    }

    public void SetField() {

        if (showAnswers == true) img.SetImage(actualTest.chars[actualField].tested.japLookPath);
        else img.SetImage("blank.png");
        nameLabel.setText(actualTest.chars[actualField].tested.engLook);
        if (actualTest.chars[actualField].success == true)
            correctLabel.setText("POPRAWNE");
        else correctLabel.setText("NIEPOPRAWNE");
        countLabel.setText(actualField + 1 + "/" + actualTest.chars.length);
        ((DrawingPanel) DrawPanel).WipeIt();
        ((DrawingPanel) DrawPanel).DrawIt();


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
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        poprzedniButton = new JButton();
        poprzedniButton.setText("Poprzedni");
        mainPanel.add(poprzedniButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nastepnyButton = new JButton();
        nastepnyButton.setText("Nastepny");
        mainPanel.add(nastepnyButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        poprawnoscButton = new JButton();
        poprawnoscButton.setText("Poprawnosc");
        mainPanel.add(poprawnoscButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        koniecButton = new JButton();
        koniecButton.setText("Koniec");
        mainPanel.add(koniecButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.add(ImgPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.add(DrawPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, 300), new Dimension(300, 300), new Dimension(300, 300), 0, false));
        pokazUkryjRozwiazanieButton = new JButton();
        pokazUkryjRozwiazanieButton.setText("Pokaz/Ukryj Rozwiazanie");
        panel1.add(pokazUkryjRozwiazanieButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreLabel = new JLabel();
        scoreLabel.setText("Label");
        panel1.add(scoreLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wrongOnesLabel = new JTextPane();
        panel1.add(wrongOnesLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        correctLabel = new JLabel();
        correctLabel.setText("Label");
        panel1.add(correctLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setEnabled(true);
        nameLabel.setText("Label");
        panel1.add(nameLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(-1, 20), 0, false));
        countLabel = new JLabel();
        countLabel.setText("Label");
        panel1.add(countLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(-1, 20), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    class NastepnyPressedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            NastepnyButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }

    class PoprzedniPressedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PoprzedniButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }

    class PoprawnoscPressedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PoprawnoscButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }

    class PokazUkryjPressedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PokazUkryjRozwiazanieButton();
            //System.out.println("przycisksiemaaa");
        }

    }

    class KoniecPressedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            KoniecButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }

    private void createUIComponents() {

        DrawPanel = new DrawingPanel();

        img = new ImagePanel();
        ImgPanel = img;
        //imgPanel.setBackground(new java.awt.Color(255, 255, 255));
        //imgPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));


    }

}
