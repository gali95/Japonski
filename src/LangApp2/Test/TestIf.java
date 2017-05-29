package LangApp2.Test;

/**
 * Created by Lach on 2017-04-08.
 */
public interface TestIf {

    public int GetExcerciseNum();
    public ExcerciseIf GetExcercise(int index);
    public boolean AddExcercise(ExcerciseIf exc);
    public boolean RemoveExcercise(int index);
    public ExcerciseIf[] GetAllExcercises();

    public TestState GetTestState();
    public void SetTestState(TestState ts);
    public default void SetAllExcercisesState(ExcerciseState st)
    {
        for(ExcerciseIf exc:GetAllExcercises())
        {
            exc.SetExcerciseState(st);
        }
    }
    public default boolean ContainsExcerciseState(ExcerciseState st)
    {
        for(ExcerciseIf exc:GetAllExcercises())
        {
            if( exc.GetExcerciseState() == st)
            {
                return true;
            }
        }
        return false;
    }
    public default boolean ContainsExcerciseWithoutState(ExcerciseState st)
    {
        for(ExcerciseIf exc:GetAllExcercises())
        {
            if( exc.GetExcerciseState() != st)
            {
                return true;
            }
        }
        return false;
    }
    public default boolean CanEndCheckState()
    {
        if(GetTestState() != TestState.Check) return false;
        for(ExcerciseIf exc:GetAllExcercises())
        {
            if( exc.GetExcerciseState() == ExcerciseState.Unchecked && exc.IsAutoCheckable()==false)
            {
                return false;
            }
        }
        return true;
    }

}
