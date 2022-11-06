package studio.fuji;

import java.awt.image.BufferedImage;
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

    public void rmTile(int id) {
        this.tiles.remove(id);
    }

    private BufferedImage getTileset(int col, int row) {
        BufferedImage tileset = new BufferedImage(col * this.tiles.get(0).getCanvas().getWidth(),
                row * this.tiles.get(0).getCanvas().getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < this.tiles.size(); i++) {
            tileset.createGraphics().drawImage(this.tiles.get(i).getImage(),
                    (i % col) * this.tiles.get(0).getCanvas().getWidth(),
                    (i / col) * this.tiles.get(0).getCanvas().getHeight(), null);
        }
        return tileset;
    }

    public void saveAs(int col, int row, String path) {
        try {
            javax.imageio.ImageIO.write(this.getTileset(col, row), "png", new java.io.File(path));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Vector<MyTile> getTiles() {
        return this.tiles;
    }
}
