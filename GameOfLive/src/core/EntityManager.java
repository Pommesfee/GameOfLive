package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class EntityManager {

	private LinkedList<Entity> entities = new LinkedList<Entity>();

	private int screenWidth;
	private int screenHeight;
	private int tileCountX;
	private int tileCountY;
	
	private int tileWidth;
	private int tileHeight;
	
	public EntityManager(int screenWidth, int screenHeight, int tileCountX, int tileCountY) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.tileCountX = tileCountX;
		this.tileCountY = tileCountY;
		
		calTiles();
		createTiles();
	}
	
	public Entity getXY(int x, int y) {
		for (Entity e : entities) {
			if (e.getPosx() == x && e.getPosy() == y) {
				return e;
			}
		}
		return null;
	}
	
	private void createTiles() {
		for (int x = 0; x < tileCountX; x++) {
			for (int y = 0; y < tileCountY; y++) {
				entities.add(new Entity(x, y, this));
			}
		}
	}

	private void calTiles() {
		tileWidth = screenWidth / tileCountX;
		tileHeight = screenHeight / tileCountY;
	}

	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}

	public void drawEntities(Graphics g) {
		g.setColor(Color.RED);
		for (Entity e : entities) {
			if (e.isAlive()) {
				g.setColor(Color.GREEN);
			}
			g.fillRect(((e.getPosx() * tileWidth) + 3), ((e.getPosy() * tileHeight) + 3), (tileWidth - 6), (tileHeight - 6));
		}
	}

	public void drawLines(Graphics g) {
		for (int x = 0; x < tileCountX; x++) {
			g.drawLine(x * tileWidth, 0, x* tileWidth, (tileHeight * tileCountY));
		}
		for (int y = 0; y < tileCountY; y++) {
			g.drawLine(0, y * tileHeight, tileWidth * tileCountX, y * tileHeight);
		}
	}
	
}
