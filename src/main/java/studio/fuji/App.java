package studio.fuji;
//import stage
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.io.IOException;
//import button from javafx
import javafx.scene.control.Button;
//import scrollpane from javafx
import javafx.scene.control.ScrollPane;

/**
 * JavaFX App
 */
public class App extends Application {
    private static MyTileset tileset;
    private static Scene scene;

    /*
    @Override
    public void start(Stage stage) throws IOException {
        MyTileset tileset = new MyTileset("tileset");
        MyTile tile = new MyTile(32, 32);
        MyTile tile2 = new MyTile(32, 32);
        MyTile tile3 = new MyTile(32, 32);
        MyTile tile4 = new MyTile(32, 32);
        MyTile tile5 = new MyTile(32, 32);
        MyTile tile6 = new MyTile(32, 32);
        tileset.addTile(tile);
        tileset.addTile(tile2);
        tileset.addTile(tile3);
        tileset.addTile(tile4);
        tileset.addTile(tile5);
        tileset.addTile(tile6);

        

        VBox master = new VBox();
        StackPane detail = new StackPane();
        detail.getChildren().add(tileset.getTile(0).getCanvas());
        
        for(int i=0; i<tileset.getTileCount(); i++){
            MyTile t = tileset.getTile(i);
            t.setOnMouseClicked(event->{
                detail.getChildren().remove(0);
                detail.getChildren().add(t.getCanvas());
            });
            master.getChildren().add(t);
        }
        

        GridPane grid = new GridPane();
        grid.add(master, 0, 0);
        grid.add(detail, 1, 0);
        scene = new Scene(grid, 640, 640);
        //if i press s
        scene.setOnKeyPressed(event->{
            switch(event.getCode()){
                case S:
                    tileset.saveAs("tileset.png");
                    break;
            }
        });
        stage.setScene(scene);
        stage.show();
    }
    */
    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws IOException{
        tileset = new MyTileset("tileset");
        BorderPane main_layout = new BorderPane();
        StackPane detail = new StackPane();
        detail.getChildren().add(new Label("Detail"));
        detail.setStyle("-fx-background-color: #ffffff");
        
        VBox master = new VBox();
        {
            VBox tiles = new VBox();
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(tiles);
            scroll.setFitToWidth(true);
            scroll.setFitToHeight(true);
            master.getChildren().add(scroll);
            HBox buttons = new HBox();
            {
                Button new_tile = new Button("New Tile");
                new_tile.setOnAction(event->{
                    MyTile tile = new MyTile(32, 32);
                    tile.setOnMouseClicked(event2->{
                        detail.getChildren().remove(0);
                        detail.getChildren().add(tile.getCanvas());
                    });
                    tileset.addTile(tile);
                    tiles.getChildren().add(tile);
                });
                Button rem_tile = new Button("Remove Tile");
                rem_tile.setOnAction(event->{
                    if(tileset.getTileCount()>0){
                        tileset.removeTile(tileset.getTileCount()-1);
                        tiles.getChildren().remove(tiles.getChildren().size()-1);
                    }
                });
                buttons.getChildren().addAll(new_tile, rem_tile);
            }
            GridPane grid = new GridPane();
            grid.add(scroll, 0, 0);
            grid.add(buttons, 0, 1);
            grid.setPrefHeight(640);
            grid.setPrefWidth(200);
            //change the size of the scroll cell to fit the content
            scroll.setPrefHeight(640);
            scroll.setPrefWidth(200);
            
            master.getChildren().add(grid);
        }
        master.setStyle("-fx-background-color: #0000ff");
        //change the width of the master
        master.setPrefWidth(200);
        
        main_layout.setLeft(master);
        main_layout.setCenter(detail);



        scene = new Scene(main_layout, 640, 640);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
