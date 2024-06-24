import javafx.scene.paint.Color;

public abstract class Product {

	private String proName;
	private double proPrice;
	private int qttAvail; //Quantity Available
	private String itemNum;
	private boolean proStatus;
	
	//Default constructor
	public Product() {
		proStatus = true;
	}
	
	//Parameterised constructor
	public Product(String proName, double proPrice, int qttAvail, String itemNum) {
		this.proName = proName;
		this.proPrice = proPrice;
		this.qttAvail = qttAvail;
		this.itemNum = itemNum;
		proStatus = true;
	}
	
	//Mutators for all data fields
	public void setProName(String pName) {
		proName = pName;
	}
	
	public void setProPrice(double pPrice) {
		proPrice = pPrice;
	}
	
	public void setQttAvail(int qAvail) {
		qttAvail = qAvail;
	}
	
	public void setItemNum(String iNum) {
		itemNum = iNum;
	}
	
	public void setProStatus(boolean pStatus) {
		proStatus = pStatus;
	}
	
	//Accessors for data fields
	public String getProName() {
		return proName;
	}
	
	public double getProPrice() {
		return proPrice;
	}
	
	public int getQttAvail() {
		return qttAvail;
	}
	
	public String getItemNum() {
		return itemNum;
	}
	
	public boolean getProStatus() {
		return proStatus;
	}
	
	//Instance Method: Get the total inventory value
	public double totInventoryValue() {
		return proPrice * qttAvail;
	}
	
	//Instance Method: Allow user to add the number of quantity available in stock
	public void AddQttAvail(int quantity) {
		if (proStatus) { //proStatus == true
			qttAvail += quantity;
			setQttAvail(qttAvail);
			InputData.setTextValue("Adding number of quantity... " + 
			"\nTa-da! The number of quantity is successfully added :)");
			InputData.setColour(Color.GREEN);
		}
		
		else {//proStatus == false
			InputData.setTextValue("\nOoopps, this is a discontinued product!\nYou are not able to modify on it :(");
			InputData.setColour(Color.CHOCOLATE);
		}
	}
	
	//Instance Method: Allow user to deduct the number of quantity available in stock
	public void DeductQttAvail(int quantity) {
		qttAvail -= quantity;
		setQttAvail(qttAvail);
		InputData.setTextValue("\nDeducting number of quantity..." + 
		"\nTa-da! The number of quantity is successfully deducted :)");	
		InputData.setColour(Color.GREEN);
	}

	@Override
	public String toString() {		
		StringBuilder str = new StringBuilder();
		str.append("Item Number" + String.format("%11s", ": ") + itemNum);
		str.append("\n" + "Product Name" + String.format("%10s", ": ") + proName);
		str.append("\nQuantity Available" + String.format("%4s", ": ") + qttAvail);
		str.append("\n" + "Price (RM)" + String.format("%12s", ": ") + String.format("%,.2f", proPrice));
		str.append("\nInventory value (RM): " + String.format("%,.2f", totInventoryValue()));
		str.append("\n" + "Product status" + String.format("%8s", ": ") + proStatus);
				
		return str.toString();
	}
	
}


