package studio.fuji;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class NewTileset extends Dialog{
    private TextField nameField;
    private Spinner<Integer> widthSpinner;
    private Spinner<Integer> heightSpinner;
    public NewTileset(){
        super();
        //ce dialog doit permettre de créer un nouveau tileset, il doit demander le nom du tileset, la taille des tiles, et le nombre de tiles
        VBox layout = new VBox();
        HBox name = new HBox();
        {
            Label label = new Label("Name:");
            nameField = new TextField();
            name.getChildren().addAll(label, nameField);
        }
        VBox size = new VBox();
        {
            HBox width = new HBox();
            {
                Label label = new Label("Width:");
                widthSpinner = new Spinner<Integer>(1, 100, 16);
                width.getChildren().addAll(label, widthSpinner);
            }
            HBox height = new HBox();
            {
                Label label = new Label("Height:");
                heightSpinner = new Spinner<Integer>(1, 100, 16);
                height.getChildren().addAll(label, heightSpinner);
            }
            size.getChildren().addAll(width, height);
        }
        layout.getChildren().addAll(name, size);
        this.getDialogPane().setContent(layout);
        //add a button to create the tileset
        ButtonType cbutton = new ButtonType("Create");
        this.getDialogPane().getButtonTypes().add(cbutton);
        ButtonType cbutton2 = new ButtonType("Cancel");
        this.getDialogPane().getButtonTypes().add(cbutton2);
    }
    public Object[] getValues(){
        return new Object[]{nameField.getText(), widthSpinner.getValue(), heightSpinner.getValue()};
    }
}
