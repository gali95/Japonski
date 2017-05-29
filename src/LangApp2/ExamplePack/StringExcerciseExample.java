package LangApp2.ExamplePack;

import LangApp2.GUI.ExcerciseGUIIf;
import LangApp2.Test.ExcerciseIf;
import LangApp2.Test.ExcerciseState;

import javax.swing.*;

/**
 * Created by Lach on 2017-04-14.
 */
public class StringExcerciseExample implements ExcerciseIf {
    @Override
    public ExcerciseState GetExcerciseState() {
        return state;
    }

    @Override
    public void SetExcerciseState(ExcerciseState es) {
        state = es;
    }

    @Override
    public boolean IsAutoCheckable() {
        return false;
    }

    @Override
    public void AutoCheck() {
        if(answer==null || properAnswer==null || state!=ExcerciseState.Unchecked) return;
        if(answer.equals(properAnswer))
        {
            state = ExcerciseState.Checked;
            result = true;
        }
        state = ExcerciseState.Checked;
        result = false;
    }

    @Override
    public boolean GetResult() {
        return result;
    }

    @Override
    public void SetResult(boolean result) {
        this.result = result;
    }

    @Override
    public ExcerciseGUIIf GetGUI() {
        StringExcerciseExampleGUI excTest = new StringExcerciseExampleGUI();
        excTest.SetExcercise(this);
        JPanel ret = excTest.GetJPanel();
        ret.setVisible(true);
        return excTest;
    }

    @Override
    public void SetName(String name) {
        this.name = name;
    }

    @Override
    public String GetName() {
        if(properAnswer==null)return name;
        else return name+"("+properAnswer+")";
    }

    @Override
    public int GetExcerciseNumber() {
        return index;
    }

    @Override
    public void SetExcerciseNumber(int ind) {
        index = ind;
    }

    public void SetAnswer(String ans)
    {
        this.answer = ans;
    }
    public void SetProperAnswer(String ans)
    {
        this.properAnswer = ans;
    }

    private ExcerciseState state;
    private boolean result;
    private String name;
    private String answer,properAnswer;
    private int index;
}
