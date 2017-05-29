package alphabet_logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by gali95 on 20.08.16.
 */
public class CharacterRandomizer {

    public static Character[] GetEveryCharRandom(Character[] content)
    {

        Character[] ret = content.clone();
        Character temp;
        int rand;

        Random generator = new Random();

        for(int i=0;i<ret.length;i++) {

            temp = ret[i];
            rand = generator.nextInt(ret.length);
            ret[i] = ret[rand];
            ret[rand] = temp;

        }
        return ret;

    }
    public static List<Integer> GetLenghts(int min, int max,int numberOfAllChars)
    {

        Random generator = new Random();

        List<Integer> ret;
        ret = new LinkedList<Integer>();

        int rand;
        int all = numberOfAllChars;
        while(all>0) {

            rand = generator.nextInt((max-min+1)) + min;
            if(rand > all) rand = all;
            ret.add(rand);
            all -= rand;
        }
        return ret;
    }
    public static String[] GetCompleteString(int min,int max,Character[] content)
    {
        Character[] chars = GetEveryCharRandom(content);
        List<Integer> lenghts = GetLenghts(min,max,content.length);
        String[] ret = new String[lenghts.size()];
        int k=0;
        for(int i=0;i<ret.length;i++)
        {
            ret[i] = "";
            for(int j=0;j<lenghts.get(i);j++)
            {
                ret[i] += chars[k++].engLook;
                ret[i] += " ";
            }

        }
        return ret;
    }
    public static String GetExampleAll(Character[] content)
    {
        String[] entry = GetCompleteString(3,5,content);
        String ret = "";
        for(int i=0;i<entry.length;i++)
        {
            ret += entry[i];
            ret += "\n";
        }
        return ret;
    }
    public static String GetExampleAll(Character[] content,int min, int max)
    {
        String[] entry = GetCompleteString(min,max,content);
        String ret = "";
        for(int i=0;i<entry.length;i++)
        {
            ret += entry[i];
            ret += "\n";
        }
        return ret;
    }

}
