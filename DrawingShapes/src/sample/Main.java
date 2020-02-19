package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        initUI(primaryStage);
    }

    private void initUI(Stage stage) {

        var root = new Pane();
        Button button = new Button("Draw a Figure");
        Label label = new Label("Shape Options");

        final int[] i = {0};

        Rect rect = new Rect(100, 50,200 ,200);
        Rect rect1 = new Rect(100, 50,150 ,150);
        Oval oval = new Oval(100, 100, 150 ,150);
        Line line = new Line(100,100, 200,150);
        Arc arc = new Arc(100, 110, 300, 90, 45, 240);
        Polygon polygon = new Polygon(new double[]{140, 200, 140, 200}, new double[]{120, 120, 200, 200}, 4);

        FiguresList list = new FiguresList();
        list.addShape(rect);
        list.addShape(rect1);
        list.addShape(oval);
        list.addShape(line);
        list.addShape(arc);
        list.addShape(polygon);

        var canvas = new Canvas(300, 300);
        var gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Shape shape = null;
                gc.clearRect(0, 0, 300, 300);
                if (i[0] == list.getSize) {
                    i[0] = 0;
                }
                shape = list.getShape(i[0]);
                shape.draw(gc);
                label.setText((shape.getName() == "Polygon") ? (((Polygon) shape).getParams()) : (shape.getAllParams()));
                ++i[0];
            }
        };

        button.setOnAction(event);

        root.getChildren().add(canvas);
        root.getChildren().add(button);
        root.getChildren().add(label);

        var VBox = new VBox(canvas, button, label);
        VBox.setSpacing(10);
        VBox.setLayoutX(20.0);

        var scene = new Scene(VBox, 420, 400, Color.WHITESMOKE);
        stage.setTitle("Lines");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
