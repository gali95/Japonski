package LangApp2.Vocab.Dictionary;

import java.util.Objects;

/**
 * Created by Lach on 2017-06-02.
 */
public class WordConnection {

    private String tags[];
    private Word conn1,conn2;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o) return true;
        if(!(o instanceof WordConnection)) return false;

        if(conn1.equals(((WordConnection) o).conn1)&&conn2.equals(((WordConnection) o).conn2)) return true;
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(conn1,conn2);
    }

    public void SetWords(Word first,Word second)
    {
        if(first==second) {
            return;
        }
        if(first.hashCode()<second.hashCode())
        {
            conn1 = first;
            conn2 = second;
        }
        else
        {
            conn1 = second;
            conn2 = first;
        }
    }

    @Override
    public String toString()
    {
    	String tags="";
    	for(int i=0;i<this.tags.length;i++)
    	{
    		tags += this.tags[i] + ", ";
    	}
    	return "[ " + conn1.getContent() + "(" +conn1.getLang() + ") - " + conn2.getContent() + "(" +conn2.getLang() + ") tags: " + tags + " ]";
    }
    
}
