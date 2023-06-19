package entity;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameObject;

public class Bullet extends Entity implements GameObject{
	
	public static int lastShot = 0;
	
	public Bullet(int x, int y, int speed) {
		super(x, y, 0, speed);
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {	
		y += speed;	
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

}
