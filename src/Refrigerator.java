
public class Refrigerator extends Product {

	private String refriDoor;
	private String refriColor;
	private double refriCapacity;
	
	//Parameterised constructor
	public Refrigerator(String proName, double proPrice, int qttAvail, String itemNum, String refriDoor, 
			String refriColor, double refriCapacity){
		super(proName, proPrice, qttAvail, itemNum);
		this.refriDoor = refriDoor;
		this.refriColor = refriColor;
		this.refriCapacity = refriCapacity;
	}
		
	//Mutators for all data fields
	public void setRefriDoor(String rDoor){
		refriDoor = rDoor;
	}
		
	public void setRefriColor(String rColor){
		refriColor = rColor;
	}
		
	public void setRefriCapacity(double rCapacity){
		refriCapacity = rCapacity;
	}
		
	//Accessors for data fields
	public String getRefriDoor(){
		return refriDoor;
	}
		
	public String getRefriColor(){
		return refriColor;
	}
	
	public double getRefriCapacity(){
		return refriCapacity;
	}
	
	//Method: Calculate the value of the stock of a refrigerator
	public double getRefriInventoryValue() {
		return super.totInventoryValue();
	}
		
	@Override
	public String toString() {		
		StringBuilder str = new StringBuilder();
		
		str.append("Item Number    " + String.format("%11s", ": ") + getItemNum());
		str.append("\n" + "Product Name    " + String.format("%10s", ": ") + getProName());
		str.append("\nDoor Design      " + String.format("%11s",": ") + getRefriDoor());
		str.append("\nColor           " + String.format("%17s",": ") + getRefriColor());
		str.append("\nCapacity (in Litres)    " + String.format("%2s",": ") + getRefriCapacity());
		str.append("\nQuantity Available  " + String.format("%4s", ": ") + getQttAvail());
		str.append("\n" + "Price (RM)         " + String.format("%12s", ": ") + String.format("%,.2f", 
				getProPrice()));
		str.append("\nInventory value (RM) : " + String.format("%,.2f", getRefriInventoryValue()));
		str.append("\n" + "Product status      " + String.format("%8s", ": ") + getProStatus());
			
		return str.toString();
	}
}
