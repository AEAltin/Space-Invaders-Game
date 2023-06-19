package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import game.ActionManager;
import user.User;
import entity.*;

public class GamePanel extends GeneralPanel implements Runnable{
	
	private User user = null;
	
	private static final int fps = 60;
	private static final int bgSpeed = 1;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private int level = 1;
	private int enemySpeed = 3;
	private int spawnRate = 2000;
	private int bulletRate = 1000;
	private int levelRate = 30000;	
	private boolean newLevel = true;
	private boolean won = false;
	private static int bgYPos = 0;
	private long frameCount = 0;
	private boolean gameOver = false;
	private int score = 0;	
	
	private LinkedList<Enemy> enemies;
	private LinkedList<Bullet> playerBullets;
	private LinkedList<Bullet> enemyBullets;
	public static Random rand = new Random();
	
	private static ActionManager am = new ActionManager();
	private Thread t = new Thread(this);
	private static Image bg;
	private static LinkedList<Image> gameoverBG = new LinkedList<Image>();
	
	private Player player = new Player(this, am);
	
	public GamePanel() {
		enemies = new LinkedList<Enemy>();
		playerBullets = new LinkedList<Bullet>();
		enemyBullets = new LinkedList<Bullet>();
		try {
			bg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\space_bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
			bg = null;
		}
		try {
			gameoverBG.add(ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\gameover1.png")));
			gameoverBG.add(ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\gameover2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.start();	
		run();
	}
	@Override
	public void run() {
		Timer gameLoop = new Timer(1000 / fps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!won) {
            		update();
            		repaint();
            			frameCount++;		
            	}
            }
        });
        gameLoop.start();
        Timer levelStart = new Timer(2000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
	            newLevel = false;
	            repaint();
			}
        });
        levelStart.start();
	}
	
	private void update() {
		if(frameCount == 3 * levelRate * fps/1000) {
			won = true;
		}
		if(won || gameOver) {
			enemies.clear();
			playerBullets.clear();
			enemyBullets.clear();
			score = player.score;
			if(user != null && !user.isUpdated) {
				user.updateHighScore(score);
			}	
				
		}
		if(everyNMilisecond(levelRate) && frameCount != 0 && level < 3) {
			level++;
			enemySpeed += 2*level;
			spawnRate = 2000 - level * 200;
			bulletRate = 1000 - level * 125;
			enemies.clear();
			enemyBullets.clear();
			playerBullets.clear();	
			newLevel = true;
			return;
		}
		if(bgYPos >= HEIGHT) {
			bgYPos = 0;
		}
			bgYPos += bgSpeed;
		player.update();	
		if(player.isShooting && Bullet.lastShot > 25) {
			playerBullets.add(new Bullet(player.x + player.img.getWidth(null)/2, player.y, -6));
			Bullet.lastShot = 0;		
		}
		Bullet.lastShot++;
		if(everyNMilisecond(spawnRate)) {
			if(everyNMilisecond(spawnRate * 8) && frameCount != 0	) {
				for(int x = 0; x < WIDTH; x += Enemy.imgWidth + 20) {
					enemies.add(new PurpleEnemy(x, -100));	
				}
			} else {
				enemies.add(new GreenEnemy(rand.nextInt(WIDTH - Enemy.imgWidth),	 -100, enemySpeed, rand.nextBoolean()));
			}
		}
		if(everyNMilisecond(bulletRate) && !enemies.isEmpty()) {	
			int index = rand.nextInt(enemies.size());
			enemyBullets.add(new Bullet(enemies.get(index).x + enemies.get(index).img.getWidth(null)/2, enemies.get(index).y + enemies.get(index).img.getHeight(null), 6));		
		}
		LinkedList<Enemy> enemiesToRemove = new LinkedList<Enemy>();
		for(Enemy enemy : enemies) {	
			LinkedList<Bullet> bulletsToRemove = new LinkedList<Bullet>();
			for(Bullet bullet : playerBullets) {
				if(collide(bullet, enemy)) {
					enemy.health--;
					if(enemy.getClass() == GreenEnemy.class) {
						player.score += level * 10;
					}
					else if(enemy.getClass() == PurpleEnemy.class) {
						player.score += level * 20;	
					}
					bulletsToRemove.add(bullet);
				}
				if(bullet.y < -bullet.img.getHeight(null)) {
					bulletsToRemove.add(bullet);
					continue;	
				}
			}
			playerBullets.removeAll(bulletsToRemove);
			bulletsToRemove.clear();
			for(Bullet bullet : enemyBullets) {
				if(collide(bullet, player) && player.invincibilityFrames >= 60) {
					player.invincibilityFrames = 0;
					player.health--;
					bulletsToRemove.add(bullet);	
				} else if(collide(bullet, enemy)) {
					bulletsToRemove.add(bullet);
				}else if(bullet.y > HEIGHT) {
					bulletsToRemove.add(bullet);
				}
			}	
			enemyBullets.removeAll(bulletsToRemove);
			if(enemy.y > GamePanel.HEIGHT || enemy.health == 0) {
				enemiesToRemove.add(enemy);
				continue;
			}
			if(collide(player, enemy) && player.invincibilityFrames >= 60) {
				player.invincibilityFrames = 0;
				player.health--;
			}
			enemy.update();	
		}
		for(Bullet bullet : enemyBullets) {	
			bullet.update();
		}
		for(Bullet bullet : playerBullets) {
			bullet.update();
		}	
		enemies.removeAll(enemiesToRemove);
		if(player.health <= 0) {
				gameOver = true;			
		}
	}
	@Override
	public void paintComponent(Graphics rawGraphics) {
        super.paintComponent(rawGraphics);
        Graphics2D g = (Graphics2D)rawGraphics;
        if (bg != null) {
            g.drawImage(bg, 0, bgYPos, this);
            g.drawImage(bg, 0, bgYPos-HEIGHT, this);
        }
        if(won && !gameOver) {
        	g.setColor(new Color(188, 253, 196));
	        g.setFont(font.deriveFont(font.getSize() * 72F));
	        g.drawString("you won!", 270, 150);	
	        g.setFont(font.deriveFont(font.getSize() * 48F));
	        g.drawString("score: " + player.score, 310, 300);
	        return;
        }
        if(newLevel && !gameOver && !won) {	
        	g.setColor(new Color(188, 253, 196));
	        g.setFont(font.deriveFont(font.getSize() * 48F));
	        g.drawString("Level " + level, 330, 250);
	        return;
        }
        if(!gameOver) {
	        for(Enemy enemy : enemies) {
				enemy.draw(g);
			}
	        for(Bullet bullet : playerBullets) {
				bullet.draw(g);	
			}
	        for(Bullet bullet : enemyBullets) {	
				bullet.draw(g);	
			}
	        player.draw(g);	
	        g.setColor(new Color(188, 253, 196));
	        g.setFont(font.deriveFont(font.getSize() * 24F));
	        g.drawString("Level " + level, 20, 30);
	        g.drawString((100000 * frameCount/(levelRate*fps))%100 +"%", 100, 30);
	        g.drawString(Integer.toString(player.health), WIDTH-75, 30);
	        g.drawString(""+player.score, 350, 30);	
	        g.setFont(new Font("Arial", Font.PLAIN, 24));	
	        g.drawString("\u2665", WIDTH-60	, 30);	
        } else {
        	if(frameCount % fps < 30) {
        		g.drawImage(gameoverBG.get(0), 0, 0, this);	
        	} else {		
        		g.drawImage(gameoverBG.get(1), 0, 0, this);
        	}
        	g.setColor(new Color(102, 204, 204));	
	        g.setFont(font.deriveFont(font.getSize() * 48F));
	        g.drawString("score: " + player.score, 320	, 430);		
        }
        g.dispose();
    }
	private boolean everyNMilisecond(int n) {
		return frameCount%(fps * n/1000) == 0;
	}
	private boolean collide(Entity e1, Entity e2) {
		if(e1.x < e2.x + e2.img.getWidth(null) && e1.x + e1.img.getWidth(null) > e2.x) {
			if(e1.y < e2.y + e2.img.getHeight(null) && e1.y + e1.img.getHeight(null) > e2.y) {
				return true;
			}
		}
		return false;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void restart() {
		level = 1;
		enemySpeed = 3;
		spawnRate = 2000;
		bulletRate = 1000;
		levelRate = 30000;
		newLevel = true;
		won = false;
		enemies.clear();
		playerBullets.clear();
		enemyBullets.clear();
		bgYPos = 0;
		frameCount = 0;
		gameOver = false;
		score = 0;
		player.restart();
	}
}