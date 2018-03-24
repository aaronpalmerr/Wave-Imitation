package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/*
This enemy bounces more quickly around the walls of the GUI
*/

public class MenuParticles extends GameObject {

	private Handler handler;
	private Random r = new Random();
	Color color;

	public MenuParticles(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		speedX = r.nextInt(7-(-7)+(-7));
		speedY = r.nextInt(7-(-7)+(-7));
		if (speedX == speedY) {
			speedX = 3;
			speedY = 2;
		}
		if (speedX == 0) {
			speedX = 1;
		}
		if (speedY == 0) {
			speedY = 1;
		}
		if (speedX == speedY) {
			speedX = 3;
			speedY = 2;
		}
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	@Override
	public void tick() {
		x += speedX;
		y += speedY;

		// Keeps enemy from exiting x and y borders
		if (y <= 0 || y >= Game.HEIGHT-48) speedY *= -1;
		if (x <= 0 || x >= Game.WIDTH-32) speedX *= -1;

		handler.addObject(new Trail((int)x, (int)y, ID.Trail, color, 16, 16, 0.05f, handler));
	}

	@Override
	public void render(Graphics g) {
		// set color
		g.setColor(color);
		g.fillRect((int)x, (int)y, 16, 16);
	}



}
