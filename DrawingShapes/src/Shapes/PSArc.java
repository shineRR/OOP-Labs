package Shapes;

import Shapes.PSShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class PSArc extends PSShape {
    int startAngle, arcAngle;
//
//    public PSArc(double x, double y, double radiusX, double radiusY, double startAngle, double length) {
////        super(new Arc(x, y, radiusX, radiusY, startAngle, length));
//    }
    public PSArc(int x1, int y1, int x2, int y2, int startAngle, int arcAngle) {
        super(x1, y1, x2, y2, "Arc");
        this.arcAngle = arcAngle;
        this.startAngle = startAngle;
    }

    public PSArc() {
        super(0,0,0,0,"Arc");
        this.arcAngle = 0;
        this.startAngle = 0;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillArc(getX1(), getY1(), getX2(), getY2(), this.startAngle, this.arcAngle, ArcType.ROUND);
    }
}
