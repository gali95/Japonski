package LangApp2.Vocab.Dictionary;

import LangApp2.Vocab.DefaultTagsObj;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

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
    private String[] connectionTags;

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
    }

    private class groupsSettings
    {
        public DefaultTagsObj defTags;
        public String defLang;

        public groupsSettings()
        {
            defTags = new DefaultTagsObj();
        }
    }

    public void AddToDictionary(String[] entry)
    {
        if(entry.length<3) return;
        boolean stop = true;
        int equalSignPosition = 0;
        for(int i=1;i<entry.length;i++) {
            if (entry[i].equals("=")) {
                equalSignPosition = i;
                stop = false;
                break;
            }
        }
        if(stop) return;
        Word leftWord,rightWord;
        for(int i=0;i<equalSignPosition;i++)
        {
            for(int j=equalSignPosition+1;j<entry.length;j++)
            {
                leftWord = new Word();
                leftWord.setContent(entry[i]);
                leftWord.setLang(left.defLang);

                rightWord = new Word();
                rightWord.setContent(entry[j]);
                rightWord.setLang(right.defLang);

                outputDictionary.AddConnection(leftWord,rightWord,new WordConnection(),left.defTags.Get(),right.defTags.Get(),connectionTags);

                if(aboveSource.length>0)
                {
                    Word aboveWord = new Word();
                    aboveWord.setLang(above.defLang);
                    // TODO continue dis szit
                }
            }
        }
    }

    @Override
    public Dictionary Parse(String entry) {

        Reader in = new StringReader(entry);
        try {
            for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
                for (String field : record) {
                    System.out.print("\"" + field + "\", ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
