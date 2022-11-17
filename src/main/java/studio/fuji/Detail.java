package studio.fuji;
import javafx.scene.*;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;

import java.util.Stack;

import javafx.beans.Observable;

public class Detail extends javafx.scene.Parent{
    public BorderPane layout;
    public ColorPicker colorPicker;
    public Slider thicknSlider;
    public BorderPane display;
    public ScrollPane center;
    public Slider zoomSlider;
    public HBox control;
    public HBox bottom;


    private MyCanvas currentCanvas;
    public Detail(){
        super();

        layout = new BorderPane();
        colorPicker = new ColorPicker("Color");
        thicknSlider = new Slider(0, 100, 1);
        thicknSlider.setShowTickLabels(true);
        thicknSlider.setShowTickMarks(true);

        control = new HBox();
        control.getChildren().addAll(colorPicker, thicknSlider);
        control.setSpacing(10);
        control.setMinHeight(50);
        control.setMaxHeight(50);
        control.setMinWidth(500);
        control.setMaxWidth(Double.MAX_VALUE);
        


        bottom = new HBox();
        zoomSlider = new Slider(0, 100, 1);
        zoomSlider.setShowTickLabels(true);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.valueProperty().addListener((Observable o) -> {
            currentCanvas.setSize(zoomSlider.getValue());
        });
        bottom.getChildren().add(zoomSlider);
        bottom.setSpacing(10);

        display = new BorderPane();
        center = new ScrollPane();
        display.setPrefWidth(320);
        display.setPrefHeight(320);
        display.setCenter(center);
        display.setTop(new Pane());
        display.setLeft(new Pane());
        display.setRight(new Pane());
        display.setBottom(new Pane());
        center.setStyle("-fx-background-color: #2C2C2C;-fx-background-insets: 0, 0;");
        center.setFitToWidth(true);
        center.setFitToHeight(true);
        center.setBorder(null);
        
        layout.setTop(control);
        layout.setCenter(display);
        layout.setBottom(bottom);

        layout.setStyle("-fx-background-color: #2B2B2B;");
        this.getChildren().add(layout);
    }
    public BorderPane getLayout(){
        return layout;
    }
    public void setDisplay(MyTile tile){
        currentCanvas = tile.getCanvas();
        //set margin for center
        center.setContent(currentCanvas);
        center.setFitToWidth(true);
        center.setFitToHeight(true);
        //set margin for display
        display.setTop(new Pane());
        display.setLeft(new Pane());
        display.setRight(new Pane());
        display.setBottom(new Pane());

        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color: #2C2C2C;");
        //remove the border
        stack.setBorder(null);
        stack.getChildren().add(currentCanvas);
        center.setContent(stack);
    }
    public void addToControl(Node node){
        control.getChildren().add(node);
    }
    public void addToBottom(Node node){
        bottom.getChildren().add(node);
    }
    public HBox getTop(){
        return control;
    }
    public BorderPane getCenter(){
        return display;
    }
    public HBox getBottom(){
        return bottom;
    }

}
