package studio.fuji;

import java.io.Console;
import java.util.Vector;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MyCanvas extends javafx.scene.Parent {
    private VBox vhBox;
    private int width;
    private int height;
    private double scale;
    private Vector<MyPixel> pixels;
    private Color color;
    static int count=0;

    public MyCanvas(int width, int height){
        count=0;
        this.width = width;
        this.height = height;
        this.scale = 10;
        this.pixels = new Vector<MyPixel>();
        this.color = new Color(0, 0, 0);
        this.vhBox = new VBox();
        for(int i=0; i<height; i++){
            HBox hBox = new HBox();
            for(int j=0; j<width; j++){
                MyPixel pixel = new MyPixel(this.color);
                pixel.setSize(this.scale);
                this.pixels.add(pixel);
                hBox.getChildren().add(pixel);
            }
            this.vhBox.getChildren().add(hBox);
        }
        color = new Color(255, 255, 255);
        this.setOnMouseClicked(event->{
            //if it's on a pixel
            if(event.getX() < this.width * this.scale && event.getY() < this.height * this.scale) {
                int x = (int)(event.getX() / this.scale);
                int y = (int)(event.getY() / this.scale);
                int index = x + y * this.width;
                MyPixel pixel = this.pixels.get(index);
                pixel.setColor(this.color);
                count++;
                update();
            }
        });
        this.setOnMouseDragged(e->{
            if(e.getX() < this.width * this.scale && e.getY() < this.height * this.scale) {
                int x = (int)(e.getX() / this.scale);
                int y = (int)(e.getY() / this.scale);
                int index = x + y * this.width;
                MyPixel pixel = this.pixels.get(index);
                pixel.setColor(this.color);
                count++;
                update();
            }
        });
        this.getChildren().add(this.vhBox);
        
        
    }
    public void update(){
        switch(count){
            case 0:
                this.color = new Color(0, 0, 0);
                break;
            case 1:
                this.color = new Color(255, 255, 255);
                break;
            case 2:
                this.color = new Color(255, 0, 0);
                break;
            case 3:
                this.color = new Color(0, 255, 0);
                break;
            case 4:
                this.color = new Color(0, 0, 255);
                break;
            case 5:
                this.color = new Color(255, 255, 0);
                break;
            case 6:
                this.color = new Color(0, 255, 255);
                break;
            case 7:
                this.color = new Color(255, 0, 255);
                count=0;
                break;
            default:
                this.color = new Color(0, 0, 0);
                break;
        }
    }
    public void setSize(double n){
        this.scale = n;
        System.out.println(this.scale);
        for(int i = 0; i < this.pixels.size(); i++) {
            this.pixels.get(i).setSize(n);
        }
    }
    public MyPixel getPixel(int index){
        return this.pixels.get(index);
    }
    public void setPixel(int index){
        this.pixels.get(index).setColor(this.color);
    }
    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < this.pixels.size(); i++) {
            image.setRGB(i % this.width, i / this.width, this.pixels.get(i).getRGB());
        }
        try{
            javax.imageio.ImageIO.write(image, "png", new java.io.File("image" + count + ".png"));
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        count++;
        return image;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
}