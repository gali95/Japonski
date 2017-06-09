package LangApp2.Vocab.Dictionary;

import java.util.ArrayList;

/**
 * Created by Lach on 2017-06-02.
 */
public class Dictionary {

    private ArrayList<Word> words;
    private ArrayList<WordConnection> connections;

    public void InitEmpty()
    {
        words = new ArrayList<>();
        connections = new ArrayList<>();
    }
    public Word getWord(String name,String lang)  // if doesn't exist, returns null
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
    public void AddConnection(Word first, Word second, WordConnection conn, String[] firstTags, String[] secondTags, String[] connTags)  // firstTags and secondTags will be added as tags without removing previous ones
    {

        Word selected1 = getWord(first.getContent(),first.getLang());
        if(selected1 == null)
        {
            AddWord(first);
            selected1 = first;
            selected1.setTags(firstTags);
        }

        Word selected2 = getWord(second.getContent(),second.getLang());
        if(selected2 == null)
        {
            AddWord(second);
            selected2 = second;
            selected2.setTags(secondTags);
        }
        conn.setTags(connTags);
        conn.SetWords(selected1,selected2);

        if(connections.contains(conn)) return;

        connections.add(conn);

    }
    public void AddWord(Word added)
    {
        if(words.contains(added)) return;

        words.add(added);
    }




}
