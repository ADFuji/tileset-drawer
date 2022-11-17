package studio.fuji;

import java.awt.image.BufferedImage;
import java.io.Console;
import java.util.Arrays;
import java.util.Vector;
//import color from java.awt.Color;
import java.awt.Color;


public class MyTileset {
    private Vector<MyTile> tiles;
    private String name;
    private int width;
    private int height;
    public String path;

    public MyTileset(){
        this.tiles = new Vector<MyTile>();
        this.name = "Untitled";
        this.width = 0;
        this.height = 0;
        this.path = "";
    }
    public MyTileset(String name) {
        this.name = name;
        this.width = 32;
        this.height = 32;
        this.tiles = new Vector<MyTile>();
    }
    public MyTileset(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = new Vector<MyTile>();
    }
    public void newTile(){
        System.out.println("newTile");
        System.out.println(this.width);
        System.out.println(this.height);
        this.tiles.add(new MyTile(this.width, this.height));
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

    public BufferedImage getImage() {
        //create a square image that is the size of the tileset
        int n = (this.tiles.size() %2 != 0)?this.tiles.size()+1:this.tiles.size();
        //si le tileset a 20 tiles, il faut faire une image de 4x5
        int _x = (int)Math.sqrt(n);
        int _y = (int)Math.sqrt(n);
        if(_x*_y < n) {
            _x++;
        }
        if(_x*_y < n) {
            _y++;
        }
        BufferedImage image = new BufferedImage(_x*this.width, _y*this.height, BufferedImage.TYPE_INT_ARGB);
        int lastX = 0;
        int lastY = 0;
        for (int i = 0; i < this.tiles.size(); i++) {
            BufferedImage tileImage = this.tiles.get(i).getImage();
            if (tileImage.getWidth() + lastX > image.getWidth()) {
                lastX = 0;
                lastY += tileImage.getHeight();
            }
            System.out.println("Tile " + i + " is " + tileImage.getWidth() + "x" + tileImage.getHeight() + " at "
                    + lastX + ", " + lastY);
            for (int x = 0; x < tileImage.getWidth(); x++) {
                for (int y = 0; y < tileImage.getHeight(); y++) {
                    image.setRGB(x + lastX, y + lastY, tileImage.getRGB(x, y));
                }
            }
            lastX += tileImage.getWidth();
        }
        return image;
    }

    public int[] calcSize() {
        int[] size = new int[2];
        size[0] = this.tiles.get(0).getWidth() * getTileCount();
        size[1] = this.tiles.get(0).getHeight();
        return size;
    }
    public void save() {
        saveAsTileset(this.path);
    }
    public void saveAsPng(String path) {
        BufferedImage image = this.getImage();
        try {
            javax.imageio.ImageIO.write(image, "png", new java.io.File(path));
        } catch (java.io.IOException e) {
            System.out.println("Error saving image");
        }
    }
    public void saveAll(String path) {
        for (int i = 0; i < this.tiles.size(); i++) {
            this.tiles.get(i).saveAsPng(path + "/" + this.name + "_" + i + ".png");
        }
    }
    public void saveAsTileset(String path) {
        System.out.println("saveAsTileset");
        Vector<MyTile> tiles = this.tiles;
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        for(int i=0; i<this.tiles.size(); i++) {
            MyTile tile = this.tiles.get(i);
            Vector<String> row = new Vector<String>();
            row.add(tile.getName().getText());
            row.add(Integer.toString(tile.getWidth()));
            row.add(Integer.toString(tile.getHeight()));
            for(int x=0; x<tile.getWidth(); x++) {
                for(int y=0; y<tile.getHeight(); y++) {
                    row.add(Integer.toString(tile.getCanvas().getPixel(x, y).getRGB()));
                }
            }
            data.add(row);
        }
        System.out.println(data);
        try{
            //write data into file
            java.io.FileWriter writer = new java.io.FileWriter(path);
            for(int i=0; i<data.size(); i++) {
                Vector<String> row = data.get(i);
                for(int j=0; j<row.size(); j++) {
                    writer.write(row.get(j));
                    System.out.println(row.get(j));
                    if(j<row.size()-1) {
                        writer.write(",");
                    }
                }
                writer.write("\r\n");
            }
            writer.close();
        }
        catch(java.io.IOException e) {
            System.out.println("Error saving tileset");
        }
    }
    public Vector<MyTile> getTiles() {
        return this.tiles;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public static MyTileset open(String path){
        //try to open the file
        try{
            //read the file
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(path));
            MyTileset tileset = new MyTileset();
            tileset.setPath(path);
            //read the file line by line
            String line = reader.readLine();
            while(line != null) {
                //split the line into an array
                String[] data = line.split(",");
                tileset.setHeight(Integer.parseInt(data[2]));
                tileset.setWidth(Integer.parseInt(data[1]));
                //create a new tile
                MyTile tile = new MyTile(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                //set the name of the tile
                tile.getName().setText(data[0]);
                //set the pixels of the tile
                int index = 3;
                for(int x=0; x<tile.getWidth(); x++) {
                    for(int y=0; y<tile.getHeight(); y++) {
                        tile.getCanvas().setPixel(x, y, new Color(Integer.parseInt(data[index])));
                        index++;
                    }
                }
                //add the tile to the tileset
                tileset.addTile(tile);
                //read the next line
                line = reader.readLine();
            }
            //close the file
            reader.close();
            //return the tileset
            return tileset;
        }
        catch(java.io.IOException e) {
            System.out.println("Error opening tileset");
            return null;
        }
    }
}
