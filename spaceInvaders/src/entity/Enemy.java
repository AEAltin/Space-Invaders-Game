package entity;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameObject;

public class Enemy extends Entity implements GameObject{

	public static int imgWidth;
	public static int imgHeight;
	public int fallSpeed = 1;
	public Enemy(int x, int y, int health, int speed, String imgFileName) {
		super(x, y, health, speed);
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\" + imgFileName));
			imgWidth = img.getWidth(null);
			imgHeight = img.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
			img = null;
		}
	}

	@Override
	public void update() {
		y += fallSpeed;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

}
