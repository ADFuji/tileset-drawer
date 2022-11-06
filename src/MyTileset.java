public class MyTileset {
    private java.util.Vector<MyTile> tiles;
    private String name;
    public MyTileset(String name){
        this.name = name;
        this.tiles = new java.util.Vector<MyTile>();
    }
    public void addTile(MyTile tile){
        tiles.add(tile);
    }
    public void removeTile(MyTile tile){
        tiles.remove(tile);
    }
    public void removeTile(int id){
        tiles.remove(id);
    }
    public MyTile getTile(int id){
        return tiles.get(id);
    }
    public int getTileCount(){
        return tiles.size();
    }
    //save the tileset in a unique png file
    public void save(String path){
        int x = 0;
        int y = 0;
        int tile_width = tiles.elementAt(0).getWidth();
        int tile_height = tiles.elementAt(0).getHeight();
        //create a new square image
        int size = (int)Math.ceil(Math.sqrt(tiles.size()));
        javafx.scene.image.WritableImage img = new javafx.scene.image.WritableImage(size*tile_width, size*tile_height);
        javafx.scene.image.PixelWriter pw = img.getPixelWriter();
        for(int i = 0; i<tiles.size(); i++){
            javafx.scene.image.Image tile = tiles.elementAt(i).getImage();
            for(int j = 0; j<tile_width; j++){
                for(int k = 0; k<tile_height; k++){
                    pw.setColor(j+x*tile_width, k+y*tile_height, tile.getPixelReader().getColor(j, k));
                }
            }
            x++;
            if(x>=size){
                x = 0;
                y++;
            }
        }
        javafx.embed.swing.SwingFXUtils.fromFXImage(img, null);
        try{
            javax.imageio.ImageIO.write(javafx.embed.swing.SwingFXUtils.fromFXImage(img, null), "png", new java.io.File(path));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
