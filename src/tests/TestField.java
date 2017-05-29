package tests;

import alphabet_logic.Character;
import main.Line;

import java.io.Serializable;

/**
 * Created by gali95 on 12.08.16.
 */
public class TestField implements Serializable {

    public Character tested;
    public Boolean success;
    public Line[] lines;

    public TestField()
    {

    }

}
