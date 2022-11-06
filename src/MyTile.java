public class MyTile extends javafx.scene.Parent{
    static int count = 0;
    private MyCanvas canvas;
    private String name;
    private int id;
    private int width;
    private int height;
    private int scale;

    public MyTile(String name, int w, int h, int scale){
        super();
        this.scale = scale;
        this.width = w;
        this.height = h;
        this.id = count;
        count++;
        this.name = name;
        canvas = new MyCanvas(w, h, scale);
        super.getChildren().add(canvas);
    }
    public void setScale(int n){
        this.scale = n;
        canvas.setScale(n);
    }
    //get the png image of the tile
    public javafx.scene.image.Image getImage(){
        return canvas.getImage();
    }

    //save the tile in a file
    public void save(String path){
        javafx.scene.image.Image img = getImage();
        javafx.embed.swing.SwingFXUtils.fromFXImage(img, null);
        try{
            javax.imageio.ImageIO.write(javafx.embed.swing.SwingFXUtils.fromFXImage(img, null), "png", new java.io.File(path));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int getScale(){
        return scale;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    
}
