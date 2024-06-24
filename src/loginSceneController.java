import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginSceneController implements Initializable{
	
	@FXML
	private TextField userName;
	@FXML
	private Text displayNameResult;
	@FXML
	private Button backButton;
	@FXML
	private Button confirmButton;
	@FXML
	private Button clearButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//If the backButton is clicked, it will back to the main scene
		backButton.setOnMouseClicked(e -> {
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.close();
			promptMainScene();
		});
		
		clearButton.setOnMouseClicked(e -> {
			userName.clear();
		});
		
		confirmButton.setOnMouseClicked(e -> {
			boolean invalid = readUserName();
			
			//If the input name for a user object is valid
			if (!invalid) {
				Stage stage = (Stage) confirmButton.getScene().getWindow();
				stage.hide();
				promptAddProductScene();
			}
			else {
				userName.clear();
			}
		});
		
		userName.setOnMouseClicked(e -> {
			displayNameResult.setText("");
		});
	}
	
	//Load the JavaFX.fxml to back to the main scene
	protected void promptMainScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("JavaFX.fxml"));
		Parent root = null;
		Stage primaryStage = new Stage();
		
		//To handle the IOException
		try {
			root = loader.load();
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	//To check if the userName/inputName is valid
	private boolean readUserName() {
		boolean invalid = StockManagement.user.setName(userName.getText());
		
		if(invalid) {
			displayNameResult.setText("Opps...Invalid input. Please enter again.");
			displayNameResult.setFill(Color.RED);
		}
		else {
			displayNameResult.setText("Ta-da! Your login information has been successfully recorded.");
			displayNameResult.setFill(Color.GREEN);
		}
		return invalid;
	}
	
	//Load the Option.fxml to go to the Stock Management System Function Page
	protected void promptAddProductScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Option.fxml"));
		Parent root = null;
		Stage optionStage = new Stage();
		
		//To handle the IOException
		try {
			root = loader.load();
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		optionStage.setTitle("Setting - Stock Management System"); //Remember to rename
		optionStage.setScene(new Scene(root));
		optionStage.show();
	}
}
