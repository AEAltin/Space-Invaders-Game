package user;

import java.io.*;

public class UserManager {
	
	protected User user;
	protected static final String userInfoPath = "Users\\users.txt";
	
	public UserManager(String username, String password, int score) {
		user = new User(username, password, score);
	}

	public int userExists(String username) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(userInfoPath));
			String line = reader.readLine();
			int lineCount = 0;
			while(line != null) {
				String[] words = line.split(" ");
				if(words[0].equals(username)) {
					return lineCount;
				}
				lineCount++;
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	public User getUser() {
		return user;
	}
}