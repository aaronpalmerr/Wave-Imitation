package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
This is the class which all other enemeies will inherit from.
*/

public class EnemyBoss extends GameObject {

	private Handler handler;
	private int timer = 60;
	private int timer2 = 50;
	private Random r = new Random();
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		speedX = 0;
		speedY = 2;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

	@Override
	public void tick() {
		x += speedX;
		y += speedY;
		
		if (timer <= 0) {
			speedY = 0;
		}
		else {
			timer--;
		}

		if (timer <= 0) {
			timer2--;
		}
		if (timer2 <= 0) {
			if (speedX == 0) {
				speedX = 2;
			}
				
				int spawn = r.nextInt(10);
				if (spawn == 0) {
					handler.addObject(new EnemyBossBullet((int)x+48, (int)y+48, id.BasicEnemy, handler));
				}
		}
		
		
		// Keeps enemy from exiting x and y borders
//		if (y <= 0 || y >= Game.HEIGHT-48) speedY *= -1;
		if (x <= 0 || x >= Game.WIDTH-110) speedX *= -1;

		//handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.MAGENTA, 96, 96, 0.03f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, 96, 96);
	}



}
