package Shapes;

import Shapes.PSShape;
import javafx.scene.canvas.GraphicsContext;
import sample.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PSPolygon extends PSShape {

    double[] xPoints, yPoints;
    int nPoints;

    public PSPolygon() {
//        super(0, 0, 0, 0, "Polygon");
        this.xPoints = null;
        this.yPoints = null;
        this.nPoints = 0;
    }

    public PSPolygon(Point[] points) {
        this.xPoints = getXPoints(points);
        this.yPoints = getYPoints(points);
        this.nPoints = points.length;
    }

    private double[] getXPoints(Point[] points) {
        double[] XPoints = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            XPoints[i] = points[i].x;
        }
        return XPoints;
    }

    private double[] getYPoints(Point[] points) {
        double[] YPoints = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            YPoints[i] = points[i].x;
        }
        return YPoints;
    }

//    public String getParams() {
//        String resultParams = name +  "(";
//        resultParams += paramsOfArray(xPoints) + ", ";
//        resultParams += paramsOfArray(yPoints);
//        resultParams += ", " + nPoints + ")";
//        return resultParams;
//    }
//
//    private String paramsOfArray(double[] array) {
//        int i = 0;
//        String params = "{";
//        for (double item : array) {
//            params += (i == array.length - 1) ? (item + "}") : (item + ", ");
//            i++;
//        }
//        return params;
//    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillPolygon(this.xPoints, this.yPoints, nPoints);
    }
}
