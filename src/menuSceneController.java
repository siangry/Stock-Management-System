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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class menuSceneController implements Initializable {
	
	@FXML
	Text errorMessage;
	@FXML
	TextField menuOption;
	@FXML
	Button proceedButton;
	protected static int option;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuOption.setOnMouseClicked (e -> {
			errorMessage.setText(null);
		});
		
		proceedButton.setOnMouseClicked(e ->{
			//To retrieve the input from text field
			InputData.setTextValue(menuOption.getText());
			
			//To invoke the displayMenu method in the StockManagement.java
			option = StockManagement.displayMenu(StockManagement.input);
			
			if(option == -1) {
				errorMessage.setText(InputData.getTextValue());
				errorMessage.setFill(InputData.getColour());
				menuOption.clear();
			}
			else {
				menuOption.setEditable(false);
				hideStage();
				if (option != 0) {
					promptExecuteScene();
				}
				else {
					promptExitScene();
				}
			}
		});
	}
	
	protected void promptExecuteScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ExecuteMethod.fxml"));
		Parent root = null;
		Stage executeStage = new Stage();
		
		//To handle the IOException
		try {
			root = loader.load();
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		executeStage.setTitle("Stock Management System");
		executeStage.setScene(new Scene(root));
		executeStage.show();
	}
	
	public void hideStage() {
		Stage stage = (Stage) proceedButton.getScene().getWindow();
		stage.hide();
	}
	
	//Direct to the exit page
		protected void promptExitScene() {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Exit.fxml"));
			Parent root = null;
			Stage exitStage = new Stage();
			
			//To handle the IOException
			try {
				root = loader.load();
			}
			catch (IOException e) {
				System.out.println(e);
			}
			
			exitStage.setTitle("Exit");
			exitStage.setScene(new Scene(root));
			exitStage.show();
		}
}
