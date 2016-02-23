import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LineTest extends Application{
	
	private Stage myStage;
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        
        Line test = new Line();
        test.setStartX(100);
        test.setStartY(100);
        test.setEndX(200);
        test.setEndY(200);
        
        Rectangle rec = new Rectangle();
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        final KeyValue kv = new KeyValue(test.endXProperty(), 10, Interpolator.DISCRETE);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
        root.getChildren().add(test);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    
    public static void main (String[] args) {
        launch(args);
    }
}