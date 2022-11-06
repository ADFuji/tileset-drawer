package studio.fuji;

import java.io.Console;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MyCanvas extends javafx.scene.Parent {
    private GridPane gridPane;
    private int width;
    private int height;
    private int scale;
    private Vector<StackPane> pixels;
    private boolean canDraw;
    private String color;

    public MyCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.gridPane = new GridPane();
        this.pixels = new Vector<StackPane>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                StackPane pixel = new StackPane();
                pixel.setPrefSize(1, 1);
                pixel.setStyle("-fx-background-color: #000000");
                this.pixels.add(pixel);
            }
        }
        for (int i = 0; i < this.pixels.size(); i++) {
            this.gridPane.add(this.pixels.get(i), i % width, i / width);
        }
        color = "#000000";

        gridPane.setGridLinesVisible(true);
        gridPane.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            drawPixel(x, y, color);
        });
        gridPane.setOnMouseDragged(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            drawPixel(x, y, color);
        });
        gridPane.setOnMouseEntered(event -> {
            this.canDraw = true;
        });
        gridPane.setOnMouseExited(event -> {
            this.canDraw = false;
        });
        this.getChildren().add(gridPane);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Vector<StackPane> getPixels() {
        return this.pixels;
    }

    public StackPane getPixel(int index) {
        return (StackPane) this.pixels.get(index);
    }

    public void changeScale(int scale) {
        this.scale = scale;
        for (int i = 0; i < this.pixels.size(); i++) {
            this.pixels.get(i).setPrefSize(scale, scale);
        }
    }

    private void drawPixel(int x, int y, String color) {
        int index = (y / this.scale) * this.width + (x / this.scale);
        getPixel(index).setStyle("-fx-background-color:" + color);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getScale() {
        return this.scale;
    }

    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int index = j * this.width + i;
                String style = pixels.get(index).getStyle();
                System.out.println(style);
                switch (style) {
                    case "-fx-background-color: #000000":
                        image.setRGB(i, j, 0x000000);
                        break;
                    case "-fx-background-color: #FFFFFF":
                        image.setRGB(i, j, 0xFFFFFF);
                        break;
                    case "-fx-background-color: #FF0000":
                        image.setRGB(i, j, 0xFF0000);
                        break;
                    case "-fx-background-color: #00FF00":
                        image.setRGB(i, j, 0x00FF00);
                        break;
                    case "-fx-background-color: #0000FF":
                        image.setRGB(i, j, 0x0000FF);
                        break;
                    case "-fx-background-color: #FFFF00":
                        image.setRGB(i, j, 0xFFFF00);
                        break;
                    case "-fx-background-color: #00FFFF":
                        image.setRGB(i, j, 0x00FFFF);
                        break;
                    case "-fx-background-color: #FF00FF":
                        image.setRGB(i, j, 0xFF00FF);
                        break;
                    default:
                        image.setRGB(i, j, 0x000000);
                        break;
                }
            }
        }
        return image;
    }
}
