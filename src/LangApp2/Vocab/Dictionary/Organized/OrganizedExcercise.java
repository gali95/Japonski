package LangApp2.Vocab.Dictionary.Organized;

import java.util.ArrayList;

import LangApp2.Test.ExcerciseIf;
import LangApp2.Test.ExcerciseInput;

public class OrganizedExcercise {

	private ArrayList<ExcerciseInput> excerciseLog;
	private OrganizedExcerciseSource source;
	
    public OrganizedExcercise(OrganizedExcerciseSource initializer)
    {
    	source = initializer;
    }
	public boolean ArchiveExcercise(ExcerciseIf entry)
	{
		if(source.CreateExcerise().isSourceEqual(entry))
		{
			excerciseLog.add(entry.GetExcerciseInput());
			return true;
		}
		return false;
	}
	public ExcerciseIf CreateExcercise()
	{
		return source.CreateExcerise();
	}
	
}
