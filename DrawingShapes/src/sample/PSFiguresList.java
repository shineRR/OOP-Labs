package sample;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;

public class PSFiguresList implements Serializable {

    public ArrayList<PSShape> PSShapes = new ArrayList<PSShape>();
    public PSShape nowShapes = null;
    int getSize = 0;

    public void addShape(PSShape newShapes, GraphicsContext gc) {
        PSShapes.add(newShapes);
        newShapes.draw(gc);
        nowShapes = newShapes;
        ++getSize;
    }

    public void printList() {
        System.out.print("List: \n");
        for (PSShape psShape: PSShapes) {
            System.out.print(psShape.getClass());
        }
    }

    public PSShape getShape(int i){
        return PSShapes.get(i);
    }
}
