package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PurpleEnemy extends Enemy{

	private int frameCount = 0;	
	private Image[] imgs = {null, null};	
	public PurpleEnemy(int x, int y) {
		super(x, y, 10, 0, "purple1_down.png");
		try {
			imgs[0] = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\purple1_down.png"));
			imgs[1] = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\purple1_up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update() {
		super.update();
		if(frameCount % 60 < 30) {
			img = imgs[0];
		}
		else {
			img = imgs[1];	
		}
		frameCount++;	
	}
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}