package studio.fuji;

import javafx.beans.Observable;

import javafx.stage.FileChooser;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import list view
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
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
    private ListView<MyTile> list;

    @Override
    public void start(Stage stage) throws IOException {
        //menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Menu menuFile = new Menu("File");
        {
            MenuItem menuFileNew = new MenuItem("New Tileset..");
            menuFileNew.setOnAction((event) -> {
                NewTileset dialog = new NewTileset();
                dialog.showAndWait();
                String name = (String)dialog.getValues()[0];
                int width = (int)dialog.getValues()[1];
                int height = (int)dialog.getValues()[2];
                tileset = new MyTileset(name, width, height);
                tileset.setPath("C:\\Users\\fuji\\Desktop\\test.tileset");
                //clear the list view
                list.getItems().clear();
                MyTile.tileCount = 0;
            });
            MenuItem menuFileOpen = new MenuItem("Open Tileset..");
            menuFileOpen.setOnAction((event) -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Tileset");
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Tileset", "*.tileset")
                );
                java.io.File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    tileset = MyTileset.open(file.getAbsolutePath());
                    //clear the list view
                    list.getItems().clear();
                    //add the tiles to the list view
                    for (MyTile tile : tileset.getTiles()) {
                        list.getItems().add(tile);
                    }
                }
            });
            MenuItem menuFileSave = new MenuItem("Save Tileset");
            menuFileSave.setOnAction((event) -> {
                tileset.save();
            });
            MenuItem menuFileSaveAs = new MenuItem("Save Tileset As..");
            menuFileSaveAs.setOnAction((event) -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Tileset");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tileset", "*.tileset"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Tiles", "*.*"));

                java.io.File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    if(fileChooser.getSelectedExtensionFilter().getDescription().equals("Tileset")) {
                        tileset.saveAsTileset(file.toString());
                    } else if(fileChooser.getSelectedExtensionFilter().getDescription().equals("PNG")) {
                        tileset.saveAsPng(file.toString());
                    } else {
                        tileset.saveAll(file.toString());
                    }
                }
            });
            MenuItem menuFileExit = new MenuItem("Exit");
            menuFileExit.setOnAction((event) -> {
                System.exit(0);
            });
            menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, menuFileSaveAs);
            //add separator
            menuFile.getItems().add(new javafx.scene.control.SeparatorMenuItem());
            menuFile.getItems().add(menuFileExit);
        }
        Menu menuEdit = new Menu("Edit");
        {
            MenuItem menuEditUndo = new MenuItem("Undo");
            MenuItem menuEditRedo = new MenuItem("Redo");
            MenuItem menuEditCut = new MenuItem("Cut");
            MenuItem menuEditCopy = new MenuItem("Copy");
            MenuItem menuEditPaste = new MenuItem("Paste");
            MenuItem menuEditDelete = new MenuItem("Delete");
            menuEdit.getItems().addAll(menuEditUndo, menuEditRedo, menuEditCut, menuEditCopy, menuEditPaste, menuEditDelete);
        }
        Menu menuView = new Menu("View");
        {
            MenuItem menuViewZoomIn = new MenuItem("Zoom In");
            MenuItem menuViewZoomOut = new MenuItem("Zoom Out");
            MenuItem menuViewZoomReset = new MenuItem("Zoom Reset");
            menuView.getItems().addAll(menuViewZoomIn, menuViewZoomOut, menuViewZoomReset);
        }
        Menu menuHelp = new Menu("Help");
        {
            MenuItem menuHelpAbout = new MenuItem("About");
            menuHelp.getItems().addAll(menuHelpAbout);
        }
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
        


        tileset = new MyTileset("tileset");
        BorderPane main_layout = new BorderPane();
        Detail detail = new Detail();
        

        BorderPane master = new BorderPane();
        {   
            list = new ListView<MyTile>();
            list.setStyle("-fx-background-color: #BBBBBB;-fx-background-insets: 0, 0;");
            ObservableList<MyTile> data = FXCollections.observableArrayList();
            //when a tile is selected, it is set as the selected tile
            list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends MyTile> ov, MyTile old_val, MyTile new_val) -> {
                selectedTile = new_val;
                detail.setDisplay(new_val);
            });
            list.setItems(data);
            master.setTop(list);
            HBox buttons = new HBox();
            {
                Button new_tile = new Button("New Tile");
                new_tile.setOnAction(event -> {
                    tileset.newTile();
                    MyTile tile = tileset.getTile(tileset.getTileCount() - 1);
                    System.out.println(tile);
                    System.out.println(tileset.getTileCount());
                    tile.setOnMouseClicked(event2 -> {
                        selectedTile = tile;
                        detail.setDisplay(tile);
                    });
                    //on a double click, open the tile editor
                    tile.setOnMousePressed(event2 -> {
                        if(event2.getClickCount() == 2) {
                            //TODO rename this
                        }
                    });
                    data.add(tile);
                    list.setStyle("-fx-background-color: #BBBBBB;-fx-background-insets: 0, 0;");
                });
                Button rem_tile = new Button("Remove Tile");
                rem_tile.setOnAction(event -> {
                    if (tileset.getTileCount() > 0) {
                        tileset.removeTile(selectedTile);
                        data.remove(selectedTile);
                    }
                });
                buttons.getChildren().addAll(new_tile, rem_tile);
            }
            master.setBottom(buttons);
        }
        // change the width of the master
        master.setPrefWidth(200);
        master.setStyle("-fx-background-insets: 0, 0;");
        main_layout.setTop(menuBar);
        main_layout.setLeft(master);
        main_layout.setCenter(detail);
        //detail.getLayout doit correspondre a l'espace libre dans le main_layout
        detail.getLayout().prefWidthProperty().bind(main_layout.widthProperty().subtract(master.getPrefWidth()));
        detail.getLayout().prefHeightProperty().bind(main_layout.heightProperty().subtract(menuBar.getPrefHeight()+50));

        main_layout.prefWidthProperty().bind(stage.widthProperty());
        main_layout.prefHeightProperty().bind(stage.heightProperty());

        
        //set the background color of the main layout
        main_layout.setStyle("-fx-background-color: #555555;");


        scene = new Scene(main_layout, 640, 640);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
