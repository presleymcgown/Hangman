import acm.graphics.GCanvas;
import acm.graphics.GOval;

import java.awt.*;

public class Head extends GOval {

    private GCanvas screen;
    public Color color;

    public Head (double x, double y, double size, GCanvas screen, Color color){

        super(x, y, size, size);

        setFilled(false);

        this.setColor(new Color(1, 1, 1, 55));
        this.screen = screen;
        this.color = color;

    }

}
