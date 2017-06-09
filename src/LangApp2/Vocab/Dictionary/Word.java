package LangApp2.Vocab.Dictionary;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Lach on 2017-06-02.
 */
public class Word{

    private String content,tags[],lang;
    private ArrayList<WordConnection> connectedWords;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(!(o instanceof Word)) return false;

        if(content.equals(((Word) o).content) && lang.equals(((Word) o).lang)) return true;
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(content,lang);
    }
}
