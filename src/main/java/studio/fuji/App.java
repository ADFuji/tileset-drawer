package studio.fuji;

//import stage
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.Vector;

//import button from javafx
import javafx.scene.control.Button;
//import scrollpane from javafx
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;

/**
 * JavaFX App
 */
public class App extends Application {
    private static MyTileset tileset;
    private static Scene scene;
    private static MyTile selectedTile;

    @Override
    public void start(Stage stage) throws IOException {
        tileset = new MyTileset("tileset");
        BorderPane main_layout = new BorderPane();
        BorderPane detail_layout = new BorderPane();
        ScrollPane ddetail = new ScrollPane();
        StackPane detail = new StackPane();
        detail.getChildren().add(new Label("Detail"));
        ddetail.setContent(detail);
        detail_layout.setCenter(ddetail);

        HBox control = new HBox();
        Slider slider = new Slider(0, 100, 1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        control.getChildren().add(slider);
        detail_layout.setBottom(control);

        BorderPane master = new BorderPane();
        {
            VBox tiles = new VBox();
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(tiles);
            scroll.setFitToWidth(true);
            scroll.setFitToHeight(true);
            scroll.setPrefHeight(500);
            // change the size of the current tile
            slider.valueProperty().addListener((Observable o) -> {
                Vector<MyTile> tileList = tileset.getTiles();
                for (int i = 0; i < tileList.size(); i++) {
                    tileList.get(i).getCanvas().setSize((int) slider.getValue());
                }
            });
            detail_layout.setOnScroll(e -> {
                System.out.println(e);
                // if i press shift and scroll, i change the size of the current tile
                if (e.isShiftDown()) {
                    slider.setValue(slider.getValue() + e.getDeltaY());
                }
                // if press ctrl and scroll, i change x axis of ddetail
                if (e.isControlDown()) {
                    ddetail.setHvalue(ddetail.getHvalue() + e.getDeltaY() / 1000);
                }
                // if press alt and scroll, i change y axis of ddetail
                if (e.isAltDown()) {
                    ddetail.setVvalue(ddetail.getVvalue() + e.getDeltaY() / 1000);
                }
            });
            master.setTop(scroll);
            HBox buttons = new HBox();
            {
                Button new_tile = new Button("New Tile");
                new_tile.setOnAction(event -> {
                    MyTile tile = new MyTile(32, 32);
                    tile.setOnMouseClicked(event2 -> {
                        detail.getChildren().remove(0);
                        detail.getChildren().add(tile.getCanvas());
                        selectedTile = tile;
                    });
                    tileset.addTile(tile);
                    tiles.getChildren().add(tile);
                });
                Button rem_tile = new Button("Remove Tile");
                rem_tile.setOnAction(event -> {
                    if (tileset.getTileCount() > 0) {
                        tileset.removeTile(tileset.getTileCount() - 1);
                        tiles.getChildren().remove(tiles.getChildren().size() - 1);
                    }
                });
                buttons.getChildren().addAll(new_tile, rem_tile);
            }
            master.setBottom(buttons);
            // master display flex column justify-content space-between
            master.setStyle("-fx-display: flex; -fx-flex-direction: column; -fx-justify-content: space-between");
        }
        // change the width of the master
        master.setPrefWidth(200);

        main_layout.setLeft(master);
        main_layout.setCenter(detail_layout);

        scene = new Scene(main_layout, 640, 640);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
