package LangApp2.ExamplePack;

import LangApp2.Test.ExcerciseIf;
import LangApp2.Test.ExcerciseState;
import LangApp2.Test.TestIf;
import LangApp2.Test.TestState;

import java.util.ArrayList;

/**
 * Created by Lach on 2017-04-14.
 */
public class ExampleTest implements TestIf {

    public ExampleTest()
    {
        content = new ArrayList<>();
    }

    @Override
    public int GetExcerciseNum() {
        return content.size();
    }

    @Override
    public ExcerciseIf GetExcercise(int index) {
        return content.get(index);
    }

    @Override
    public boolean AddExcercise(ExcerciseIf exc) {
        return content.add(exc);
    }

    @Override
    public boolean RemoveExcercise(int index) {
        return content.remove(content.get(index));
    }

    @Override
    public ExcerciseIf[] GetAllExcercises() {
        ExcerciseIf[] ret = new ExcerciseIf[content.size()];
        return content.toArray(ret);
    }

    @Override
    public TestState GetTestState() {
        return state;
    }

    @Override
    public void SetTestState(TestState ts) {
        state = ts;
    }

    ArrayList<ExcerciseIf> content;
    private TestState state;

    public static ExampleTest GetTestExampleTest()
    {
        ExampleTest content;
        content = new ExampleTest();
        ExerciseExample first = new ExerciseExample();
        first.SetName("pierwszy");
        boolean[] arr = {true,true,false,true};
        first.SetProperAnswers(arr);
        StringExcerciseExample second = new StringExcerciseExample();
        second.SetName("nastepny");
        second.SetProperAnswer("piesek");
        StringExcerciseExample third = new StringExcerciseExample();
        third.SetName("ostatni");
        third.SetProperAnswer("duzy piesek");

        content.AddExcercise(second);
        content.AddExcercise(third);
        content.AddExcercise(first);
        first.SetExcerciseState(ExcerciseState.NotDone);
        second.SetExcerciseState(ExcerciseState.NotDone);
        third.SetExcerciseState(ExcerciseState.NotDone);

        content.SetTestState(TestState.Take);

        return content;
    }

}
