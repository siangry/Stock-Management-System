import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class optionSceneController implements Initializable {
	
	@FXML
	private Text inputQues;
	@FXML
	private ComboBox<String> option;
	@FXML
	private Text popOutText;
	@FXML
	private VBox vbox;
	@FXML
	private TextField popOutInput;
	@FXML
	private Button confirmButton;
	@FXML
	private Button cancelButton;
	@FXML
	protected Text errorMessage;
	protected static int numOfElement = -1;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		vbox.setVisible(false);
		
		//To set the combo box
		String[] itemArray = {"Yes", "No"};
		ObservableList<String> items = FXCollections.observableArrayList(itemArray);
		option.getItems().addAll(items);
		
		inputQues.setText("Would you like to add any products?");
		
		cancelButton.setOnMouseClicked(e -> {
			popOutInput.clear();
		});
		
		popOutInput.setOnMouseClicked(e -> {
			errorMessage.setText("");
		});
		
	}
	
	public void checkedSelectedItem() {

		//To check what is the option chosen by the user
		String Option = option.getSelectionModel().getSelectedItem();
		
		if(Option != null) {
			
			//To avoid the user from reselecting the option
			option.setDisable(true); 
			vbox.setVisible(true);
			
			if(Option == "Yes") {
				popOutText.setText("How many products do you want to add?");
				buttonSelection(Option);
			}
			else if (Option == "No") {
				popOutText.setText("Please enter a zero value to exit the program:");
				buttonSelection(Option);
				
			}
		}
	}
	
	public void buttonSelection(String Option) {
		confirmButton.setOnMouseClicked(e -> {
			if (Option == "Yes") {
				InputData.setTextValue(popOutInput.getText());
				numOfElement = StockManagement.getMaxNumOfProduct(StockManagement.input);
				
				if(numOfElement != -1){ //If it is a valid integer					
					Stage stage = (Stage) confirmButton.getScene().getWindow();
					stage.hide();
					
					if (numOfElement == 0) {
						promptExitScene();
					}
					else {
						StockManagement.productList = new Product [numOfElement];
						promptAddProductScene();
					}
				}
				else { //If it is not a valid integer
					errorMessage.setText(InputData.getTextValue());
					errorMessage.setFill(InputData.getColour());
					popOutInput.clear();
				}
			}
			else if (Option == "No") {
				
				//To check if the user is entering a zero value
				if(popOutInput.getText().equalsIgnoreCase("0")){
					Stage stage = (Stage) confirmButton.getScene().getWindow();
					stage.hide();
					promptExitScene();
				}
				else {
					errorMessage.setText("Oppsss...Error. Please input zero to exit the program :)");
					errorMessage.setFill(Color.RED);
					popOutInput.clear();
				}
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
	
	//Direct to the addProduct scene
	private void promptAddProductScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addProduct.fxml"));
		Parent root = null;
		Stage addStage = new Stage();
		
		//To handle the IOException
		try {
			root = loader.load();
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		addStage.setTitle("Add Product");
		addStage.setScene(new Scene(root));
		addStage.show();
	}
}
