package LangApp2.Test;

/**
 * Created by Lach on 2017-04-08.
 */
public enum TestState {

    NotCreated,
    Take,
    Check,
    Repeat,
    Undefined;

    public ExcerciseState[] GetLinkedTestStates()
    {
        ExcerciseState[] ret;
        switch (this){
            case NotCreated:
                ret = new ExcerciseState[]{ExcerciseState.NotCreated};
                break;
            case Take:
                ret = new ExcerciseState[]{ExcerciseState.NotDone,ExcerciseState.Done,ExcerciseState.NotCreated};
                break;
            case Check:
                ret = new ExcerciseState[]{ExcerciseState.Unchecked,ExcerciseState.Checked};
                break;
            case Repeat:
                ret = new ExcerciseState[]{ExcerciseState.Unrepeated,ExcerciseState.Repeated};
                break;
            case Undefined:
                ret = new ExcerciseState[]{ExcerciseState.Undefined};
                break;
            default:
                ret = null;
        }
        return ret;

    }
    public ExcerciseState GetStartExcerciseState()
    {
        switch(this)
        {
            case Take:
                return ExcerciseState.NotDone;
            case Check:
                return ExcerciseState.Unchecked;
            case Repeat:
                return ExcerciseState.Unrepeated;
            default:
                return ExcerciseState.Undefined;
        }
    }

}
