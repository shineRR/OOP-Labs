package Shapes;

import Shapes.PSShape;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PSLine extends PSShape {

    public int quantityOfCoordinates = 1;

    private Point[] point = new Point[quantityOfCoordinates];

    @Override
    public void draw(GraphicsContext g) {
        g.setStroke(Color.RED);
        g.setLineWidth(3.0);
        g.strokeLine(point[0].x, point[0].y, point[1].y, point[1].y);
    }

}
