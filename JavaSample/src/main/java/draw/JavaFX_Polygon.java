package draw;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class JavaFX_Polygon extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root, 260, 80);
        stage.setScene(scene);

        Group g = new Group();

        Polygon polygon = new Polygon(0.0, 0.0, 80.0, 40.0, 30.0, 60.0, 40.0, 80.0);
        //Polygon polygon = new Polygon(1.94979879, 20.87667934, 1.9504697, 20.87843539, 1.95241047, 20.88000126, 1.95541082, 20.88120722);

        g.getChildren().add(polygon);

        scene.setRoot(g);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}