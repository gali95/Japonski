package LangApp2.Vocab.Dictionary;

import LangApp2.Vocab.DefaultTagsObj;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by Lach on 2017-06-03.
 */
public class SimpleTextParser implements TextToDictionaryParser{

    private Dictionary outputDictionary;
    private groupsSettings right,left,above,selected;
    private String[] aboveSource;
    private DefaultTagsObj connectionTags;
    
    private class groupsSettings
    {
        public DefaultTagsObj defTags;
        public String defLang;

        public groupsSettings()
        {
            defTags = new DefaultTagsObj();
        }
    }

    public SimpleTextParser()
    {
        Init();
    }

    public void Init()
    {
        outputDictionary = new Dictionary();
        right=new groupsSettings();
        left=new groupsSettings();
        above=new groupsSettings();
        aboveSource = null;
        connectionTags = new DefaultTagsObj();
    }

   
    public void AddToDictionary(String[] entry)
    {
        if(entry.length<3) return;
        boolean stop = true;
        int equalSignPosition = 0;
        for(int i=1;i<entry.length;i++) {
            if (entry[i].equals("!connect")) {
                equalSignPosition = i;
                stop = false;
                break;
            }
        }
        if(stop) return;
        Word leftWord,rightWord;
        WordConnection conn;
        for(int i=0;i<equalSignPosition;i++)
        {
            for(int j=equalSignPosition+1;j<entry.length;j++)
            {
                leftWord = new Word();
                leftWord.setContent(entry[i]);
                leftWord.setLang(left.defLang);
                leftWord.setTags(left.defTags.Get());

                rightWord = new Word();
                rightWord.setContent(entry[j]);
                rightWord.setLang(right.defLang);
                rightWord.setTags(right.defTags.Get());
                
                conn = new WordConnection();
                conn.setTags(connectionTags.Get());

                outputDictionary.AddConnection(leftWord,rightWord,conn);

                for(int k=0;k<aboveSource.length;k++)
                {
                    Word aboveWord = new Word();
                    aboveWord.setLang(above.defLang);
                    aboveWord.setTags(above.defTags.Get());
                    aboveWord.setContent(aboveSource[k]);
                    
                    conn = new WordConnection();
                    conn.setTags(connectionTags.Get());
                    outputDictionary.AddConnection(aboveWord,rightWord,conn);
                    
                    conn = new WordConnection();
                    conn.setTags(connectionTags.Get());
                    outputDictionary.AddConnection(aboveWord,leftWord,conn);
                    
                }
            }
        }
    }

    public boolean SetCommandTarget(String[] entry)
    {
    	return false;
    }
    /*
    public void CommandAddTags(String[] entry);
    
    public void CommandSetTags(String[] entry);
    
    public void CommandRemoveTags(String[] entry);
    
    public void CommandSetAboveSources(String[] entry);
    
    public void CommandSetLang(String[] entry);
    */

    @Override
    public Dictionary Parse(String entry) {

        Reader in = new StringReader(entry);
        //try {
            //for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
            //    for (String field : record) {
            //        System.out.print("\"" + field + "\", ");
            //    }
            //    System.out.println();
            //}
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        return null;
    }
}
