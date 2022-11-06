import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MyCanvas extends javafx.scene.Parent {
    private GridPane gridPane;

    public MyCanvas(int width, int height) {
        gridPane = new GridPane();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                StackPane stackPane = new StackPane();
                stackPane.setStyle("-fx-background-color: #000000;");
                stackPane.setPrefSize(10, 10);
                gridPane.add(stackPane, i, j);
                stackPane.setOnMouseEntered(e -> {
                    stackPane.setStyle("-fx-background-color: #ffffff;");
                });
            }
        }
        this.getChildren().add(gridPane);

    }
}
