package LangApp2.Vocab.Dictionary;

import java.util.ArrayList;

/**
 * Created by Lach on 2017-06-02.
 */
public class Dictionary {

    private ArrayList<Word> words;
    private ArrayList<WordConnection> connections;

    public Dictionary()
    {
    	InitEmpty();
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
    public void AddConnection(Word first, Word second, WordConnection conn)
    {

    	// TODO ensure it works "correctly"
    	
        Word selected1 = getWord(first.getContent(),first.getLang());
        if(selected1 == null)
        {
            AddWord(first);
            selected1 = first;
            //addWordAfterwards1 = true;
            //selected1.setTags(first.getTags());
        }

        Word selected2 = getWord(second.getContent(),second.getLang());
        if(selected2 == null)
        {
            AddWord(second);
            selected2 = second;
            //addWordAfterwards2 = true;
            //selected2.setTags(second.getTags());
        }
        //conn.setTags(conn.getTags());
        conn.SetWords(selected1,selected2);

        if(connections.contains(conn)) return;

        connections.add(conn);
        

    }
    private void AddWord(Word added)
    {
        if(words.contains(added)) return;

        words.add(added);
    }

    
    
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
