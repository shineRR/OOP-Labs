package sample;

import javafx.scene.canvas.GraphicsContext;

public class Oval extends Shape {

    public Oval(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, "Oval");
    }

    public Oval() {
        super();
    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillOval(getX1(), getY1(), getX2(), getY2());
    }
}
