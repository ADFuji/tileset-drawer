package studio.fuji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        MyTileset tileset = new MyTileset("first tileset");
        tileset.addTile(32, 32);
        tileset.addTile(32, 32);
        tileset.addTile(32, 32);

        VBox master = new VBox();
        StackPane detail = new StackPane();
        for (int i = 0; i < tileset.getTiles().size(); i++) {
            MyTile tile = tileset.getTiles().get(i);
            master.getChildren().add(tile);
            tileset.getTiles().get(i).setOnMouseClicked(event -> {
                detail.getChildren().clear();
                // add the canvas to the detail pane
                detail.getChildren().add(tile.getCanvas());
                tile.getCanvas().changeScale(16);
            });
        }

        GridPane grid = new GridPane();
        grid.add(master, 0, 0);
        grid.add(detail, 1, 0);
        scene = new Scene(grid, 640, 640);
        // if i press on key "s" i want to save the tileset
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S:
                    tileset.saveAs(5, 5, "./tileset.png");
                    for (int i = 0; i < tileset.getTiles().size(); i++) {
                        tileset.getTiles().get(i).saveAs(String.format("./tile%d.png", i));
                    }
                    break;
                case B:
                    for (int i = 0; i < tileset.getTiles().size(); i++) {
                        tileset.getTiles().get(i).getCanvas().setColor("#000000");
                    }
                    break;
                case W:
                    for (int i = 0; i < tileset.getTiles().size(); i++) {
                        tileset.getTiles().get(i).getCanvas().setColor("#ffffff");
                    }
                    break;
                case V:
                    for (int i = 0; i < tileset.getTiles().size(); i++) {
                        tileset.getTiles().get(i).getCanvas().setColor("#ff0000");
                    }
                    break;
                default:
                    break;
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}