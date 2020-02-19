package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Arc extends Shape {
    int startAngle, arcAngle;

    public Arc(int x1, int y1, int x2, int y2, int startAngle, int arcAngle) {
        super(x1, y1, x2, y2, "Arc");
        this.arcAngle = arcAngle;
        this.startAngle = startAngle;
    }

    public Arc() {
        super(0,0,0,0,"Arc");
        this.arcAngle = 0;
        this.startAngle = 0;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillArc(getX1(), getY1(), getX2(), getY2(), this.startAngle, this.arcAngle, ArcType.ROUND);
    }
}
