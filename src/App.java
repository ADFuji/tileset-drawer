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
        MyCanvas canvas = new MyCanvas(10, 10);
        Scene scene = new Scene(canvas, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Drawing Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}