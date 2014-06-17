package core;

public class Entity {

	private int posy;
	private int posx;
	
	private boolean alive = false;
	
	private EntityManager en = null;
	
	public Entity(int posy, int posx, EntityManager en) {
		this.posy = posy;
		this.posx = posx;
		this.en = en;
	}
	
	public void update() {

		// TODO
		// Spiellogik !!
		
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
