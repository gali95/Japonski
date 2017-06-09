package LangApp2.Vocab;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lach on 2017-06-03.
 */
public class DefaultTagsObj {

    ArrayList<String> content;

    public DefaultTagsObj()
    {
        content = new ArrayList<>();
    }

    public void Set(String[] cont)
    {
        content = new ArrayList<>(Arrays.asList(cont));
    }

    public void Set(String cont)
    {
        content = new ArrayList<>();
        content.add(cont);
    }

    public String[] Get()
    {
        String[] ret = new String[content.size()];
        return content.toArray(ret);
    }

    public void Add(String[] cont)
    {
        for (String part:cont
             ) {
            if(!content.contains(part))
            content.add(part);
        }
    }

    public void Add(String cont)
    {
        if(!content.contains(cont)) content.add(cont);
    }

    public void Remove(String[] cont)
    {
        for (String part:cont
             ) {
            content.remove(part);
        }
    }

    public void Remove(String cont)
    {
        content.remove(cont);
    }

}
