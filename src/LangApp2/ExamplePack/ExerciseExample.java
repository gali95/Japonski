package LangApp2.ExamplePack;

import LangApp2.GUI.ExcerciseGUIIf;
import LangApp2.Test.ExcerciseIf;
import LangApp2.Test.ExcerciseState;

import javax.swing.*;

/**
 * Created by Lach on 2017-04-09.
 */
public class ExerciseExample implements ExcerciseIf {
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
        return true;
    }

    @Override
    public void AutoCheck() {
        if(odp==null || selectedOdp==null || state!=ExcerciseState.Unchecked) return;
        for(int i=0;i<4;i++)
        {
            if(odp[i] != selectedOdp[i])
            {
                state = ExcerciseState.Checked;
                result = false;
            }
        }
        state = ExcerciseState.Checked;
        result = true;
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
        ExcerciseTest excTest = new ExcerciseTest();
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
        if(odp==null)
            return name;
        else
        {
            String ret = name+"(";
            for(int i=0;i<4;i++) ret+= odp[i];
            ret += ")";
            return ret;
        }

    }

    public void SetProperAnswers(boolean[] ans)
    {
        if(ans.length<4) return;
        odp = ans;
    }
    public void SetAnswers(boolean[] ans)
    {
        if(ans.length<4) return;
        selectedOdp = ans;
    }

    @Override
    public int GetExcerciseNumber() {
        return index;
    }

    @Override
    public void SetExcerciseNumber(int ind) {
        index = ind;
    }

    private ExcerciseState state;
    private boolean result;
    private String name;
    private boolean[] odp;
    private boolean[] selectedOdp;
    private int index;
}
