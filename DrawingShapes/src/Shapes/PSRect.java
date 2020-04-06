package Shapes;

import Shapes.PSShape;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class PSRect extends PSShape {

    private int quantityOfCoordinates = 1;

    private Point[] point = new Point[quantityOfCoordinates];

    @Override
    public void draw(GraphicsContext g) {
//        g.rect();
//        g.fillRect();
        g.fillRect(point[0].x, point[0].y, point[1].y, point[1].y);
    }

}

