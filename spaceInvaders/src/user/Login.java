package user;

import java.io.*;

public class Login extends UserManager{
	
	public boolean loginSuccess;
	
	public Login(String username, String password) {
		super(username, password, 0);
		loginSuccess = validate();
	}
	public boolean validate() {
		try {
            FileReader fileReader = new FileReader(userInfoPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
            	String[] words = line.split(" ");
            	if(words[0].replaceAll("\uFEFF", "").equals(user.getUsername().trim())) {
            		if(words[1].equals(user.getPassword())) {
            			return true;
            		}
					return false;
            	}
            }
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		System.out.println("couldn't find the username");
		return false;
	}
}
