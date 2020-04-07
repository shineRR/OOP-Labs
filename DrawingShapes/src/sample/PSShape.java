package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import sample.Point;

import java.awt.*;

public abstract class PSShape {

    public abstract int quantityOfCoordinates();
    public abstract void addPoints(Point point);
    public abstract void draw(GraphicsContext g);
}
