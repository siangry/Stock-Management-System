import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainSceneController implements Initializable {
	@FXML
	private Text dateTime;
	@FXML
	BorderPane borderPane1;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getDateTime();
		
		//If the user clicked on the screen (borderPane1)
		borderPane1.setOnMouseClicked(e -> {
			hideMainScene(); //Hide the current window
			promptLoginWindow(); //Prompt the login window
		});
	}

	private void getDateTime() {
		
		//To obtain the current date and time from system clock
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		//To customise the display format of date and time
		DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy   HH:mm:ss");
		String formattedDateTime = currentDateTime.format(displayFormat);
		
		dateTime.setText(formattedDateTime);
	}
	
	protected void promptLoginWindow() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent loginRoot = null;
		Stage loginStage = new Stage();
		
		//To handle the IOException
		try {
			loginRoot = loader.load();
		}
		catch (IOException e) {
			System.out.println(e);
		}
			
		loginStage.setTitle("Login");
		loginStage.setScene(new Scene(loginRoot));
		loginStage.show();
	}
	
	private void hideMainScene() {
		//(Stage) is used to cast Window to Stage.
		Stage stage = (Stage) borderPane1.getScene().getWindow();
		stage.hide(); //Hide the main scene
	}
}
