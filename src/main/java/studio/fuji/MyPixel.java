package studio.fuji;

import javafx.scene.layout.StackPane;
import java.awt.Color;

public class MyPixel extends javafx.scene.Parent{
    private static boolean isDraw = false;

    private javafx.scene.layout.StackPane pixel;
    private Color color;
    public MyPixel(Color color) {
        this.color = color;
        this.pixel = new StackPane();
        this.pixel.setStyle(String.format("-fx-background-insets: 0, 0;-fx-background-color: rgba(%d, %d, %d, %d);", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
        this.pixel.setPrefSize(1, 1);
        
        this.getChildren().add(this.pixel);

    }
    public void setColor(java.awt.Color color) {
        this.color = color;
        this.pixel.setStyle(String.format("-fx-background-insets: 0, 0;;-fx-background-color: rgba(%d, %d, %d, %d);", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }
    public java.awt.Color getColor() {
        return this.color;
    }
    public javafx.scene.layout.StackPane getPixel() {
        return this.pixel;
    }
    public void setWidth(int width) {
        this.pixel.setPrefWidth(width);
    }
    public void setHeight(int height) {
        this.pixel.setPrefHeight(height);
    }
    public void setSize(int width, int height) {
        this.pixel.setPrefWidth(width);
        this.pixel.setPrefHeight(height);
    }
    public void setSize(double size) {
        this.pixel.setPrefWidth(size);
        this.pixel.setPrefHeight(size);
    }
    public int getWidth() {
        return (int)this.pixel.getPrefWidth();
    }
    public int getHeight() {
        return (int)this.pixel.getPrefHeight();
    }
    public int getRGB() {
        return this.color.getRGB();
    }
}
