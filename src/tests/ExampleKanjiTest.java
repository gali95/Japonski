package tests;

import alphabet_logic.Character;
import alphabet_logic.CharacterRandomizer;
import alphabet_logic.Kaiji;

/**
 * Created by gali95 on 18.09.16.
 */
public class ExampleKanjiTest {

    public static Test GetTest()
    {
        Kaiji example = new Kaiji();
        Character[] randomized = CharacterRandomizer.GetEveryCharRandom(example.content);
        Test ret = new Test();
        ret.chars = new TestField[example.content.length];
        for(int i=0;i<ret.chars.length;i++)
        {
            ret.chars[i] = new TestField();
            ret.chars[i].tested = randomized[i];
            ret.chars[i].success = false;
        }
        return ret;
    }

    public static Test[] GetPartedTest(int partSize)
    {
        Kaiji example = new Kaiji();
        Character[] randomized = CharacterRandomizer.GetEveryCharRandom(example.content);
        int testNum = (int)(Math.ceil(((double)randomized.length / partSize)));
        Test[] ret = new Test[testNum];
        int j=0;
        for(int i=0;i<testNum-1;i++)
        {
            ret[i] = new Test();
            ret[i].chars = new TestField[partSize];
        }
        ret[testNum-1] = new Test();
        ret[testNum-1].chars = new TestField[randomized.length-(testNum-1)*partSize];
        for(int i=0;i<testNum;i++)
        {
            for(int k=0;k<ret[i].chars.length;k++)
            {
                ret[i].chars[k] = new TestField();
                ret[i].chars[k].tested = randomized[j++];
                ret[i].chars[k].success = false;
            }
        }
        return ret;
    }

}
