import acm.graphics.GLine;

import java.awt.*;

public class Arm extends GLine {

    public Arm(double pointA, double pointB, double pointC, double pointD){
        super(pointA, pointB,pointC, pointD);
        this.setColor(new Color(1, 1, 1, 55));
    }
}
