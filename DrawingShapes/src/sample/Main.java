package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        initUI(primaryStage);
    }

    private MenuBar createMenuOfApp() {
        Menu menu = new Menu("DrawingShapes");

        MenuItem menuItem1 = new MenuItem("Open");
        MenuItem menuItem2 = new MenuItem("Save");
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private MenuBar createMenuOfShapes() {
        Menu menu = new Menu("Shapes");

        RadioMenuItem choice1Item = new RadioMenuItem("Circle");
        RadioMenuItem choice2Item = new RadioMenuItem("Rectangle");
        RadioMenuItem choice3Item = new RadioMenuItem("Polygon");

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(choice1Item);
        toggleGroup.getToggles().add(choice2Item);
        toggleGroup.getToggles().add(choice3Item);

        menu.getItems().add(choice1Item);
        menu.getItems().add(choice2Item);
        menu.getItems().add(choice3Item);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void initUI(Stage stage) {

        var root = new Pane();

        FlowPane panel = new FlowPane(createMenuOfApp(), createMenuOfShapes());
        PSFiguresList list = new PSFiguresList();

        var canvas = new Canvas(300, 300);
        var gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        root.getChildren().add(canvas);

        var VBox = new VBox(panel, canvas);
        VBox.setSpacing(10);
        VBox.setLayoutX(0.0);

        Scene scene = new Scene(VBox, 640, 480);
        scene.setOnMouseClicked(getCoordinates);
        stage.setTitle("Lines");
        stage.setScene(scene);
        stage.show();
    }

    private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
//            PSShape figureShape = null;
//            gc.clearRect(0, 0, 300, 300);
//            if (i[0] == list.getSize) {
//                i[0] = 0;
//            }
//            figureShape = list.getShape(i[0]);
//            figureShape.draw(gc);
//            label.setText((figureShape.getName() == "Polygon") ? (((PSPolygon) figureShape).getParams()) : (figureShape.getAllParams()));
//            ++i[0];
        }
    };

    private EventHandler<MouseEvent> getCoordinates = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("x = " + event.getSceneX());
            System.out.println("y = " + event.getSceneY());
        }
    };

    public static void main(String[] args) {

        launch(args);
    }
}
