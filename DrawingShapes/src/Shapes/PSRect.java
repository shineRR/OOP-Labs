package Shapes;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import sample.PSShape;
import javafx.scene.canvas.GraphicsContext;
import sample.Point;

public class PSRect extends PSShape {

    private int quantityOfCoordinates = 2;
    private int currentCoordinate = 0;
    private Point[] points = new Point[quantityOfCoordinates];

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
        points = validateValuesForTwoCoordinates(points);
        g.fillRect(points[0].x, points[0].y, points[1].x - points[0].x, points[1].y - points[0].y);

    }
}

