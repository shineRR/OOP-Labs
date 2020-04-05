package Shapes;

import Shapes.PSShape;
import javafx.scene.canvas.GraphicsContext;

public class PSOval extends PSShape {

//    public Oval(double radius) {
//        super(new Circle(radius));
//    }

    public PSOval(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, "Oval");
    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillOval(getX1(), getY1(), getX2(), getY2());
    }
}
