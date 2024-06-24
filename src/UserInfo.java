public class UserInfo {
	private String name;
	private String userID;
	
	public UserInfo() {
		userID = "guest";
	}
	
	//To check if the inputName is valid
	public boolean setName (String inputName) {
		boolean invalid = false;
		
		if (!inputName.isEmpty()) {
			for(int i = 0; i < inputName.length(); i++) {
				char j = inputName.charAt(i);
				
				if (i == 0 && j == ' ') {
					return invalid = true;
				}
				
				if(Character.isLetter(j) || j == ' ') {
					invalid = false;
				}
				else {
					return invalid = true;
				}
			}
			name = new String(inputName);
			setUserID();
			return invalid;
		}
		else 
			return invalid = true;
	}
	
	public boolean checkSpace() {
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == ' ') 
				return true;
		}
		return false;
	}
	
	public void setUserID() {
		if (checkSpace()) {
			int index = name.lastIndexOf(' ');
			userID = name.charAt(0) + name.substring(index + 1);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getUserID() {
		return userID;
	}
}


