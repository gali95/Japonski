package LangApp2.ExamplePack;

import LangApp2.GUI.ExcerciseGUIIf;
import LangApp2.Test.ExcerciseIf;

import javax.swing.*;

/**
 * Created by Lach on 2017-04-09.
 */
public class ExcerciseTest implements ExcerciseGUIIf {
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JPanel excExamplePanel;

    private ExcerciseIf content;

    public JPanel GetJPanel() {
        return excExamplePanel;
    }

    @Override
    public ExcerciseIf GetExcercise() {
        return content;
    }

    @Override
    public void SetExcercise(ExcerciseIf exc) {
        content = exc;
        checkBox1.setText(content.GetName());
    }
}
