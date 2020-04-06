package sample;

import Shapes.PSShape;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.util.ArrayList;


public class Main extends Application {

    private String helpForChoosingFirstPointText = "    Click for the 1 point of your figure";
    private String helpForChoosingSecondPointText = "   Click for the 2 point of your figure";
    private String helpForDrawingPolygon = "  Click for choosing points then right button click to draw";

    private sceneStates sceneState = sceneStates.WAITING_FOR_CHOOSING_FIGURE;
    private PSShape choosedShape = null;

    private enum sceneStates {
        WAITING_FOR_CHOOSING_FIGURE,
        WAITING_FOR_FIRST_POINT,
        WAITING_FOR_SECOND_POINT,
        WAITING_FOR_N_POINTS
    }

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

    private ComboBox createMenuOfShapes() {

        ArrayList<String> kek = new ArrayList<String>();
        Path path = FileSystems.getDefault().getPath("src/Shapes").toAbsolutePath();
        String s = path.toAbsolutePath().toString();

        File myFolder = new File(s);
        for (File i: myFolder.listFiles()) {
            String string = i.getName();
            string = string.substring(0, string.length() - 5);
            kek.add(string);
        }

        ComboBox cb = new ComboBox(FXCollections.observableArrayList(kek));

        return cb;
    }

    private void initUI(Stage stage) {

        var root = new Pane();
        PSFiguresList list = new PSFiguresList();
        String helpForChoosingShapeText = "    <- Choose a figure";
        Label hint = new Label(helpForChoosingShapeText);

        MenuBar info = createMenuOfApp();
        ComboBox shapesMenu = createMenuOfShapes();
        EventHandler<ActionEvent> shapeFromComboBox = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.print(shapesMenu.getValue() + " selected");
                try {
                    Object nameOfClass = Class.forName("Shapes.PSLine").getConstructor().newInstance();
                    choosedShape = (PSShape) nameOfClass;
                    System.out.print(choosedShape);
                } catch (Exception exp) {
                    System.out.print(exp);
                };

                hint.setText(helpForChoosingFirstPointText);
            }
        };
        shapesMenu.setOnAction(shapeFromComboBox);

        FlowPane panel = new FlowPane(info, shapesMenu, hint);

        var canvas = new Canvas(300, 300);
        var gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        root.getChildren().add(canvas);

        var VBox = new VBox(panel, canvas);
        VBox.setSpacing(10);
        VBox.setLayoutX(0.0);

        Scene scene = new Scene(VBox, 640, 480);
//        scene.setOnMouseClicked(getCoordinates);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickHandler);
        stage.setTitle("Lines");
        stage.setScene(scene);
        stage.show();
    }

    EventHandler<MouseEvent> mouseClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.out.println(mouseEvent.getEventType() + "\n"
                        + "X : Y - " + mouseEvent.getX() + " : " + mouseEvent.getY() + "\n"
                        + "SceneX : SceneY - " + mouseEvent.getSceneX() + " : " + mouseEvent.getSceneY() + "\n"
                        + "ScreenX : ScreenY - " + mouseEvent.getScreenX() + " : " + mouseEvent.getScreenY());
                switch (sceneState) {
                    case WAITING_FOR_CHOOSING_FIGURE:
                        break;
                    case WAITING_FOR_FIRST_POINT:
                        break;
                    case WAITING_FOR_SECOND_POINT:
                        break;
                    case WAITING_FOR_N_POINTS:
                        break;
                }
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY)
                System.out.print(mouseEvent.getButton());
        }
    };

    public static void main(String[] args) {

        launch(args);
    }
}
