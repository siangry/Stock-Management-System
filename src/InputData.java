import javafx.scene.paint.Color;

//Act as an intermediate to transfer data between multiple classes
public class InputData {  

    private static String textValue;
    private static Color colour;

    public static void setTextValue(String value) {
        textValue = value;
    }

    public static String getTextValue() {
        return textValue;
    }
    
    public static void setColour(Color color) {
    	colour = color;
    }
    
    public static Color getColour() {
    	return colour;
    }
}
