package LangApp2.GUI;

import LangApp2.Test.ExcerciseIf;

import javax.swing.*;

/**
 * Created by Lach on 2017-04-14.
 */
public interface ExcerciseGUIIf {

    public ExcerciseIf GetExcercise();
    public void SetExcercise(ExcerciseIf exc);
    public JPanel GetJPanel();

}
