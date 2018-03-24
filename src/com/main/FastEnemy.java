package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
This enemy bounces more quickly around the walls of the GUI
*/

public class FastEnemy extends GameObject {

	private Handler handler;

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		speedX = 2;
		speedY = 8;
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

		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.orange, 16, 16, 0.03f, handler));
	}

	@Override
	public void render(Graphics g) {
		// set color
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 16, 16);
	}



}
