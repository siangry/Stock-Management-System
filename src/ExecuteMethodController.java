import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExecuteMethodController implements Initializable {
	
	private int executeOpt = menuSceneController.option;
	protected static Product chosenProduct;
	@FXML
	Text headerText;
	@FXML
	TextArea displayText;
	@FXML
	Button nextButton;
	@FXML
	Text quesText;
	@FXML
	TextField inputTf;
	@FXML
	Button confirmButton;
	@FXML
	Text errorMessage;
	@FXML
	HBox hbox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (executeOpt == 1) {
			headerText.setText("View Products");
			hbox.setVisible(false);
		}
		else if (executeOpt == 2) {
			headerText.setText("Add Stock");
			
		}
		else if (executeOpt == 3) {
			headerText.setText("Deduct Stock");
		}
		else if (executeOpt == 4) {
			headerText.setText("Discontinue Product");
		}
		
		inputTf.setEditable(true);
		startExecution();
		
		nextButton.setOnMouseClicked(e -> {
			hideStage();
			promptMenuScene();
		});
		
		inputTf.setOnMouseClicked(e -> {
			errorMessage.setText(null);
		});
	}
	
	public void startExecution() {
		
		if (executeOpt != 1) {
			//To get the product list
			StockManagement.selectProductForUpdate(StockManagement.productList, StockManagement.input);
			displayText.setText(StockManagement.list.toString());
			quesText.setText(StockManagement.ques);
			
			confirmButton.setOnMouseClicked(e -> {
				InputData.setTextValue(inputTf.getText());
				chosenProduct = StockManagement.selectProductForUpdate(StockManagement.productList, 
						StockManagement.input);
				
				//To check if it is a valid input
				if (InputData.getColour() == Color.RED) {
					errorMessage.setText(InputData.getTextValue());
					errorMessage.setFill(InputData.getColour());
					inputTf.clear();
				}
				else {
					quesText.setText(null);
					displayText.setText(null);
					
					//To add stock
					if (executeOpt == 2) {
						option2and3();
					}
					
					//To deduct stock
					else if (executeOpt == 3) {
						option2and3();
					}
					
					//To discontinue product
					else if (executeOpt == 4) {
						displayText.setText("Processing ... ...");
						StockManagement.executeMethod(executeOpt, StockManagement.productList, 
								StockManagement.input);
						errorMessage.setText(InputData.getTextValue());
						errorMessage.setFill(InputData.getColour());
						inputTf.setEditable(false); //To prevent users from re-editing the text field
						confirmButton.setDisable(true);
					}
				}
			});
			
		}
		//To view products
		if (executeOpt == 1) {
			StockManagement.executeMethod(executeOpt, StockManagement.productList, StockManagement.input);
			displayText.setText(StockManagement.list.toString());
		}

	}
	
	private void option2and3() {
		inputTf.clear();
		InputData.setTextValue("0"); //To clear the previous record
		StockManagement.executeMethod(executeOpt, StockManagement.productList, 
				StockManagement.input);
		displayText.setText("Processing ... ...");
		quesText.setText(StockManagement.ques);
		
		//When the confirmButton is clicked, the input value is read from the textfield(inputTf) and
		//pass to the executeMethod in StockManagement.java
		confirmButton.setOnMouseClicked(f -> {
			InputData.setTextValue(inputTf.getText());
			StockManagement.executeMethod(executeOpt, StockManagement.productList, 
					StockManagement.input);
			
			if (InputData.getColour() == Color.RED) {
				errorMessage.setText(InputData.getTextValue());
				errorMessage.setFill(InputData.getColour());
				inputTf.clear();
			}
			else {
				errorMessage.setText(InputData.getTextValue());
				errorMessage.setFill(InputData.getColour());
				inputTf.setEditable(false);
				confirmButton.setDisable(true);
			}
		});
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
	
	public void hideStage() {
		Stage stage = (Stage) headerText.getScene().getWindow();
		stage.hide();
	}
	
	//To prompt the menuScene
	private void promptMenuScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		Parent root = null;
		Stage menuStage = new Stage();
		
		//To handle the IOException
		try {
			root = loader.load();
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		menuStage.setTitle("Menu List");
		menuStage.setScene(new Scene(root));
		menuStage.show();
	}
}
