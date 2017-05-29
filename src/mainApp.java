import GUI.DrawingPanel;
import GUI.ImagePanel;
import alphabet_logic.CharacterRandomizer;
import alphabet_logic.Hiragana;
import alphabet_logic.Katakana;
import tests.ExampleKanjiTest;
import tests.Test;

import javax.swing.*;
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

    public void PrintAdno()
    {
        adnoLabel.setText(partialTest[0].adno);
    }

    public void AdnoButton()
    {
        partialTest[0].adno = textField1.getText();
        PrintAdno();
    }

    public void SavePartialButton()
    {
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

    public void OpenPartialButton()
    {
        ObjectInputStream in;
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("partial");
            in = new ObjectInputStream(fileIn);
            partialTest = (Test[]) in.readObject();

            PrintPartialLenght();
            PrintAdno();
        }catch(IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

    public void GeneratePartialButton()
    {
        partialTest = ExampleKanjiTest.GetPartedTest(Integer.valueOf(textField1.getText()));
        PrintPartialLenght();
    }

    public void PrintPartialLenght()
    {
        fragmLabel.setText(Integer.toString(partialTest.length));
    }

    public void VisualTestWithPartialButton()
    {
        JFrame frame = new JFrame("tescik");
        visualTest anotherTest = new visualTest(partialTest[Integer.valueOf(textField1.getText())],frame);
        frame.setContentPane(anotherTest.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //moj.custome = new GUI.CustomJPanel();
        frame.pack();

        frame.setVisible(true);
    }

    public mainApp()
    {
        fajny = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {



                JButton sourceBtn = (JButton) actionEvent.getSource();

                if (sourceBtn == hiraganaButton) {

                    textArea1.setText(CharacterRandomizer.GetExampleAll((new Hiragana()).content));
                    obrazek.SetImage("images.png");
                }
                else if (sourceBtn == katakanaButton) {

                    textArea1.setText(CharacterRandomizer.GetExampleAll((new Katakana()).content));
                    obrazek.SetImage("images2.png");

                }
                else if (sourceBtn == kaiji20Button) {

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


                }
                else if(sourceBtn == generujButton)
                {
                    GeneratePartialButton();
                }
                else if(sourceBtn == saveButton)
                {
                    SavePartialButton();
                }
                else if(sourceBtn == openButton)
                {
                    OpenPartialButton();
                }
                else if(sourceBtn == AdnoButton)
                {
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





}
