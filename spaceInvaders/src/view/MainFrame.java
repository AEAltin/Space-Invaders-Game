package view;

import javax.swing.*;

public class MainFrame extends JFrame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	JMenuBar menuBar;
	
	public MainFrame() {
		super("Space Invaders");
		setMenuBar();
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	private void setMenuBar() {
		menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		JMenuItem menuRegister = new JMenuItem("Register");
		JMenuItem menuPlayGame = new JMenuItem("Play Game");
		JMenuItem menuHighScore = new JMenuItem("High Score");
		JMenuItem menuQuit = new JMenuItem("Quit");
		
		JMenu menuHelp = new JMenu("Help");		
		JMenuItem menuAbout = new JMenuItem("About");
		
		menuFile.add(menuRegister);
		menuFile.add(menuPlayGame);
		menuFile.add(menuHighScore);
		menuFile.add(menuQuit);
		
		menuHelp.add(menuAbout);
		
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		
		setJMenuBar(menuBar);
	}
}
