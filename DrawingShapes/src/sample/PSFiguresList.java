package sample;

import java.util.ArrayList;

public class PSFiguresList {

    public ArrayList<PSShape> PSShapes = new ArrayList<PSShape>();
    public PSShape nowShapes = null;
    int getSize = 0;

    public void addShape(PSShape newShapes) {
        PSShapes.add(newShapes);
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
