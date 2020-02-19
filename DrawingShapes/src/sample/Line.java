package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, "Line");
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setStroke(Color.RED);
        g.setLineWidth(3.0);
        g.strokeLine(getX1(), getY1(), getX2(), getY2());
    }

}
