//import all javaFX classes

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.Stage;
import java.util.Calendar;
import java.util.Date;
public class App extends Application {

    @Override
    public void start(Stage stage) {
        MyTileset tileset = new MyTileset("tileset");
        VBox master = new VBox();
        //init a listview
        ListView<MyTile> listview = new ListView<MyTile>();
        ObservableList<MyTile> items = listview.getItems();
        Button addButton = new Button("Add");
        addButton.setOnAction(e->{
            MyTile tile = new MyTile("tile", 32,32,10);
            tileset.addTile(tile);
            list.getItems().add(tile);
        });
        

        //create a canvas
        int scale = 10;
        MyCanvas myCanvas = new MyCanvas(32, 32, scale);
        ColorPicker colorPicker = new ColorPicker();
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
        colorPicker.setOnAction(e -> {
            myCanvas.setColor(colorPicker.getValue());
        });
        Spinner<Integer> spinner = new Spinner<Integer>(1, 10, 1);
        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            //myCanvas.setScale(newValue);
        });
        ToolBar toolBar = new ToolBar(colorPicker, clearButton, spinner);
        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(myCanvas);
        root.setStyle("-fx-background-color: #000000;");
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Drawing Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}