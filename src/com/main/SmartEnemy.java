package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
This enemy follows the player around.
*/

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player) {
				player = handler.object.get(i);
			}
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	@Override
	public void tick() {
		x += speedX;
		y += speedY;

		//float diffX = x - player.getSpeedX() - 16;
		//float diffY = y - player.getSpeedY() - 16;
		//float distance = (float) Math.sqrt( (x-player.getSpeedX()) * (x-player.getSpeedX()) + (y-player.getSpeedY()) * (y-player.getSpeedY()) );
		//speedX = (float) ((-1.0/distance) * diffX);
		//speedY = (float) ((-1.0/distance) * diffY);

		// easier code for a stalker
		if (x > player.getX()) {
			speedX = -1;
		}
		else if (x < player.getX()) {
			speedX = 1;
		}
		else if (x == player.getX()) {
			speedX = 0;
		}

		if (y > player.getY()) {
			speedY = -1;
		}
		else if (y < player.getY()) {
			speedY = 1;
		}
		else if (y == player.getY()) {
			speedY = 0;
		}

		// Keeps enemy from exiting x and y borders
		if (y <= 0 || y >= Game.HEIGHT-48) speedY *= -1;
		if (x <= 0 || x >= Game.WIDTH-32) speedX *= -1;

		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, 16, 16);
	}



}
