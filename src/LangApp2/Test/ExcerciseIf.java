package LangApp2.Test;

import LangApp2.GUI.ExcerciseGUIIf;

import java.util.Date;
import java.util.Map;

import javax.swing.*;

/**
 * Created by Lach on 2017-04-08.
 */
public interface ExcerciseIf {

    public ExcerciseState GetExcerciseState();
    public void SetExcerciseState(ExcerciseState st);
    public boolean IsAutoCheckable();
    public void AutoCheck();
    public boolean GetResult();
    public void SetResult(boolean result);
    public ExcerciseGUIIf GetGUI();
    public void SetName(String name);
    public String GetName();
    public int GetExcerciseNumber();
    public void SetExcerciseNumber(int ind);
    
    // TODO implement funcs below
    public boolean isSourceEqual(ExcerciseIf other);
    public ExcerciseInput GetExcerciseInput();
}
