
public class Validation {
	
	//Check if the input is a digit
	public static boolean isDigit(String input) {
		boolean invalid = false;
		
		if (input.length() == 0) {
			invalid = true;
		}
		else if(input.length() == 1 && input.compareToIgnoreCase("-") == 0) {
			invalid = true;
		}
		else {
			for(int i = 0; i < input.length(); i++) {
				
				if(i == 0 && input.charAt(0) == '-') {
					invalid = false;
				}
				else if(input.charAt(i) == ' ') {
					invalid = true;
					break;
				}
				else if(Character.isDigit(input.charAt(i))) {
					invalid = false;
				}
				else {
					invalid = true;
					break;
				}
			}
		}
		return invalid;
	}
	
	//Check if the input value is a positive integer
	public static boolean isPositiveValue(int input) {
		boolean invalid = false;
		
		if(input >= 0) {
			invalid = false;
		}
		else
			invalid = true;
		
		return invalid;
	}
	
	//Check if the input is a double value
	public static boolean isDouble(String input) {
		boolean invalid = true;
		int dotNum = 0;
		
		if (input.length() == 0) {
			invalid = true;
		}
		else if(input.length() == 1 && input.compareToIgnoreCase(".") == 0) {
			invalid = true;
		}
		else {
			for(int i = 0; i < input.length(); i++) {
				
				if(input.charAt(i) == ' ' || (i == 0 && input.charAt(i) == '.') ||
						(i == input.length() -1 && input.charAt(i) == '.')) {
					invalid = true;
					break;
				}
				else if (input.charAt(i) == '.') {
					dotNum++;
					if (dotNum > 1) {
						invalid = true;
						break;
					}
				}
				else if(Character.isDigit(input.charAt(i))) {
					invalid = false;
				}
				else {
					invalid = true;
					break;
				}
			}
		}
		return invalid;
	}
	
}
