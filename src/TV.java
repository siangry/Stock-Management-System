
public class TV extends Product {
	
	private String tvScreen;
	private String tvResolution ;
	private Double tvDisplaySize;
	
	//Parameterised constructor
	public TV (String proName, double proPrice, int qttAvail, String itemNum, String tvScreen, 
			String tvResolution, Double tvDisplaySize){
		
		super(proName, proPrice, qttAvail, itemNum);
		this.tvScreen = tvScreen;
		this.tvResolution = tvResolution;
		this.tvDisplaySize = tvDisplaySize;
	}
		
	//Mutators for all data fields
	public void setTvScreen(String tScreen){
		tvScreen = tScreen;
	}
		
	public void setTvResolution(String tResolution){
		tvResolution = tResolution;
	}
		
	public void setTvDisplaySize(double tDisplaySize){
		tvDisplaySize = tDisplaySize;
	}
		
	//Accessors for data fields
	public String getTvScreen(){
		return tvScreen;
	}
		
	public String getTvResolution(){
		return tvResolution;
	}
		
	public double getTvDisplaySize(){
		return tvDisplaySize;
	}
	
	//Method: Calculate the value of the stock of a TV.
	public double getTvInventoryValue() {
		return super.totInventoryValue();
	}
	
	@Override
	public String toString(){		
		StringBuilder str = new StringBuilder();
		
		str.append("Item Number     " + String.format("%11s", ": ") + getItemNum());
		str.append("\n" + "Product Name     " + String.format("%10s", ": ") + getProName());
		str.append("\nScreen type        " + String.format("%11s",": ") + getTvScreen());
		str.append("\nResolution         " + String.format("%12s",": ") + getTvResolution());
		str.append("\nDisplay size        " + String.format("%10s",": ") + getTvDisplaySize());
		str.append("\nQuantity Available   " + String.format("%4s", ": ") + getQttAvail());
		str.append("\n" + "Price (RM)         " + String.format("%12s", ": ") + String.format("%,.2f", 
				getProPrice()));
		str.append("\nInventory value (RM) : " + String.format("%,.2f", getTvInventoryValue()));
		str.append("\n" + "Product status      " + String.format("%8s", ": ") + getProStatus());
		
		return str.toString();
	}
}
