package main;

import java.io.Serializable;

/**
 * Created by Lach on 2016-12-21.
 */
public class Vector2D implements Serializable{

    public double x,y;

    public void setLocation(double x,double y)
    {
        this.x = x;
        this.y = y;
    }

}
