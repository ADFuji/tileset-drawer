package studio.fuji;

import java.awt.image.BufferedImage;
import java.io.Console;
import java.util.Arrays;
import java.util.Vector;

public class MyTileset {
    private Vector<MyTile> tiles;
    private String name;

    public MyTileset(String name) {
        this.name = name;
        this.tiles = new Vector<MyTile>();
    }
    public void addTile(MyTile tile) {
        this.tiles.add(tile);
    }
    public void addTile(int width, int height) {
        this.tiles.add(new MyTile(width, height));
    }
    public void removeTile(MyTile tile) {
        this.tiles.remove(tile);
    }
    public void removeTile(int index) {
        this.tiles.remove(index);
    }
    public MyTile getTile(int index) {
        return this.tiles.get(index);
    }
    public int getTileCount() {
        return this.tiles.size();
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(tiles.get(0).getWidth()*4, tiles.get(0).getWidth()*2, BufferedImage.TYPE_INT_RGB);
        int lastX = 0;
        int lastY = 0;
        for(int i = 0; i < this.tiles.size(); i++) {
            BufferedImage tileImage = this.tiles.get(i).getImage();
            if(tileImage.getWidth()+lastX>image.getWidth()) {
                lastX = 0;
                lastY += tileImage.getHeight();
            }
            System.out.println("Tile " + i + " is " + tileImage.getWidth() + "x" + tileImage.getHeight()+ " at " + lastX + ", " + lastY);
            for(int x = 0; x < tileImage.getWidth(); x++) {
                for(int y = 0; y < tileImage.getHeight(); y++) {
                    image.setRGB(x + lastX, y + lastY, tileImage.getRGB(x, y));
                }
            }
            lastX += tileImage.getWidth();
        }
        return image;
    }
    public int[] calcSize() {
        int[] size = new int[2];
        size[0] = this.tiles.get(0).getWidth()*getTileCount();
        size[1] = this.tiles.get(0).getHeight();
        return size;
    }
    public void saveAs(String path){
        BufferedImage image = this.getImage();
        try {
            javax.imageio.ImageIO.write(image, "png", new java.io.File(path));
        } catch (java.io.IOException e) {
            System.out.println("Error saving image");
        }
    }
}
