package studio.fuji;
import java.awt.Color;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;


public class ColorPicker extends javafx.scene.Parent {
    private javafx.scene.control.ColorPicker colorPicker;
    private Label label;
    private HBox hBox;
    private Color color;

    public ColorPicker(String name) {
        this.colorPicker = new javafx.scene.control.ColorPicker();
        this.colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        MyCanvas.color = Color.BLACK;
        this.hBox = new javafx.scene.layout.HBox();
        this.hBox.getChildren().add(this.colorPicker);
        this.color = new Color((int)this.colorPicker.getValue().getRed(),(int)this.colorPicker.getValue().getRed(),(int)this.colorPicker.getValue().getRed());
        this.getChildren().add(this.hBox);
        this.colorPicker.setOnAction(e->{
            System.out.print(this.colorPicker.getValue().getBlue());
            System.out.println("");
            //if this.colorPicker.getvalue().getRed()==1.0 then its 255
            double red = this.colorPicker.getValue().getRed()*255;
            double green = this.colorPicker.getValue().getGreen()*255;
            double blue = this.colorPicker.getValue().getBlue()*255;
            double a = this.colorPicker.getValue().getOpacity()*255;

            System.out.println(red);
            System.out.println(green);
            System.out.println(blue);
            System.out.println(a);

            this.color = new Color((int)red,(int)green,(int)blue, (int)a);
            MyCanvas.color = this.color;
        });

    }

    public Color getColor() {
        return this.color;
    }
}
