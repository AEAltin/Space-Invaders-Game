		package view;

import java.awt.event.*;
import javax.swing.*;

import user.*;

public class ViewManager {
	
	private MainFrame frame;
	private GeneralPanel panel;
	
	private GamePanel gamePanel = new GamePanel();
	private HighScorePanel hsPanel = new HighScorePanel();
	private MainMenuPanel mainPanel = new MainMenuPanel();
	
	public static void main(String args[]) {
		
		ViewManager viewManager = new ViewManager();		
	}

	public ViewManager() {
		
		frame = new MainFrame();
		panel = mainPanel;	
		setMenuActions();
		frame.add(getPanel());
		frame.setVisible(true);
	}
	
	public GeneralPanel getPanel() {
		return panel;
	}
	
	public MainFrame getFrame() {
		return frame;
	}
	
	private void setFrame() {
		getFrame().getContentPane().removeAll();
		getFrame().getContentPane().add(getPanel());
		getFrame().validate();
	}
	
	private void setMenuActions() {
		
		frame.getJMenuBar().getMenu(0).getItem(0).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Register register = null;
				String username = JOptionPane.showInputDialog(null, "Enter username:");
				String password = JOptionPane.showInputDialog(null, "Enter password:");
				try {
					register = new Register(username, password);
					if(register.registerSuccess) {
						JOptionPane.showMessageDialog(null, "Register Successful");
					}
					else {
						if(register.registerMessage != null) {
							JOptionPane.showMessageDialog(null, "Register Failed\n" + register.registerMessage);
						}
						else {
							JOptionPane.showMessageDialog(null, "Register Failed");
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		frame.getJMenuBar().getMenu(0).getItem(1).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Login login = null;
				String username = JOptionPane.showInputDialog(null, "Enter username:");
				String password = JOptionPane.showInputDialog(null, "Enter password:");
				try {
					login = new Login(username, password);
					if(login.loginSuccess) {
						JOptionPane.showMessageDialog(null, "Login Successful");
						gamePanel.setUser(login.getUser());	
						gamePanel.restart();
						panel = gamePanel;
						setFrame();
					}	
					else {
						JOptionPane.showMessageDialog(null, "Login Failed");
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		frame.getJMenuBar().getMenu(0).getItem(2).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				hsPanel = new HighScorePanel();			
				panel = hsPanel;
				setFrame();
			}
			
		});
		frame.getJMenuBar().getMenu(0).getItem(3).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		frame.getJMenuBar().getMenu(1).getItem(0).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Created by Ali Emir Altin\n20200702011\naliemir.altin@std.yeditepe.edu.tr");
				setFrame();
			}
		});
		frame.getJMenuBar().getMenu(0).addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {
				((JMenu)e.getSource()).doClick();				
			}
		});
		frame.getJMenuBar().getMenu(1).addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {
				((JMenu)e.getSource()).doClick();				
			}
		});
		
	}
	
}
