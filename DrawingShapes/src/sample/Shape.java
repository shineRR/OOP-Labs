package sample;

import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    public int x1, y1, x2, y2;
    public String name;

    public Shape() {
        this(0, 0, 1, 1, "Polygon");
    }

    public Shape(int x1, int y1, int x2, int y2, String name) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.name = name;
    }

    public String getAllParams() {
        return getName() + "(" + getX1() + ", " + getY1() + ", "
                + getX2() + ", " + getY2() + ")";
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x) {
        this.x1 = x;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y) {
        this.y1 = y;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y1) {
        this.y2 = y2;
    }

    public String getName() {
        return name;
    }

    public abstract void draw(GraphicsContext g);
}
