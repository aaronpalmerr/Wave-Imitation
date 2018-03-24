package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	
	
	public void mousePressed(MouseEvent e) {
	
		
		// temp variables for getting mouse position
		int mx = e.getX();
		int my = e.getY();
		
		
		if (game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mx, my, 200, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT/2-32, ID.Player, handler));
			
				AudioPlayer.getSound("menu_sound").play();
			}
			
			// help button
			if (mouseOver(mx, my, 200, 225, 200, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}
			
			// quit button
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				System.exit(0);
			}
		}
		
		// back button for help menu
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				game.gameState = STATE.Menu;
				return;
			}
		}

		// try again button for help menu
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 200, 300, 200, 64)) {
				hud.resetGame();
				game.gameState = STATE.Menu;
				return;
			}
		}

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	/**
	 * Checks whether the mouse is over a button by testing
	 * if the mouse is within the boundaries of the box. 
	 * @param mx
	 * @param my
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			// fonts
			Font font = new Font("ariel", 1, 50);
			Font font2 = new Font("ariel", 1, 30);
			
			// menu font
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Snakes 'n Tanks", 105, 100);
			
			// play button and font
			g.setColor(Color.white);
			g.drawRect(200, 150, 200, 64);
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Play", 265, 190);
			
			
			
			// set help button and font
			g.setColor(Color.white);
			g.drawRect(200, 225, 200, 64);
			g.drawString("Help", 265, 268);
			
			
			// quit button and font
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Quit", 265, 343);
		}
		else if (game.gameState == STATE.Help) {
			
			// fonts
			Font font = new Font("ariel", 1, 50);
			Font font2 = new Font("ariel", 1, 20);
			Font font3 = new Font("ariel", 1, 30);
			
			// help menu font
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help", 235, 100);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("Use WASD keys to move player and dodge enemies ", 60, 210);
			
			g.setFont(font3);
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Back", 265, 343);
			
		}
		else if (game.gameState == STATE.End) {
			
			// fonts
			Font font = new Font("ariel", 1, 50);
			Font font2 = new Font("ariel", 1, 20);
			Font font3 = new Font("ariel", 1, 30);
			
			// help menu font
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Over", 170, 100);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawString("You lost with a score of "+hud.getScore(), 170, 210);
			
			g.setFont(font3);
			g.setColor(Color.white);
			g.drawRect(200, 300, 200, 64);
			g.drawString("Try again?", 230, 343);
			
			
			
			
		}
		
	}
}
