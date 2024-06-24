import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//Launch the application
public class StockManagement extends Application{

	protected static Product [] productList = null;
	protected static StringBuilder list = null; //Used to transfer data between classes
	protected static String ques = null; //Used to transfer simple data between classes
	private static Product selectedProduct = ExecuteMethodController.chosenProduct; 
	protected static Scanner input = new Scanner(System.in);
	protected static UserInfo user = new UserInfo();

	public static void main(String[] args) {
		launch(args);
		input.close();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Load the user interface defined in JavaFX.fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("JavaFX.fxml"));
		Parent root = loader.load();
		
		primaryStage.setTitle("Stock Management System");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	
	//Get the maximum number of products that the user wishes to store in the system
	public static int getMaxNumOfProduct(Scanner input) {
		boolean invalid = false;
		String maxNum;
		int maxNumProduct = 0;
		
		//Question will be display in the GUI (optionSceneController.java)
		//InputData.getTextValue() is used as an intermediate to get the input from GUI
		maxNum = InputData.getTextValue();
		
		//Check the if the input is an integer
		invalid = Validation.isDigit(maxNum);

		if (invalid) {
			
			//Display error message
			InputData.setTextValue("Oppss...Error. Please enter a valid integer value.");
			InputData.setColour(Color.RED);
			return -1;
		}
		
		else {
			maxNumProduct = Integer.parseInt(maxNum);
			
			if (maxNumProduct >= 0) {
				invalid = false;
				InputData.setColour(Color.GREEN);
				return maxNumProduct;
			}
			else {
				invalid = true;
				String errorMessage = "Oppsss...Error. You can only input a value that is greater or equal to zero.";
				InputData.setTextValue(errorMessage);
				InputData.setColour(Color.RED);
				return -1;
			}
		}
	}
	
	
	//Display the contents of the products array
	public static Product selectProductForUpdate (Product[] productArray, Scanner input) {
		boolean invalid = false;
		int index = -1;
		
		//Both list and ques are used to transfer the text that want to display on GUI of other classes
		list = new StringBuilder();
		for(int i = 0; i < StockManagement.productList.length; i++) {
			list.append("[" + (i + 1) + "] " + productArray[i].getProName() + "\n");
		}
		ques = new String("Which product do you want to update? ");
		
		String keyboard = InputData.getTextValue();
		invalid = Validation.isDigit(keyboard);

		if(!invalid) {
			index = Integer.parseInt(keyboard) - 1;
			
			//To avoid ArrayOutOfBoundException
			if(index < 0 || index >= productArray.length) {
				invalid = true;
				InputData.setTextValue("Oppsss...Error. You can only enter value between 1 to " + 
						(productArray.length) + ".");
				InputData.setColour(Color.RED);
				return selectedProduct = null;
			}
			else {
				invalid = false;
				InputData.setColour(Color.GREEN);
				return selectedProduct = productArray[index];
			}
		}
		else {
			InputData.setTextValue("Oppssss....Error. Please enter a valid integer value.");
			InputData.setColour(Color.RED);
			return selectedProduct = null;
		}
		
	}
	
	//Display the menu of the system
	public static int displayMenu(Scanner input) {
		
		//The menu of the system will be automatically displayed in the menuScene
		boolean invalid = false;
		int index = -1;
		
		String keyboard = InputData.getTextValue();
		invalid = Validation.isDigit(keyboard);
		if(!invalid) {
			//To check if the input value is within 0 and 4
			if (Integer.parseInt(keyboard) >= 0 && Integer.parseInt(keyboard) <= 4) {
				index = Integer.parseInt(keyboard);
			}
			else {
				invalid = true;
				InputData.setTextValue("Oppsss...Error. You can only input value between 0 to 4.");
				InputData.setColour(Color.RED);
			}
		}
		else {
			InputData.setTextValue("Oppss...Error. Please enter a valid integer value.");
			InputData.setColour(Color.RED);
		}
		return index;
	}
	
	//Add stock values to each identified product
	public static void addStock(Product[] productArray, Scanner input) {		
		boolean invalid = false;
		int addAmount = 0;
		
		if (selectedProduct != null) {
			ques = new String("How many stocks do you want to add? ");
			String keyboard = InputData.getTextValue();
			invalid = Validation.isDigit(keyboard);
			
			if (!invalid) {
				addAmount = Integer.parseInt(keyboard);
				//Validation.isPositiveValue(int) is used to check if the input value is a positive integer
				invalid = Validation.isPositiveValue(addAmount);
				
				if(invalid) {
					InputData.setTextValue("Oppsss...Error. You can only input a value greater or equal 0.");
					InputData.setColour(Color.RED);
				}
				else {
					//If valid, add the stock value of product
					selectedProduct.AddQttAvail(addAmount);
				}
			}
			else {
				InputData.setTextValue("Oppss...Error. Please enter a valid integer value.");
				InputData.setColour(Color.RED);
			}
		}
	}
	
	//Deduct stock values to each identified product
	public static void deductStock(Product[] productArray, Scanner input) {
		boolean invalid = false;
		int deductAmount = 0;
		
		ques = new String("How many stocks do you want to deduct? ");
		String keyboard = InputData.getTextValue();
		invalid = Validation.isDigit(keyboard);
		
		if (!invalid) {
			deductAmount = Integer.parseInt(keyboard);
			
			//To check if the deduct amount is between 0 and the max quantity available of the product
			if(deductAmount >= 0 && deductAmount <= selectedProduct.getQttAvail()) {
				invalid = false;
				selectedProduct.DeductQttAvail(deductAmount);
			}
			else {
				invalid = true;
				InputData.setTextValue("Oppsss...Error. You can only input value between 0 to " +
				selectedProduct.getQttAvail());
				InputData.setColour(Color.RED);
			}
		}
		else {
			InputData.setTextValue("Oppss...Error. Please enter a valid integer value.");
			InputData.setColour(Color.RED);
		}
	}
	
	//Set the status of the chosen product to false (discontinued)
	public static void setStatus(Product[] productArray, Scanner input) {
		selectedProduct.setProStatus(false);
		InputData.setTextValue("Ta-Da...The product is successfully discontinued.");
		InputData.setColour(Color.GREEN);
	}
	
	//Execute the appropriate methods
	public static void executeMethod(int menuChoice, Product[] productArray, Scanner input) {
		switch (menuChoice) {
		case 0:
			//Will be directed to the exit scene in the menuSceneController
			break;
		case 1:
			displayProductArray(productArray);
			break;
		case 2:
			addStock(productArray, input);
			break;
		case 3:
			deductStock(productArray, input);
			break;
		case 4:
			setStatus(productArray, input);
			break;
		}
	}
	
	//Allow user to select either to add a refrigerator or a TV product
	public static void addProduct(Product[] productArray, Scanner input) {
		boolean invalid = false;
		String keyboard;
		int option = -1;
		
		keyboard = InputData.getTextValue();
		invalid = Validation.isDigit(keyboard);
		
		if(!invalid) {
			option = Integer.parseInt(keyboard);
			if(option != 1 && option != 2) {
				invalid = true;
				InputData.setTextValue("Oppss...Error. You can only enter 1 or 2.");
				InputData.setColour(Color.RED);
			}
			else {
				InputData.setColour(Color.GREEN);
			}
		}
		else {
			InputData.setTextValue("Oppsss...Error. Please enter a valid integer value.");
			InputData.setColour(Color.RED);
		}
		
		if(option == 1) {
			addRefrigerator();
		}
		else if (option == 2) {
			addTV();
		}
	}
	
	public static void addRefrigerator() {
		
		StringBuilder refriQues = new StringBuilder();
        refriQues.append("Please input the details of the refrigerator:");
        refriQues.append("\nProduct Name: ");
        refriQues.append("\nDoor Design: ");
        refriQues.append("\nColor: ");
        refriQues.append("\nCapacity: ");
        refriQues.append("\nQuantity Available: ");
        refriQues.append("\nPrice (RM): ");
        refriQues.append("\nItem number: ");
        
        //To pass the question to addProductController as header display
        InputData.setTextValue(refriQues.toString());
        
        //This method will then automatically invoke addProduct() in the addProductController.java
        //to add a refrigerator.
	}
	
	public static void addTV() {
		
		StringBuilder tvQues = new StringBuilder();
		tvQues.append("Please input the details of the TV:");
		tvQues.append("\nProduct Name: ");
		tvQues.append("\nScreen Type: ");
		tvQues.append("\nResolution: ");
		tvQues.append("\nDisplay Size: ");
		tvQues.append("\nQuantity Available: ");
        tvQues.append("\nPrice (RM): ");
        tvQues.append("\nItem number: ");
		
		//To pass the question to addProductController as header display
        InputData.setTextValue(tvQues.toString());
        
        //This method will then automatically invoke addProduct() in the addProductController.java
        //to add a refrigerator.
	}
	
	public static void displayProductArray(Product[] productArray) {
		
		list = new StringBuilder();
		
		for(int i = 0 ; i < productArray.length; i++) {
			String type = null;
			
			if(productArray[i] instanceof Refrigerator) {
				type = " - Refrigerator";
			}
			else if (productArray[i] instanceof TV) {
				type = " - TV";
			}
			
			list.append("Product " + (i + 1) + type + "\n");
			list.append(productArray[i].toString() + "\n\n");
		}
		
		//The Product List will then be shown on screen.
	}
}
