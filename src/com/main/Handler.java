package com.main;

/*
This class holds all the game objects that will
be used in the game.
*
*/
import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	GameObject tempObject;
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if (tempObject.getID() != ID.Player) {
				object.remove(i);
				i--;
			}
		}
		
	}
	
	public void clearPlayer() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if (tempObject.getID() == ID.Player) {
				if (Game.gameState == Game.STATE.End) {
					object.remove(i);
				}
				
			}
		}
	}

}
