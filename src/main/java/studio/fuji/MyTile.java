package studio.fuji;

import java.awt.image.BufferedImage;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.util.Vector;

public class MyTile extends javafx.scene.Parent {
    private MyCanvas canvas;
    private Label name;
    static int tileCount = 0;
    private int tid;

    public MyTile(int width, int height) {
        this.canvas = new MyCanvas(width, height);
        this.name = new Label(String.format("Tile %d", tileCount));
        this.tid = tileCount;
        tileCount++;
        this.getChildren().add(this.name);
    }

    public Label getName() {
        return this.name;
    }

    public MyCanvas getCanvas() {
        return this.canvas;
    }

    public BufferedImage getImage() {
        return this.canvas.getImage();
    }
    public int getTid() {
        return this.tid;
    }
    public int getWidth() {
        return this.canvas.getWidth();
    }
    public int getHeight() {
        return this.canvas.getHeight();
    }

}
