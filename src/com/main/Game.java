package com.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Instance of the game
 * @author apalm
 *
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 4088146271165387233L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = (WIDTH / 12 * 9);
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	public static boolean pause = false;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End,
	}
	public static STATE gameState = STATE.Menu;
	
	
	public Game() {
		
		// class variables
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		r = new Random();
		new Window(WIDTH, HEIGHT, "Snakes 'n Tanks", this);
		spawn = new Spawn(handler, hud);
		
		
		this.addKeyListener(new KeyInput(handler));
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		
		
		if (gameState == STATE.Game) {
			// add players
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			
			
		}
		else {
			for (int i = 0; i <20; i++) {
				handler.addObject(new MenuParticles(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticles, handler));
			}
			
		}
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		thread.start();
	}

	public void run() {
		requestFocus(); // don't need to click on screen to control input

		// complex gaming code that magically runs game
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();

	}

	private void tick() {
		handler.tick();
		
		if (gameState == STATE.Game) {
			hud.tick();
			spawn.tick();
			
			if (hud.HEALTH <= 0) {
				hud.HEALTH = 100;
				gameState = STATE.End;
				handler.clearEnemies();
				handler.clearPlayer();
				for (int i = 0; i <20; i++) {
					handler.addObject(new MenuParticles(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticles, handler));
				}
				
			}
			
		}
		else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
		
		

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g); // render handler
		
		if (gameState == STATE.Game) {
			hud.render(g); // render HUD
		}
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}

		g.dispose();
		bs.show();

	}

	/**
	 * Sets the limits that a player can travel to in the GUI,
	 * thus ensure they remain in the borders.
	 * @param var
	 * @param min
	 * @param max
	 * @return
	 */
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		}
		else if (var <= min) {
			return var = min;
		}
		return var;
	}

	public static void main(String [] args) {
		new Game();



	}

}
