package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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

    private String helpForChoosingShapeText = "    <- Choose a figure";
    private String helpForChoosingFirstPointText = "    Click for the 1 point of your figure";
    private String helpForChoosingSecondPointText = "   Click for the 2 point of your figure";
    private String helpForDrawingPolygon = "  Click for choosing points then right button click to draw";
    private Label hint = new Label(helpForChoosingShapeText);

    PSFiguresList list = new PSFiguresList();

    private sceneStates sceneState = sceneStates.WAITING_FOR_CHOOSING_FIGURE;
    private PSShape choosedShape = null;

    private Canvas canvas = new Canvas(640, 480);

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

        ArrayList<String> nameOfShapes = new ArrayList<String>();
        Path path = FileSystems.getDefault().getPath("src/Shapes").toAbsolutePath();
        String s = path.toAbsolutePath().toString();

        File myFolder = new File(s);
        for (File file: myFolder.listFiles()) {
            String string = file.getName();
            string = string.substring(0, string.lastIndexOf("."));
            nameOfShapes.add(string);
        }

        ComboBox cb = new ComboBox(FXCollections.observableArrayList(nameOfShapes));

        return cb;
    }

    private void initUI(Stage stage) {

        var root = new Pane();

        MenuBar info = createMenuOfApp();
        ComboBox shapesMenu = createMenuOfShapes();
        EventHandler<ActionEvent> shapeFromComboBox = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String selectedShape = "Shapes." + shapesMenu.getValue().toString();
                try {
                    Object nameOfClass = Class.forName(selectedShape).getConstructor().newInstance();
                    choosedShape = (PSShape) nameOfClass;
                    sceneState = choosedShape.quantityOfCoordinates() > 2 ? sceneStates.WAITING_FOR_N_POINTS :
                                                                                    sceneStates.WAITING_FOR_FIRST_POINT;
                    hint.setText(sceneState == sceneStates.WAITING_FOR_N_POINTS ? helpForDrawingPolygon :
                                                                                    helpForChoosingFirstPointText);
                    System.out.print(choosedShape);
                } catch (Exception exp) {
                    System.out.print(exp);
                };
            }
        };
        shapesMenu.setOnAction(shapeFromComboBox);

        FlowPane panel = new FlowPane(info, shapesMenu, hint);

        canvas.setOnMouseClicked(mouseEvent -> this.mouseClicked(mouseEvent));
        var gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        root.getChildren().add(canvas);

        var VBox = new VBox(panel, canvas);
        VBox.setSpacing(10);
        VBox.setLayoutX(0.0);

        Scene scene = new Scene(VBox, 640, 480);
        stage.setTitle("Lines");
        stage.setScene(scene);
        stage.show();
    }

    private void mouseClicked(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());

        if (e.getButton() == MouseButton.PRIMARY) {
            System.out.println(e.getEventType() + "\n"
                        + "X : Y - " + e.getX() + " : " + e.getY() + "\n"
                        + "SceneX : SceneY - " + e.getSceneX() + " : " + e.getSceneY() + "\n"
                        + "ScreenX : ScreenY - " + e.getScreenX() + " : " + e.getScreenY() + " - " + sceneState + "\n");
            switch (sceneState) {
                case WAITING_FOR_CHOOSING_FIGURE:
                    break;
                case WAITING_FOR_FIRST_POINT:
                    choosedShape.addPoints(point);
                    sceneState = sceneStates.WAITING_FOR_SECOND_POINT;
                    hint.setText(helpForChoosingSecondPointText);
                    break;
                case WAITING_FOR_SECOND_POINT:
                    choosedShape.addPoints(point);
                        list.addShape(choosedShape);
                        choosedShape.draw(canvas.getGraphicsContext2D());
                        sceneState = sceneStates.WAITING_FOR_FIRST_POINT;
                        hint.setText(helpForChoosingFirstPointText);
                    break;
                case WAITING_FOR_N_POINTS:
//                    choosedShape.addPoints(point);
////                    count++;
////                    if (choosedShape.quantityOfCoordinates() ==  count) {
////                        list.addShape(choosedShape);
////                        choosedShape.draw(canvas.getGraphicsContext2D());
////                        count = 0;
////                    }
                    break;
            }
        } else if (e.getButton() == MouseButton.SECONDARY)
            System.out.print(e.getButton());
//            if (count >=  5) {
//                list.addShape(choosedShape);
//                choosedShape.draw(canvas.getGraphicsContext2D());
//            }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
