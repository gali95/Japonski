package LangApp2.GUI;

import LangApp2.ExamplePack.*;
import LangApp2.Test.ExcerciseIf;
import LangApp2.Test.ExcerciseState;
import LangApp2.Test.TestIf;
import LangApp2.Test.TestState;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

/**
 * Created by Lach on 2017-04-08.
 */
public class TestMenu {
    private JPanel TestMenuPanel;
    private JList<ExcerciseIf> contentList;
    private JList<ExcerciseState> filterList;
    private JPanel excercisePanel;
    private JButton excerciseChangeStateButton;
    private JButton excerciseSetResultButton;
    private JLabel testStateLabel;
    private JLabel excerciseNameLabel;
    private JLabel excerciseStateLabel;
    private JLabel excerciseResultLabel;
    private JButton nextTestStateButton;
    private JFrame frame;

    private TestIf content;
    private ExcerciseGUIIf selectedExcerciseGUI;
    private ActionListener actionListener;
    //private int selectedExcerciseIndex;

    public void SetupContentList()
    {
        contentList.setSelectionMode(SINGLE_SELECTION);
        contentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) return;
                UpdateExcercisePanel(contentList.getSelectedValue());
            }
        });
    }
    public void SetupFilterList()
    {
        filterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) return;
                UpdateContentList();
            }
        });
        filterList.setSelectionModel(new DefaultListSelectionModel()
        {
            @Override
            public void setSelectionInterval(int ind0,int ind1)
            {
                if(super.isSelectedIndex(ind0)) {
                    super.removeSelectionInterval(ind0, ind1);
                }
                else {
                    super.addSelectionInterval(ind0, ind1);
                }
            }
        });
    }
    public void SelectEveryFilterListPosition()
    {
        filterList.addSelectionInterval(0,filterList.getLastVisibleIndex());
    }

    public TestMenu()
    {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(e.getSource()==excerciseChangeStateButton)
                {
                    TestMenu.this.ChangeExcerciseStateButtonPressed();
                }
                else if(e.getSource()==excerciseSetResultButton)
                {
                    TestMenu.this.SetExcerciseResultButtonPresssed();
                }
                else if(e.getSource()==nextTestStateButton)
                {
                    TestMenu.this.NextTestState();
                }

            }
        };

        excerciseChangeStateButton.addActionListener(actionListener);
        excerciseSetResultButton.addActionListener(actionListener);
        nextTestStateButton.addActionListener(actionListener);

        SetupContentList();
        SetupFilterList();
    }

    public static void main(String[] args) {
        TestMenu testo = new TestMenu();
        JFrame frame = new JFrame("TestMenu");
        frame.setContentPane(testo.TestMenuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        testo.frame = frame;
        testo.Test();

        //TestState k = TestState.NotCreated;
        //System.out.println(k);
    }

    public void Test()
    {
        ExampleTest content = ExampleTest.GetTestExampleTest();
        SetContent(content);
    }

    public void SetContent(TestIf content)
    {
        if(content==null || content.GetExcerciseNum()<1) return;
        this.content = content;
        UpdateFilterList();
        UpdateInfoLabels();
        SetTestOnStart();
        UpdateContentList();
        //selectedExcerciseGUI = content.GetExcercise(0).GetGUI();
    }

    public void UpdateFilterList()
    {
        //System.out.println(content.GetTestState());
        filterList.setListData(content.GetTestState().GetLinkedTestStates());
        SelectEveryFilterListPosition();
    }

    public void UpdateContentList()
    {
        ExcerciseIf[] contArray = content.GetAllExcercises();
        contentList.setListData(FilterExcercises(contArray));

    }
    public ExcerciseIf[] FilterExcercises(ExcerciseIf[] entry)
    {
        java.util.List<ExcerciseState> visible = filterList.getSelectedValuesList();
        ArrayList<ExcerciseIf> chosen = new ArrayList<>();
        int count;
        for(ExcerciseIf exc:entry)
        {
            if(visible.contains(exc.GetExcerciseState()))
            {
                chosen.add(exc);
            }
        }
        ExcerciseIf[] ret = new ExcerciseIf[chosen.size()];
        return chosen.toArray(ret);
    }
    public void UpdateExcercisePanel(ExcerciseIf newOne)
    {

        if(selectedExcerciseGUI!=null)
        {
            excercisePanel.removeAll();
            if(newOne == selectedExcerciseGUI.GetExcercise())
            {
                return;
            }
            // TODO if else, new GUI should not be generated
        }
        selectedExcerciseGUI = newOne.GetGUI();
        excercisePanel.add(selectedExcerciseGUI.GetJPanel());
        UpdateInfoLabels();
        excercisePanel.updateUI();

    }
    public void UpdateInfoLabels()
    {
        if(content!=null)
        {
            testStateLabel.setText(content.GetTestState().toString());
        }
        else
        {
            testStateLabel .setText("");
        }
        if(selectedExcerciseGUI!=null)
        {
            excerciseNameLabel.setText(selectedExcerciseGUI.GetExcercise().GetName());
            String excResultText;
            if(selectedExcerciseGUI.GetExcercise().GetResult())
            {
                excResultText = "DOBRZE";
            }
            else
            {
                excResultText = "ŹLE";
            }
            excerciseResultLabel.setText(excResultText);
            excerciseStateLabel.setText(selectedExcerciseGUI.GetExcercise().GetExcerciseState().toString());

        }
        else
        {
            excerciseNameLabel.setText("");
            excerciseResultLabel.setText("");
            excerciseStateLabel.setText("");
        }
    }

    public void SetTestOnStart()
    {
        content.SetTestState(TestState.Take);
        content.SetAllExcercisesState(content.GetTestState().GetStartExcerciseState());
        UpdateInfoLabels();
    }

    public void NextTestState()
    {
        switch (content.GetTestState())
        {
            case NotCreated:
            case Repeat:
            case Undefined:
                return;
            case Take:
                if(content.ContainsExcerciseWithoutState(ExcerciseState.Done))
                    return;
                content.SetTestState(TestState.Check);
                break;
            case Check:
                if(!content.CanEndCheckState())
                    return;
                content.SetTestState(TestState.Repeat);
                break;
        }
        content.SetAllExcercisesState(content.GetTestState().GetStartExcerciseState());
        UpdateInfoLabels();
        UpdateFilterList();
        UpdateContentList();
    }
    public void ChangeExcerciseStateButtonPressed()
    {
        switch (selectedExcerciseGUI.GetExcercise().GetExcerciseState())
        {
            case Checked:
                selectedExcerciseGUI.GetExcercise().SetExcerciseState(ExcerciseState.Unchecked);
                break;
            case Done:
                selectedExcerciseGUI.GetExcercise().SetExcerciseState(ExcerciseState.NotDone);
                break;
            case NotDone:
                selectedExcerciseGUI.GetExcercise().SetExcerciseState(ExcerciseState.Done);
                break;
            case Repeated:
                selectedExcerciseGUI.GetExcercise().SetExcerciseState(ExcerciseState.Unrepeated);
                break;
            case Unchecked:
                selectedExcerciseGUI.GetExcercise().SetExcerciseState(ExcerciseState.Checked);
                break;
            case Unrepeated:
                selectedExcerciseGUI.GetExcercise().SetExcerciseState(ExcerciseState.Repeated);
                break;
        }
        UpdateInfoLabels();
        UpdateContentList();
    }
    public void SetExcerciseResultButtonPresssed()
    {
        if(content.GetTestState() != TestState.Check) return;

        selectedExcerciseGUI.GetExcercise().SetResult(!selectedExcerciseGUI.GetExcercise().GetResult());
        UpdateInfoLabels();
    }

    private void createUIComponents() {
        excercisePanel = new JPanel();

    }
}
