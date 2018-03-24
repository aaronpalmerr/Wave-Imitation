package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
Controlls for the player and appearance.
*/

public class Player extends GameObject {

	private Random r = new Random();
	private Handler handler;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		x += speedX;
		y += speedY;

		x = Game.clamp((int)x, 0, Game.WIDTH -48); // doesn't allow player to leave room
		y = Game.clamp((int)y, 0, Game.HEIGHT -70); // doesn't allow player to leave room

		collision();
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy) {
				// check if tempObject is an enemy
				if (getBounds().intersects(tempObject.getBounds())) {
					// if this.player intersects with enemy object
					HUD.HEALTH -= 2;
				}
			}

			if (tempObject.getID() == ID.EnemyBoss) {
				// check if tempObject is an enemy
				if (getBounds().intersects(tempObject.getBounds())) {
					// if this.player intersects with enemy object
					HUD.HEALTH -= 10;
				}
			}
			
			
		}

	}

	public void render(Graphics g) {

		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 32, 32);

	}


}
