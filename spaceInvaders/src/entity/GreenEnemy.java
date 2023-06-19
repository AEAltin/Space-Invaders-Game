package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;	

public class GreenEnemy extends Enemy{
	

	public boolean movementWay;
	private int frameCount = 0;
	private Random rand = new Random();
	private int enemyNo;
	private Image[] imgs = {null, null, null, null	};
	public GreenEnemy(int x, int y, int speed, boolean movementKey) {
		super(x, y, 3, speed, "green1_down.png");
		this.movementWay = movementKey;
		try {
			imgs[0] = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\green1_down.png"));
			imgs[1] = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\green1_up.png"));
			imgs[2] = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\green2_down.png"));	
			imgs[3] = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\green2_up.png"));
			enemyNo = rand.nextInt(2);		
			} catch (IOException e) {
				e.printStackTrace();
		}
	}

	@Override
	public void update() {
		super.update();
		if(x <= 0) movementWay = false;
		if(x >= 800-imgWidth	) movementWay = true;	
		if(movementWay) {
			x -= speed;
		}
		else {
			x += speed;
		}
		if(frameCount % 60 < 30) {
			img = imgs[2*enemyNo];
		}
		else {
			img = imgs[2*enemyNo + 1];		
		}
		frameCount++;
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		
	}

}
