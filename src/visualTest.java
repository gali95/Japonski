import GUI.DrawingPanel;
import GUI.ImagePanel;
import main.Line;
import tests.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gali95 on 18.09.16.
 */
public class visualTest {
    private JButton poprzedniButton;
    private JButton następnyButton;
    private JButton poprawnośćButton;
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
        if(actualTest.chars.length < 1) return;
        this.actualTest = actualTest;
        actualField = 0;
    }

    private int actualField;
    private Boolean showAnswers;

    public visualTest(Test actualTest, JFrame frame)
    {

        showAnswers = false;

        defaultListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JButton source = (JButton)actionEvent.getSource();

                if(source == poprzedniButton)
                {
                    PoprzedniButtonPressed();
                }
                else if(source == następnyButton)
                {
                    NastepnyButtonPressed();
                }
                else if(source == pokazUkryjRozwiazanieButton)
                {
                    PokazUkryjRozwiazanieButton();
                }
                else if(source == poprawnośćButton)
                {
                    PoprawnośćButtonPressed();
                }
                else if(source == koniecButton)
                {
                    KoniecButtonPressed();
                }

            }
        };

        poprzedniButton.addActionListener(defaultListener);
        następnyButton.addActionListener(defaultListener);
        pokazUkryjRozwiazanieButton.addActionListener(defaultListener);
        poprawnośćButton.addActionListener(defaultListener);
        koniecButton.addActionListener(defaultListener);

        NastepnyPressedAction npa = new NastepnyPressedAction();
        PoprzedniPressedAction ppa = new PoprzedniPressedAction();
        PokazUkryjPressedAction pupa = new PokazUkryjPressedAction();
        PoprawnośćPressedAction poppa = new PoprawnośćPressedAction();
        KoniecPressedAction kpa = new KoniecPressedAction();

        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("D"), "nastepnyButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("A"), "poprzedniButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("Z"), "poprawnośćButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("X"), "pokazUkryjButtonKeyboardShortcut");
        mainPanel.getInputMap(2).put(KeyStroke.getKeyStroke("C"), "koniecButtonKeyboardShortcut");

        mainPanel.getActionMap().put("nastepnyButtonKeyboardShortcut", npa);
        mainPanel.getActionMap().put("poprzedniButtonKeyboardShortcut", ppa);
        mainPanel.getActionMap().put("poprawnośćButtonKeyboardShortcut", pupa);
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

    private void PoprzedniButtonPressed()
    {
        ExportLines();
        PrevField();
        ImportLines();
        SetField();

    }
    private void NastepnyButtonPressed()
    {
        ExportLines();
        NextField();
        ImportLines();
        SetField();

    }
    private void PoprawnośćButtonPressed()
    {
        actualTest.chars[actualField].success = !actualTest.chars[actualField].success;
        ExportLines();
        SetField();
    }
    private void PokazUkryjRozwiazanieButton()
    {
        showAnswers = !showAnswers;
        SetField();
    }
    private void KoniecButtonPressed()
    {
        String zle = "";
        int poprawne = 0;
        for(int i=0;i<actualTest.chars.length;i++)
        {
            if(!actualTest.chars[i].success)
            {
                zle += actualTest.chars[i].tested.engLook;
                zle += " ";
            }
            else
            {
                poprawne++;
            }
        }
        wrongOnesLabel.setText(zle);
        scoreLabel.setText(poprawne+"/"+actualTest.chars.length);
    }

    public void ImportLines()
    {
        if(actualTest.chars[actualField].lines != null)
        ((DrawingPanel) DrawPanel).lines = new ArrayList<Line>( Arrays.asList(actualTest.chars[actualField].lines) );
        else  ((DrawingPanel) DrawPanel).lines = new ArrayList<Line>();
    }
    public void ExportLines()
    {
        actualTest.chars[actualField].lines = new Line[((DrawingPanel) DrawPanel).lines.size()];
        actualTest.chars[actualField].lines = ((DrawingPanel) DrawPanel).lines.toArray(actualTest.chars[actualField].lines);

    }
    public void NextField()
    {
        if(actualField<actualTest.chars.length-1) actualField++;
    }
    public void PrevField()
    {
        if(actualField>0) actualField--;
    }
    public void SetField()
    {

        if(showAnswers==true)img.SetImage(actualTest.chars[actualField].tested.japLookPath);
        else img.SetImage("blank.png");
        nameLabel.setText(actualTest.chars[actualField].tested.engLook);
        if(actualTest.chars[actualField].success == true)
        correctLabel.setText("POPRAWNE");
        else correctLabel.setText("NIEPOPRAWNE");
        countLabel.setText(actualField+1 + "/" + actualTest.chars.length);
        ((DrawingPanel) DrawPanel).WipeIt();
        ((DrawingPanel) DrawPanel).DrawIt();


    }

    class NastepnyPressedAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            NastepnyButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }
    class PoprzedniPressedAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PoprzedniButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }

    class PoprawnośćPressedAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PoprawnośćButtonPressed();
            //System.out.println("przycisksiemaaa");
        }

    }

    class PokazUkryjPressedAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PokazUkryjRozwiazanieButton();
            //System.out.println("przycisksiemaaa");
        }

    }

    class KoniecPressedAction extends AbstractAction
    {
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
