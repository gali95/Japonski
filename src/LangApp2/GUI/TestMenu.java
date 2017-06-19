package LangApp2.GUI;

import LangApp2.ExamplePack.*;
import LangApp2.Test.ExcerciseIf;
import LangApp2.Test.ExcerciseState;
import LangApp2.Test.TestIf;
import LangApp2.Test.TestState;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

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

    public void SetupContentList() {
        contentList.setSelectionMode(SINGLE_SELECTION);
        contentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) return;
                UpdateExcercisePanel(contentList.getSelectedValue());
            }
        });
    }

    public void SetupFilterList() {
        filterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) return;
                UpdateContentList();
            }
        });
        filterList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int ind0, int ind1) {
                if (super.isSelectedIndex(ind0)) {
                    super.removeSelectionInterval(ind0, ind1);
                } else {
                    super.addSelectionInterval(ind0, ind1);
                }
            }
        });
    }

    public void SelectEveryFilterListPosition() {
        filterList.addSelectionInterval(0, filterList.getLastVisibleIndex());
    }

    public TestMenu() {
        $$$setupUI$$$();
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == excerciseChangeStateButton) {
                    TestMenu.this.ChangeExcerciseStateButtonPressed();
                } else if (e.getSource() == excerciseSetResultButton) {
                    TestMenu.this.SetExcerciseResultButtonPresssed();
                } else if (e.getSource() == nextTestStateButton) {
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

    public void Test() {
        ExampleTest content = ExampleTest.GetTestExampleTest();
        SetContent(content);
    }

    public void SetContent(TestIf content) {
        if (content == null || content.GetExcerciseNum() < 1) return;
        this.content = content;
        UpdateFilterList();
        UpdateInfoLabels();
        SetTestOnStart();
        UpdateContentList();
        //selectedExcerciseGUI = content.GetExcercise(0).GetGUI();
    }

    public void UpdateFilterList() {
        //System.out.println(content.GetTestState());
        filterList.setListData(content.GetTestState().GetLinkedTestStates());
        SelectEveryFilterListPosition();
    }

    public void UpdateContentList() {
        ExcerciseIf[] contArray = content.GetAllExcercises();
        contentList.setListData(FilterExcercises(contArray));

    }

    public ExcerciseIf[] FilterExcercises(ExcerciseIf[] entry) {
        List<ExcerciseState> visible = filterList.getSelectedValuesList();
        ArrayList<ExcerciseIf> chosen = new ArrayList<>();
        int count;
        for (ExcerciseIf exc : entry) {
            if (visible.contains(exc.GetExcerciseState())) {
                chosen.add(exc);
            }
        }
        ExcerciseIf[] ret = new ExcerciseIf[chosen.size()];
        return chosen.toArray(ret);
    }

    public void UpdateExcercisePanel(ExcerciseIf newOne) {

        if (selectedExcerciseGUI != null) {
            excercisePanel.removeAll();
            if (newOne == selectedExcerciseGUI.GetExcercise()) {
                return;
            }
            // TODO if else, new GUI should not be generated
        }
        selectedExcerciseGUI = newOne.GetGUI();
        excercisePanel.add(selectedExcerciseGUI.GetJPanel());
        UpdateInfoLabels();
        excercisePanel.updateUI();

    }

    public void UpdateInfoLabels() {
        if (content != null) {
            testStateLabel.setText(content.GetTestState().toString());
        } else {
            testStateLabel.setText("");
        }
        if (selectedExcerciseGUI != null) {
            excerciseNameLabel.setText(selectedExcerciseGUI.GetExcercise().GetName());
            String excResultText;
            if (selectedExcerciseGUI.GetExcercise().GetResult()) {
                excResultText = "DOBRZE";
            } else {
                excResultText = "ŹLE";
            }
            excerciseResultLabel.setText(excResultText);
            excerciseStateLabel.setText(selectedExcerciseGUI.GetExcercise().GetExcerciseState().toString());

        } else {
            excerciseNameLabel.setText("");
            excerciseResultLabel.setText("");
            excerciseStateLabel.setText("");
        }
    }

    public void SetTestOnStart() {
        content.SetTestState(TestState.Take);
        content.SetAllExcercisesState(content.GetTestState().GetStartExcerciseState());
        UpdateInfoLabels();
    }

    public void NextTestState() {
        switch (content.GetTestState()) {
            case NotCreated:
            case Repeat:
            case Undefined:
                return;
            case Take:
                if (content.ContainsExcerciseWithoutState(ExcerciseState.Done))
                    return;
                content.SetTestState(TestState.Check);
                break;
            case Check:
                if (!content.CanEndCheckState())
                    return;
                content.SetTestState(TestState.Repeat);
                break;
        }
        content.SetAllExcercisesState(content.GetTestState().GetStartExcerciseState());
        UpdateInfoLabels();
        UpdateFilterList();
        UpdateContentList();
    }

    public void ChangeExcerciseStateButtonPressed() {
        switch (selectedExcerciseGUI.GetExcercise().GetExcerciseState()) {
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

    public void SetExcerciseResultButtonPresssed() {
        if (content.GetTestState() != TestState.Check) return;

        selectedExcerciseGUI.GetExcercise().SetResult(!selectedExcerciseGUI.GetExcercise().GetResult());
        UpdateInfoLabels();
    }

    private void createUIComponents() {
        excercisePanel = new JPanel();

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
        TestMenuPanel = new JPanel();
        TestMenuPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentList = new JList();
        TestMenuPanel.add(contentList, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        TestMenuPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        filterList = new JList();
        TestMenuPanel.add(filterList, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        testStateLabel = new JLabel();
        testStateLabel.setText("Label");
        TestMenuPanel.add(testStateLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TestMenuPanel.add(excercisePanel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, 300), new Dimension(300, 300), new Dimension(300, 300), 0, false));
        excerciseChangeStateButton = new JButton();
        excerciseChangeStateButton.setText("Status");
        TestMenuPanel.add(excerciseChangeStateButton, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        excerciseSetResultButton = new JButton();
        excerciseSetResultButton.setText("Wynik");
        TestMenuPanel.add(excerciseSetResultButton, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        excerciseNameLabel = new JLabel();
        excerciseNameLabel.setText("Label");
        TestMenuPanel.add(excerciseNameLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        excerciseStateLabel = new JLabel();
        excerciseStateLabel.setText("Label");
        TestMenuPanel.add(excerciseStateLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        excerciseResultLabel = new JLabel();
        excerciseResultLabel.setText("Label");
        TestMenuPanel.add(excerciseResultLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nextTestStateButton = new JButton();
        nextTestStateButton.setText("Nastepna faza testu");
        TestMenuPanel.add(nextTestStateButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return TestMenuPanel;
    }
}
