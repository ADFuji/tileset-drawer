import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
public class MyCanvas extends javafx.scene.Parent{
    private StackPane stack_canvas;
    private Canvas grid;
    private Canvas canvas;
    private Canvas case_selected; 
    private int scale;
    private int width;
    private int height;
    public MyCanvas(int w, int h, int scale){
        super();
        this.scale = scale;
        this.width = w;
        this.height = h;
        stack_canvas = new StackPane();
        grid = new Canvas(w*scale, h*scale);
        canvas = new Canvas(w*scale, h*scale);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        gc.setLineWidth(1*scale);
        //allow for pixel perfect drawing
        gc.setLineCap(javafx.scene.shape.StrokeLineCap.SQUARE);
        gc.setLineJoin(javafx.scene.shape.StrokeLineJoin.MITER);
        case_selected = new Canvas(w*scale, h*scale);
        stack_canvas.getChildren().addAll(grid, canvas, case_selected);

        GraphicsContext gridctx = grid.getGraphicsContext2D();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                gridctx.setStroke(javafx.scene.paint.Color.WHITE);
                gridctx.setLineWidth(1);
                gridctx.strokeRect(i*scale, j*scale, scale, scale);
            }
        }
        case_selected.setOnMouseMoved(e->{
            case_selected_draw_grid((int)e.getX(), (int)e.getY());
        });
        case_selected.setOnMousePressed(e->{
            draw((int) e.getX(), (int) e.getY());
        });
        case_selected.setOnMouseDragged(e->{
            case_selected_draw_grid((int)e.getX(), (int)e.getY());
            draw((int) e.getX(), (int) e.getY());
        });

        
        super.getChildren().add(stack_canvas);
    }
    public void setScale(int n){
        this.scale = n;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1*scale);
        GraphicsContext gridctx = grid.getGraphicsContext2D();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                gridctx.setStroke(javafx.scene.paint.Color.WHITE);
                gridctx.setLineWidth(1);
                gridctx.strokeRect(i*scale, j*scale, scale, scale);
            }
        }
        grid.setScaleX(scale);
        grid.setScaleY(scale);
        canvas.setScaleX(scale);
        canvas.setScaleY(scale);
        case_selected.setScaleX(scale);
        case_selected.setScaleY(scale);

    }
    public int getScale(){
        return this.scale;
    }
    public Canvas getGrid(){
        return this.grid;
    }
    public Canvas getCanvas(){
        return this.canvas;
    }
    public Canvas getCaseSelected(){
        return this.case_selected;
    }
    public StackPane getStackPane(){
        return this.stack_canvas;
    }
    private void case_selected_draw_grid(int x, int y){
        GraphicsContext ctx = case_selected.getGraphicsContext2D();
        ctx.clearRect(0, 0, 32*scale, 32*scale);
        ctx.setStroke(javafx.scene.paint.Color.RED);
        ctx.setLineWidth(2);
        ctx.strokeRect((int)(x/scale)*scale, (int)(y/scale)*scale, scale, scale);
    }
    private void draw(double x, double y){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(x < 32*scale && y < 32*scale){
            int _x = (int)(x/scale);
            int _y = (int)(y/scale);
            gc.fillRect((_x*scale), (_y*scale), scale, scale);
        }
    }
    public void setColor(javafx.scene.paint.Color c){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(c);
    }
}
