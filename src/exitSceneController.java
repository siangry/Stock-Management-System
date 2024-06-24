import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class exitSceneController implements Initializable {
	@FXML
	Text goodBye;
	@FXML
	BorderPane borderpane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String goodByeMessage = StockManagement.user.getUserID() + " - " + StockManagement.user.getName();
		goodBye.setText(goodByeMessage);
		
		//To close the application when users clicked on the borderpane
		borderpane.setOnMouseClicked(e -> {
			Platform.exit();
		});
	}
}
