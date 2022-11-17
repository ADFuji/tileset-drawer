package studio.fuji;
import javafx.scene.control.Spinner;
public class MyDialog extends javafx.scene.control.Dialog {

    public MyDialog(String title, String message) {
        this.setTitle(title);
        this.setHeaderText(message);
        Spinner<Integer> spinner_w = new Spinner<Integer>();
        spinner_w.setValueFactory(new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        Spinner<Integer> spinner_h = new Spinner<Integer>();
        spinner_h.setValueFactory(new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.add(new javafx.scene.control.Label("Width:"), 1, 1);
        grid.add(spinner_w, 2, 1);
        grid.add(new javafx.scene.control.Label("Height:"), 1, 2);
        grid.add(spinner_h, 2, 2);
        this.getDialogPane().setContent(grid);
        this.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.OK);
        //add two spinners for width and height
        //add a button to create the tile
        //add a button to cancel


        this.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.OK);
    }
}