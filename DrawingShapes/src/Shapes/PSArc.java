package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import sample.PSShape;
import sample.Point;

public class PSArc extends PSShape {
    int startAngle, arcAngle;

    public int quantityOfCoordinates = 2;
    private int currentCoordiante = 0;
    private Point[] points = new Point[quantityOfCoordinates];


    @Override
    public int quantityOfCoordinates() {
        return quantityOfCoordinates;
    }

    @Override
    public void addPoints(Point point) {
        points[currentCoordiante] = point;
        currentCoordiante++;
    }

    @Override
    public void draw(GraphicsContext g) {
        currentCoordiante = 0;
        g.fillArc(points[0].x, points[0].y, Math.abs(points[1].x - points[0].x), Math.abs(points[1].y - points[0].y),
                                                                                0, 90, ArcType.OPEN);
    }
}
