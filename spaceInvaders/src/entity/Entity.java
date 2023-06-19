package entity;

import java.awt.Image;

public class Entity{
	public int x;
	public int y;
	public int health;
	public int speed;
	public Image img;
	
	public Entity(int x, int y, int health, int speed) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.speed = speed;
	}
}
