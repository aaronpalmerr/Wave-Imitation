package com.main;

import java.awt.Color;
import java.awt.Graphics;

/**
Displays the head-up display
*/

public class HUD {

	public static int HEALTH = 100;
	private int greenValue = 255;
	private int score = 0;
	private int level = 1;

	public void tick() {
		HEALTH = (int) Game.clamp(HEALTH, 0, 100); // keep health in borders
		greenValue = (int) Game.clamp(greenValue, 0, 255);

		greenValue = HEALTH * 2;
		score++;
	}

	public void render(Graphics g) {
		// sets grey behind health
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);

		// set health color to green
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH*2, 32);

		// set grey border of health bar
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);

		// draw score and level onto the hud
		g.drawString("Score: "+ score, 15, 64);
		g.drawString("Level: "+ level, 15, 80);

	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}
	public void resetGame() {
		level = 1;
		score = 0;
	}
}
