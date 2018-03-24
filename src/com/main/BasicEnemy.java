package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
This is the class which all other enemeies will inherit from.
*/

public class BasicEnemy extends GameObject {

	private Handler handler;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		speedX = 4;
		speedY = 4;
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

		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.03f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}



}
