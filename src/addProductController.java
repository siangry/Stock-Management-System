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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addProductController implements Initializable{
	
	protected int elementNum = optionSceneController.numOfElement;
	protected static int index = 0;
	@FXML
	private TextField optionInput;
	@FXML
	private Text productNum;
	@FXML
	private Button cancelButton;
	@FXML
	private Text errorMessage;
	@FXML
	private Button confirmButton;
	@FXML
	private VBox vbox;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private Text text3;
	@FXML
	private Text text4;
	@FXML
	private TextField name;
	@FXML
	private TextField input1;
	@FXML
	private TextField input2;
	@FXML
	private TextField input3;
	@FXML
	private TextField quantity;
	@FXML
	private TextField price;
	@FXML
	private TextField itemNum;
	@FXML
	private HBox hbox1;
	@FXML
	private HBox hbox2;
	@FXML
	private HBox hbox3;
	@FXML
	private HBox hbox4;
	@FXML
	private HBox hbox5;
	@FXML
	private HBox hbox6;
	@FXML
	private Text errorMessage2;
	@FXML
	private Button nextButton;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vbox.setVisible(false);
		nextButton.setVisible(false);
		
		cancelButton.setOnMouseClicked(e -> {
			optionInput.clear();
		});
		
		optionInput.setOnMouseClicked(e -> {
			errorMessage.setText("");
		});	
		
		confirmButton.setOnMouseClicked(e -> {
            InputData.setTextValue(optionInput.getText());
            StockManagement.addProduct(StockManagement.productList, StockManagement.input);

            if (InputData.getColour() == Color.RED) {
            	errorMessage.setText(InputData.getTextValue());
            	errorMessage.setFill(InputData.getColour());
            	optionInput.clear();
            }
            else if (InputData.getColour() == Color.GREEN) {
            	optionInput.setEditable(false);
            	confirmButton.setDisable(true);
            	cancelButton.setDisable(true);
            	addProduct();
            }
        });
		
		//When the next button is clicked, it will prompt to the next addProductScene
		nextButton.setOnMouseClicked(e -> {
			Stage stage = (Stage) nextButton.getScene().getWindow();
			stage.hide();
			promptAddProductScene();
		});
		
		String name = "Product " + (index + 1);
		productNum.setText(name);
	}
	
	public void addProduct() {
		vbox.setVisible(true);
		
		String[] ques = InputData.getTextValue().split("\n");
		text1.setText(ques[0]);
		text2.setText(ques[2]);
		text3.setText(ques[3]);
		text4.setText(ques[4]);
		
		StringBuilder textInput = new StringBuilder();
		
		//1 - Name
		name.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkString(name, textInput)) {
					hbox1.setVisible(true);
					input1.requestFocus();
				}
				
			}
		});
		
		//2 - Door design / Screen type
		input1.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkString(input1, textInput)) {
					hbox2.setVisible(true);
					input2.requestFocus();
				}
			}
		});
		
		//3 - Colour / Resolution
		input2.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkString(input2, textInput)) {
					hbox3.setVisible(true);
					input3.requestFocus();
				}
			}
		});
		
		//4 - (Double) Capacity / Display size
		input3.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkDouble(input3, textInput)) { //If the input value is a double
					hbox4.setVisible(true);
					quantity.requestFocus();
				}
			}
		});
		
		//5 - (Int) Quantity available
		quantity.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkPositiveInteger(quantity, textInput)) { 
					hbox5.setVisible(true);
					price.requestFocus();
				}				
			}
		});
		
		//6 - (Double) Price
		price.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkDouble(price, textInput)) { 
					hbox6.setVisible(true);
					itemNum.requestFocus();
				}
			}
		});
		
		//7 - Item Num
		itemNum.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				if (!checkString(itemNum, textInput)) {
					String[] text = textInput.toString().split("\n");
					
					//To add new Product into the Product List
					if (ques[2].compareToIgnoreCase("Door Design: ") == 0) {
						StockManagement.productList[index] = new Refrigerator(text[0], Double.parseDouble(text[5]),
								Integer.parseInt(text[4]), text[6], text[1], text[2], Double.parseDouble(text[3]));
					}
					else if (ques[2].compareToIgnoreCase("Screen Type: ") == 0) {
						StockManagement.productList[index] = new TV(text[0], Double.parseDouble(text[5]),
								Integer.parseInt(text[4]), text[6], text[1], text[2], Double.parseDouble(text[3]));
					}
					
					errorMessage2.setText("Product is inserted successfully.");
					errorMessage2.setFill(Color.GREEN);
					index++;
					
					//If there is another product to add
					if(index < elementNum) {
						nextButton.setVisible(true);
					}
					else {
						Stage stage = (Stage) itemNum.getScene().getWindow();
						stage.hide();
						promptMenuScene();
					}
				}
			}
		});
	}
	
	public boolean checkDouble(TextField input, StringBuilder textInput) {
		
		//Check if the input value is double by invoking the isDouble() function from Validation class
		boolean invalid = Validation.isDouble(input.getText());
		
		//Display error message
		if(invalid) {
			errorMessage2.setText("Oppsss...Error. Please enter a valid value.");
			errorMessage2.setFill(Color.RED);
			input.clear();
		}
		else {
			textInput.append(input.getText() + "\n");
			input.setEditable(false);
			input.setDisable(true);
			errorMessage2.setText(null);
		}
		return invalid;
	}
	
	public boolean checkPositiveInteger(TextField input, StringBuilder textInput) {
		
		//Check if the input value is integer by invoking the isDigit() function from Validation class
		boolean invalid = false; 
		invalid = Validation.isDigit(input.getText());
		
		//Display error message
		if(invalid) {
			errorMessage2.setText("Oppsss...Error. Please enter a valid value.");
			errorMessage2.setFill(Color.RED);
			input.clear();
		}
		else {
			//Check if the input is a positive integer
			invalid = Validation.isPositiveValue(Integer.parseInt(input.getText()));
			
			if(invalid) {
				errorMessage2.setText("Oppsss...Error. Please enter a positive integer.");
				errorMessage2.setFill(Color.RED);
				input.clear();
			}
			else {
				textInput.append(input.getText() + "\n");
				input.setEditable(false);
				input.setDisable(true);
				errorMessage2.setText(null);
			}
		}
		return invalid;
	}
	
	public boolean checkString(TextField input, StringBuilder textInput) {
		//Check if the input length is greater than zero
		if(input.getText().length() <= 0) {
			errorMessage2.setText("Oppsss...Error. Please enter a valid input.");
			errorMessage2.setFill(Color.RED);
			input.clear();
			return true;
		}
		else {
			textInput.append(input.getText() + "\n");
			input.setEditable(false);
			input.setDisable(true);
			errorMessage2.setText(null);
			return false;
		}		
	}
	
	//To prompt the addProductScene
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
