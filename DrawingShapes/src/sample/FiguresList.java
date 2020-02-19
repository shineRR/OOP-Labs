package sample;

import java.util.ArrayList;

public class FiguresList {

    public ArrayList<Shape> shapes = new ArrayList<Shape>();
    public Shape nowShapes = null;
    int getSize = 0;

    public void addShape(Shape newShapes) {
        shapes.add(newShapes);
        nowShapes = newShapes;
        ++getSize;
    }

    public Shape getShape(int i){
        return shapes.get(i);
    }
}
