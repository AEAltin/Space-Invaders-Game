package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionManager implements KeyListener{
	
	public boolean up;
	public boolean down;
	public boolean right;
	public boolean left;
	public boolean shoot;
	
	public ActionManager() {
		up = down = right = left = shoot = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	up = true;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	down = true;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	right = true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
        	left = true;
        } else if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
        	shoot = true;
        } else up = true;
    }

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
        	up = false;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
        	down = false;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
        	right = false;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
        	left = false;
        } else if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
        	shoot = false;
        }
	}
}
