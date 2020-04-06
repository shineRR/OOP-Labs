package Shapes;

import Shapes.PSShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class PSArc extends PSShape {
    int startAngle, arcAngle;

    @Override
    public void draw(GraphicsContext g) {
//        g.fillArc(getX1(), getY1(), getX2(), getY2(), this.startAngle, this.arcAngle, ArcType.ROUND);
    }
}
