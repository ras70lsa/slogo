package frontend_slogo_team04;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpButton extends Button{

	public HelpButton(){
		
		// TODO Auto-generated constructor stub
	}
	
	private Button createButton(){
		Button help = new Button("Help");
		help.setOnAction(e->{
			
		});
		return help;
	}
	
	private Stage helpBox (String title) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        
        Scene scene = new Scene(dialogVbox, 300, 200);
        window.setScene(scene);
        window.showAndWait();
        return window;
    }

}
