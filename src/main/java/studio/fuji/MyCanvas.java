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
    private Vector<Vector<MyPixel>> pixels;
    public static Color color = new Color(255,255,255);
    static int count=0;

    public MyCanvas(int width, int height){
        count=0;
        this.width = width;
        this.height = height;
        this.scale = 10;
        this.pixels = new Vector<Vector<MyPixel>>();

        this.vhBox = new VBox();
        for(int i=0; i<height; i++){
            HBox hBox = new HBox();
            //make hBox border visible

            this.pixels.add(new Vector<MyPixel>());
            for(int j=0; j<width; j++){
                MyPixel pixel = new MyPixel(new Color(255,255,255,255));
                pixel.setSize(this.scale);
                this.pixels.get(i).add(pixel);
                hBox.getChildren().add(pixel);
            }
            this.vhBox.getChildren().add(hBox);
        }
        this.setOnMousePressed(e->{
            System.out.println(e);
            e.getPickResult().getIntersectedNode().setStyle(String.format("-fx-background-insets: 0, 0;-fx-background-color: rgb(%d, %d, %d);", color.getRed(), color.getGreen(), color.getBlue()));
        });
        this.setOnMouseDragged(e->{
            int x = (int)e.getX()/(int)scale;
            int y = (int)e.getY()/(int)scale;
            getPixel(x,y).setColor(color);
        });
        this.setStyle("-fx-background-insets: 0, 0;");
        this.getChildren().add(this.vhBox);
    }
    public void update(){
        switch(count){
            case 0:
                MyCanvas.color = new Color(0, 0, 0);
                break;
            case 1:
                MyCanvas.color = new Color(255, 255, 255);
                break;
            case 2:
                MyCanvas.color = new Color(255, 0, 0);
                break;
            case 3:
                MyCanvas.color = new Color(0, 255, 0);
                break;
            case 4:
                MyCanvas.color = new Color(0, 0, 255);
                break;
            case 5:
                MyCanvas.color = new Color(255, 255, 0);
                break;
            case 6:
                MyCanvas.color = new Color(0, 255, 255);
                break;
            case 7:
                MyCanvas.color = new Color(255, 0, 255);
                count=0;
                break;
            default:
                MyCanvas.color = new Color(0, 0, 0);
                break;
        }
    }
    public void setSize(double n){
        this.scale = n;
        System.out.println(this.scale);
        for(int i = 0; i < this.pixels.size(); i++) {
            for(int j=0; j<this.pixels.get(i).size(); j++){
                getPixel(i,j).setSize(n);
            }
        }
    }

    public MyPixel getPixel(int x, int y){
        return this.pixels.get(y).get(x);
    }
    public MyPixel getPixel(int index){
        int count = 0;
        for(int x=0;x<this.height;x++){
            for(int y=0;y<this.width;y++){
                count++;
                if(count==index){
                    return getPixel(x,y);
                }
            }
        }
        return null;
    }
    public void setPixel(int index){
        getPixel(index).setColor(color);
    }
    public void setPixel(int x, int y){
        getPixel(x,y).setColor(color);
    }
    public void setPixel(int x, int y, Color color){
        getPixel(x,y).setColor(color);
    }
    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        for(int i=0; i<this.height; i++){
            for(int j=0; j<this.width; j++){
                image.setRGB(j, i, new Color(255,255,255,0).getRGB());
            }
        }
        for(int i = 0; i < this.pixels.size(); i++) {
            for(int j=0; j<this.pixels.get(i).size(); j++){
                image.setRGB(j, i, getPixel(i,j).getColor().getRGB());
            }
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
    public static void setColor(Color color){
        MyCanvas.color = color;
    }
}