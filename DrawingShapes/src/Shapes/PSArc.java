package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import sample.PSShape;
import sample.Point;

public class PSArc extends PSShape {
    double startAngle = 90, arcAngle;

    public int quantityOfCoordinates = 2;
    private int currentCoordinate = 0;
    private Point[] points = new Point[quantityOfCoordinates];

    private double getArcAngle() {
        return (points[1].x - points[0].x < 0 ? 180 : -180);
    }

    @Override
    public int leftPoints() {
        return quantityOfCoordinates - currentCoordinate;
    }

    @Override
    public int quantityOfCoordinates() {
        return quantityOfCoordinates;
    }

    @Override
    public void addPoints(Point point) {
        points[currentCoordinate] = point;
        currentCoordinate++;
    }

    @Override
    public void draw(GraphicsContext g) {
        currentCoordinate = 0;
        arcAngle = getArcAngle();
        points = validateValuesForTwoCoordinates(points);
        g.fillArc(points[0].x, points[0].y, (points[1].x - points[0].x), (points[1].y - points[0].y),
                startAngle, arcAngle, ArcType.ROUND);
    }
}
