package sample;

import javafx.scene.canvas.GraphicsContext;

public class Rect extends Shape {

    public Rect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, "Rect");
    }

    public Rect() {
        super();
    }


    @Override
    public void draw(GraphicsContext g) {
        g.fillRect(getX1(), getY1(), getX2(), getY2());
    }

}

