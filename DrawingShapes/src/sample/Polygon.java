package sample;

import javafx.scene.canvas.GraphicsContext;

public class Polygon extends Shape {

    double[] xPoints, yPoints;
    int nPoints;

    public Polygon() {
        super(0, 0, 0, 0, "Polygon");
        this.xPoints = null;
        this.yPoints = null;
        this.nPoints = 0;
    }

    public Polygon(double[] xPoints, double[] yPoints, int nPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nPoints = nPoints;
    }

    public String getParams() {
        String resultParams = name +  "(";
        resultParams += paramsOfArray(xPoints) + ", ";
        resultParams += paramsOfArray(yPoints);
        resultParams += ", " + nPoints + ")";
        return resultParams;
    }

    private String paramsOfArray(double[] array) {
        int i = 0;
        String params = "{";
        for (double item : array) {
            params += (i == array.length - 1) ? (item + "}") : (item + ", ");
            i++;
        }
        return params;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillPolygon(this.xPoints, this.yPoints, nPoints);
    }
}
