package entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.*;
import view.GamePanel;

public class Player extends Entity implements GameObject{
	
	private GamePanel panel;
	private ActionManager am;
	public boolean isShooting = false;
	public int invincibilityFrames = 60;
	public int score = 0;
	
	public Player(GamePanel panel, ActionManager am) {
		super(400, 500, 3, 5);
		this.panel = panel;
		this.am = am;
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		panel.addKeyListener(am);
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
			img = null;
		}
		try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } catch (AWTException e) {
            e.printStackTrace();
        }
	}
	public void restart() {
		x = 400;
		y = 500;
		health = 3;
		speed = 5;
		try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } catch (AWTException e) {
            e.printStackTrace();
        }
	}
	public void update() {
		if(am.up) {
			if(y > 0) {
				y -= speed;
			}
		}
		if(am.down) {
			if(y < 510) {
				y += speed;
			}
		}
		if(am.left) {
			if(x > 0) {
				x -= speed;
			}
		}
		if(am.right) {
			if(x < 745) {	
				x += speed;
			}
		}
		isShooting = am.shoot;
		invincibilityFrames++;
	}	
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);	
	}
}