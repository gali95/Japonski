package LangApp2.ExamplePack;

import LangApp2.GUI.ExcerciseGUIIf;
import LangApp2.Test.ExcerciseIf;

import javax.swing.*;

/**
 * Created by Lach on 2017-04-14.
 */
public class StringExcerciseExampleGUI implements ExcerciseGUIIf{
    private JTextField textField1;
    private JPanel mainGUIPanel;
    private JLabel label1;

    private ExcerciseIf content;

    public JPanel GetJPanel() {
        return mainGUIPanel;
    }

    @Override
    public ExcerciseIf GetExcercise() {
        return content;
    }

    @Override
    public void SetExcercise(ExcerciseIf exc) {
        content = exc;
        label1.setText(content.GetName());
    }

}
