package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HighScorePanel extends GeneralPanel {
	
	private static final String userInfoPath = "Users\\users.txt";
	private NavigableMap<Integer, String> scores;
	
	public HighScorePanel() {
		super("space_bg.png");
		scores = new TreeMap<Integer, String>();
		getUserData();
		listHighScores();
	}
	private void getUserData() {
		try {
            FileReader fileReader = new FileReader(userInfoPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null && line.matches("\\S+ \\S+ \\S+")) {
            	String[] words = line.split(" ");
            	scores.put(Integer.valueOf(words[2]), words[0].replaceAll("\uFEFF", ""));
            }
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		scores = scores.descendingMap();
	}
	private void listHighScores(){
		
		addLabel("high scores", 0.5f, 0.2f, 64);
		int i = 0;
		for(Map.Entry<Integer, String> el : scores.entrySet()) {
			if(i < 5) {
				addLabel((i+1) + ". " + el.getValue() + ": " + el.getKey(), 0.5f, 0.4f + 0.1f*i);
				i++;
			}
			else break;
		}
	}
}
