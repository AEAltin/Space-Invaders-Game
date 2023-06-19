package user;

import java.io.FileWriter;

public class Register extends UserManager{
	
	public boolean registerSuccess;
	public String registerMessage;
	
	public Register(String username, String password) throws Exception {
		super(username, password, 0);
		registerSuccess = registerUser();		
	}
	public boolean registerUser() throws Exception {
		try {
			if(user.getUsername() == null || user.getPassword() == null) {
				registerMessage = "Username and password cannot be empty";
				return false;
			} else if(user.getUsername() == "" || user.getPassword() == "") {
				registerMessage = "Username and password cannot be empty";
				return false;
			} else if(user.getUsername().contains(" ") || user.getPassword().contains(" ")) {
				registerMessage = "Username cannot contain spaces";
				return false;
			} else {
				String updatedLine = user.getUsername() + " " + user.getPassword() + " " + Integer.toString(0);
				if(updatedLine.matches("\\S+ \\S+ \\S+")) {
					if(userExists(user.getUsername()) == -1) {
						FileWriter writer = new FileWriter(userInfoPath, true);
						writer.write(updatedLine);
						writer.write(System.lineSeparator());
						writer.close();
						return true;
					}
					throw new Exception("user already exists");
				}
				return false;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
