package main;

import java.io.Serializable;

/**
 * Created by gali95 on 10.09.16.
 */
public class Line implements Serializable {

    public Vector2D a,b;

    public Line(double aX, double aY, double bX, double bY)
    {
        a = new Vector2D();
        b = new Vector2D();
        a.setLocation(aX,aY);
        b.setLocation(bX,bY);

    }
}
