package LangApp2.Vocab.Dictionary;

import LangApp2.Vocab.DefaultTagsObj;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Created by Lach on 2017-06-03.
 */
public class SimpleTextParser implements TextToDictionaryParser{

    private Dictionary outputDictionary;
    private groupsSettings right,left,above,selected,connection;
    private String[] aboveSource;
    
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
        connection = new groupsSettings();
        aboveSource = null;
        
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
                conn.setTags(connection.defTags.Get());

                outputDictionary.AddConnection(leftWord,rightWord,conn);

                for(int k=0;k<aboveSource.length;k++)
                {
                    Word aboveWord = new Word();
                    aboveWord.setLang(above.defLang);
                    aboveWord.setTags(above.defTags.Get());
                    aboveWord.setContent(aboveSource[k]);
                    
                    conn = new WordConnection();
                    conn.setTags(connection.defTags.Get());
                    outputDictionary.AddConnection(aboveWord,rightWord,conn);
                    
                    conn = new WordConnection();
                    conn.setTags(connection.defTags.Get());
                    outputDictionary.AddConnection(aboveWord,leftWord,conn);
                    
                }
            }
        }
    }

    public boolean SetCommandTarget(String[] entry)  // returns true if target was successfully set
    {
    	switch(entry[0])
    	{
    	case "!right" :
    		selected = right;
    		break;
    	case "!left" :
    		selected = left;
    		break;
    	case "!above" :
    		selected = above;
    		break;
    	case "!connection" :
    		selected = connection;
    		break;
    	default:
    		return false;
    	
    	}
    	return true;
    }
    
    public void CommandAddTags(String[] entry)
    {
    	selected.defTags.Add(Arrays.copyOfRange(entry, 2, entry.length));
    }
    
    public void CommandSetTags(String[] entry)
    {
    	selected.defTags.Set(Arrays.copyOfRange(entry, 2, entry.length));
    }
    
    public void CommandRemoveTags(String[] entry)
    {
    	selected.defTags.Remove(Arrays.copyOfRange(entry, 2, entry.length));
    }
    
    public void CommandSetAboveSources(String[] entry)
    {
    	aboveSource = Arrays.copyOfRange(entry, 2, entry.length);
    }
    
    public void CommandSetLang(String[] entry)
    {
    	selected.defLang = entry[2];
    }
    
    public void ExecuteCSVLine(String[] entryLine)
    {
    	if(SetCommandTarget(entryLine))
    	{
    		switch(entryLine[1])
    		{
    		case "!addtags" :
    			CommandAddTags(entryLine);
    			break;
    		case "!removetags" :
    			CommandRemoveTags(entryLine);
    			break;
    		case "!settags" :
    			CommandSetTags(entryLine);
    			break;
    		case "!setlang" :
    			if(selected == connection)
    			{
    				break;
    			}
    			CommandSetLang(entryLine);
    			break;
    		case "!setsources" :
    			if(selected != above)
    			{
    				break;
    			}
    			CommandSetAboveSources(entryLine);
    			break;
    		default :
    			break;
    		}
    	}
    	else
    	{
    		AddToDictionary(entryLine);
    	}
    }

    @Override
    public Dictionary Parse(String entry) {

    	String[] commandEntry;
    	int i;
        Reader in = new StringReader(entry);
        try {
            for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {

            	commandEntry = new String[record.size()];
            	i = 0;
            	
            	for (String field : record) {
            		commandEntry[i++] = field;
                }

            	ExecuteCSVLine(commandEntry);
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] array)
    {
    	SimpleTextParser tmp = new SimpleTextParser();
    	
    	String text;
		try {
			text = new String(Files.readAllBytes(Paths.get("tmp.txt")), StandardCharsets.UTF_8);
			tmp.Parse(text);
			System.out.print(tmp.outputDictionary.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
}
