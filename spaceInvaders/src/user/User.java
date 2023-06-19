package user;

import java.io.*;	
public class User {
	
	private String username;
	private String password;
	private int score;
	private int id;
	private static int idCount = 0;
	public boolean isUpdated = false;
	
	public User(String username, String password, int score) {
		this.setUsername(username);
		this.setPassword(password);
		this.setScore(score);
		id = idCount++;
	}
	public User(String username, String password) {
		this(username, password, 0);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		if(this.score < score) {
			this.score = score;
		}
		if(this.score < 0) {
			this.score = 0;
		}
	}
	public int getId() {
		return id;	
	}

	public void updateHighScore(int score) {
		setScore(score);
		String path = System.getProperty("user.dir") + "\\Users\\users.txt";
		try{	
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = null;
			String text = "";
			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");
		        if(words[0].equals(getUsername())) {
		       		line = words[0] + " " + words[0] + " " + getScore();
		        }
		        text = text.concat(line + "\n");
			}
		    reader.close();

			PrintWriter writer = new PrintWriter(new FileWriter(path));
		    writer.write(text);
		    writer.close();
		    isUpdated = true;	
		} catch (IOException e) {
			e.printStackTrace();		
	    	System.out.println("IO Exception");
		}
	}	
}







