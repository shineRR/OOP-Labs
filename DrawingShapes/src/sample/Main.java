package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.util.*;
import javafx.stage.FileChooser;

public class Main extends Application {

    private String helpForChoosingShapeText = "    <- Choose a figure";
    private String helpForChoosingFirstPointText = "    Click for the 1 point of your figure";
    private String helpForChoosingSecondPointText = "   Click for the 2 point of your figure";
    private String helpForDrawingManyAngleFigureText = "  Click for choosing points then right button click to draw";
    private Label hint = new Label(helpForChoosingShapeText);

    PSFiguresList list = new PSFiguresList();

    private sceneStates sceneState = sceneStates.WAITING_FOR_CHOOSING_FIGURE;
    private Canvas canvas = new Canvas(640, 480);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private PSShape chooseShape = null;
    private String currentClass = "";

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

    private void saveArrayList(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(stage);
        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(list);
        } catch (Exception exp) {
            return;
        };
    }

    private void openArrayList(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        try {
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            var newList = (PSFiguresList) objectStream.readObject();
            newList.printList();
            newList.PSShapes.forEach(shape -> list.addShape(shape, graphicsContext));
            objectStream.close();
        } catch (Exception exp) { System.out.print(exp.getClass().getCanonicalName()); };
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0,0 , canvas.getWidth(), canvas.getHeight());
        list.PSShapes.clear();
    }

    private void createObject() {
        try {
            System.out.println(currentClass);
            Object nameOfClass = Class.forName(currentClass).getConstructor().newInstance();
            chooseShape = (PSShape) nameOfClass;
            System.out.println(chooseShape);
            sceneState = chooseShape.quantityOfCoordinates() > 2 ? sceneStates.WAITING_FOR_N_POINTS :
                    sceneStates.WAITING_FOR_FIRST_POINT;
            hint.setText(sceneState == sceneStates.WAITING_FOR_N_POINTS ? helpForDrawingManyAngleFigureText :
                    helpForChoosingFirstPointText);
        } catch (Exception ex) {
            System.out.print(ex.getClass().getCanonicalName());
        };
    }

    private MenuBar createMenuOfApp(Stage stage,  ComboBox<String> shapesMenus) {
        Menu menu = new Menu("DrawingShapes");

        var menuItem1 = new MenuItem("Open");
        menuItem1.setOnAction(event -> openArrayList(stage));
        var menuItem2 = new MenuItem("Save");
        menuItem2.setOnAction(event -> saveArrayList(stage));
        var menuItem3 = new MenuItem("Import plugins");
        menuItem3.setOnAction(event -> { importPlugins(shapesMenus); });

        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        menu.getItems().add(menuItem3);

        var menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void importPlugins(ComboBox<String> menu) {
        menu.setItems(null);
        ObservableList<String> newListOfShapes = updateShapesMenu("out/production/DrawingShapes/Plugins");
        newListOfShapes.addAll(updateShapesMenu("out/production/DrawingShapes/Shapes"));
        chooseShape = null;
        currentClass = "";
        sceneState = sceneStates.WAITING_FOR_CHOOSING_FIGURE;
        hint.setText(helpForChoosingShapeText);
        menu.setItems(newListOfShapes);
    }

    private ComboBox<String> createMenuOfShapes() {
        ObservableList<String> nameOfShapes = updateShapesMenu("out/production/DrawingShapes/Shapes");
        ComboBox<String > cb = new ComboBox<String>(FXCollections.observableArrayList(nameOfShapes));
        return cb;
    }

    private void shapeSelection(ComboBox shapesMenu) {
        try {
            var list = updateShapesMenu("out/production/DrawingShapes/Shapes");
            currentClass = shapesMenu.getValue().toString();
            currentClass = list.contains(currentClass) ? "Shapes." + currentClass : "Plugins." + currentClass;
            createObject();
        } catch (Exception e){
            System.out.println(e.getClass().getCanonicalName());
        }
    }

    private ObservableList<String> updateShapesMenu(String pathToFolder) {
        ArrayList<String> nameOfShapes = new ArrayList<String>();
        try {

            Path path = FileSystems.getDefault().getPath(pathToFolder).toAbsolutePath();
            String s = path.toAbsolutePath().toString();

            File myFolder = new File(s);
            for (File file : Objects.requireNonNull(myFolder.listFiles())) {
                String string = file.getName();
                string = string.substring(0, string.lastIndexOf("."));
                if (string.length() > 0) {
                    nameOfShapes.add(string);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getCanonicalName());
        }

        return FXCollections.observableArrayList(nameOfShapes);
    }

    private void initUI(Stage stage) {
        var root = new Pane();
        graphicsContext.setFill(Color.RED);

        Button clearScreen = new Button("Clear");
        clearScreen.setOnAction(event -> clearCanvas());

        ComboBox<String> shapesMenu = createMenuOfShapes();
        shapesMenu.setOnAction(event -> shapeSelection(shapesMenu));
        MenuBar info = createMenuOfApp(stage, shapesMenu);
        FlowPane panel = new FlowPane(info, shapesMenu, clearScreen, hint);

        canvas.setOnMouseClicked(this::mouseClicked);
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
                    chooseShape.addPoints(point);
                    sceneState = sceneStates.WAITING_FOR_SECOND_POINT;
                    hint.setText(helpForChoosingSecondPointText);
                    break;
                case WAITING_FOR_SECOND_POINT:
                    chooseShape.addPoints(point);
                    list.addShape(chooseShape, graphicsContext);
                    createObject();
                    break;
                case WAITING_FOR_N_POINTS:
                    chooseShape.addPoints(point);
                    hint.setText(helpForDrawingManyAngleFigureText);
                    if (chooseShape.leftPoints() == 0 &&
                                chooseShape.quantityOfCoordinates() == chooseShape.minimumQuantityOfCoordinates()) {
                        list.addShape(chooseShape, graphicsContext);
                        createObject();
                    }
                    break;
            }
        } else if (e.getButton() == MouseButton.SECONDARY && sceneState != sceneStates.WAITING_FOR_CHOOSING_FIGURE) {
            if (chooseShape.leftPoints() == 0) {
                list.addShape(chooseShape, graphicsContext);
                createObject();
            } else {
                hint.setText("    Not enough points. " + chooseShape.leftPoints() + " more :)");
            }
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
