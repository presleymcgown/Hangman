import acm.graphics.GLine;

import java.awt.*;

public class Leg extends GLine {

    public Leg(double pointA, double pointB, double pointC, double pointD){
        super(pointA, pointB,pointC, pointD);
        this.setColor(new Color(1, 1, 1, 55));
    }
}
