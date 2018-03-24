package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
All game objects inherit from this class.
*/
public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float speedX, speedY;

	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds(); // if two rectangles intersect, method returns true


	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setSpeedX(float speed) {
		this.speedX = speed;
	}

	public void setSpeedY(float speed) {
		this.speedY = speed;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getID() {
		return this.id;
	}

	public float getSpeedX() {
		return this.speedX;
	}

	public float getSpeedY() {
		return this.speedY;
	}
}
