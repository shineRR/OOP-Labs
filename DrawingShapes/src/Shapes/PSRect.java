package Shapes;

import Shapes.PSShape;
import javafx.scene.canvas.GraphicsContext;

public class PSRect extends PSShape {

//    public Rect(double x, double y, double width, double height) {
//        super(new Rectangle(x, y, width, height));
//    }

    public PSRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, "Rect");
    }

    public PSRect() {
        super();
    }


    @Override
    public void draw(GraphicsContext g) {
//        g.rect();
//        g.fillRect();
        g.fillRect(getX1(), getY1(), getX2(), getY2());
    }

}

