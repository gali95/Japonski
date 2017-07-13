package LangApp2.Vocab.Dictionary;

import java.util.ArrayList;

import LangApp2.Vocab.Dictionary.Filters.WordConnectionsFilterIf;
import LangApp2.Vocab.Dictionary.Filters.WordsFilterIf;

/**
 * Created by Lach on 2017-06-02.
 */
public class Dictionary {

    private ArrayList<Word> words;
    private ArrayList<WordConnection> connections;

    private String name;
    
    public Dictionary()
    {
    	InitEmpty();
    }
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void InitEmpty()
    {
        words = new ArrayList<>();
        connections = new ArrayList<>();
    }
    private Word getWord(String name,String lang)  // if doesn't exist, returns null
    {
        for(Word selected: words)
        {
            if(selected.getContent().equals(name) && selected.getLang().equals(lang))
            {
                return selected;
            }
        }
        return null;
    }
    public void AddConnection(Word first, Word second, WordConnection conn) // function doesn't check content of arguments, currently to ensure proper behaviour, words should have tags , content and lang fields set. Function can change connection class (conn1, conn2) even if it won't be added to dictionary connections
    {

        Word selected1 = getWord(first.getContent(),first.getLang());
        Word selected2 = getWord(second.getContent(),second.getLang());
        boolean wordWasThere1 = false;
        boolean wordWasThere2 = false;
        
        if(selected1 != null) wordWasThere1 = true;
        if(selected2 != null) wordWasThere2 = true;
        
        if(selected1 == null)
        {
            AddWord(first);
            selected1 = first;
        }

        if(selected2 == null)
        {
            AddWord(second);
            selected2 = second;
        }
        conn.SetWords(selected1,selected2);

        if((wordWasThere1 && wordWasThere2) && connections.contains(conn)) return;

        connections.add(conn);
        

    }
    public boolean AddWord(Word added)
    {
        if(words.contains(added)) return false;

        words.add(added);
        return true;
    }
    public boolean RemoveConnection(WordConnection conn)
    {
    	if(connections.contains(conn))
    	{
    		connections.remove(conn);
    		return true;
    	}
    	else return false;
    }
    public boolean RemoveWord(Word toRemove)
    {
    	if(words.contains(toRemove))
    	{
    		words.remove(toRemove);
    		return true;
    	}
    	else return false;
    }
    
     
    
    
    public Word[] GetFilteredWords(WordsFilterIf filters)
    {
    	Word[] entryArr = new Word[words.size()];
    	return filters.Filter(words.toArray(entryArr));
    }
    
    public WordConnection[] GetFilteredWordConnections(WordConnectionsFilterIf filters)
    {
    	WordConnection[] entryArr = new WordConnection[connections.size()];
    	return filters.Filter(connections.toArray(entryArr));
    }
    
    // TODO modify content functions
    
    // TODO functions which will let to create class containing collections of excercises.
    
    @Override
    public String toString()
    {
    	String output = "[ words: " + System.lineSeparator();
    	for(int i=0;i<words.size();i++)
    	{
    		output += "    " + words.get(i).toString();
    	}
    	output += System.lineSeparator() + "  connections: " + System.lineSeparator();
    	for(int i=0;i<connections.size();i++)
    	{
    		output += "    " + connections.get(i).toString();
    	}
    	output += " ] " + System.lineSeparator();
    	
    	return output;
    }


}
