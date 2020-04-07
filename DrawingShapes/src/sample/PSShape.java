package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import sample.Point;

import java.awt.*;
import java.io.Serializable;

public abstract class PSShape implements Serializable {

    public Point[] validateValuesForTwoCoordinates(Point[] points) {
        double dstX2X1 = points[1].x - points[0].x;;
        double dstY2Y1 = points[1].y - points[0].y;;

        if (dstX2X1 < 0) {
            double temp = points[0].x;
            points[0].x = points[1].x;
            points[1].x = temp;
        }

        if (dstY2Y1 < 0) {
            double temp = points[0].y;
            points[0].y = points[1].y;
            points[1].y = temp;
        }
        return points;
    }

    public abstract int leftPoints();
    public abstract int quantityOfCoordinates();
    public abstract void addPoints(Point point);
    public abstract void draw(GraphicsContext g);
}
