package model;

import java.util.ArrayList;
import java.util.Observable;

import model.audio.IAudioPlayer;
import model.mobs.Mob;
import model.movement.Moveable;
import model.ships.Ship;

/**
 * Componente che si occupa di far progredire il gioco, muovendo i mob e controllando le collisioni dei mob con la ship.
 * @author Max
 */
public class GameEngine extends Observable implements Runnable{
	
	private long sleepTime;
	private MobsManager mobsManager;
	private Ship ship;
	private Coordinate bounds;
	private boolean collided;
	private boolean toKill; //il thread deve terminare?
	private IAudioPlayer explosionPlayer;
	private boolean debugMode;
	
	public GameEngine(MobsManager mobsManager, Ship ship, Coordinate viewBounds) {
		this.mobsManager = mobsManager;
		this.ship = ship;
		this.bounds = viewBounds;
		this.sleepTime = 10; //default
	}	
	
	public void setExplosionPlayer(IAudioPlayer explosionPlayer) {
		this.explosionPlayer = explosionPlayer;
	}
	
	
	public void run() {
		while(toKill == false) {
			ArrayList<Mob> mobs = mobsManager.getMobsList();
			
			float shipX = ship.getCoordinate().getX();
			float shipY = ship.getCoordinate().getY();
			float shipZ = ship.getCoordinate().getZ();
			double shipCollisionRay = ship.getCollisionRay();
			
			for (Mob mob : mobs) {
				Moveable m = (Moveable) mob;
				m.move();
				
				//controllo collisione del mob con la navicella
				
				removeOutOfBoundsMobs(mob);	//questo metodo deve essere invocato prima del check delle collisioni
											//(inutile controllare se collide quando � fuori dal campo)
				
				checkCollisionWithShip(shipX, shipY, shipZ, shipCollisionRay,
						mob);
			}
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//controlla la collisione con la ship
	private void checkCollisionWithShip(float shipX, float shipY, float shipZ,
			double shipCollisionRay, Mob mob) {
		float mobX = mob.getCoordinate().getX();
		float mobY = mob.getCoordinate().getY();
		float mobZ = mob.getCoordinate().getZ();
		double mobCollisionRay = mob.getCollisionRay();
		double distance = Math.sqrt(	(mobX - shipX) * (mobX - shipX) +	
										(mobY - shipY) * (mobY - shipY) +	
										(mobZ - shipZ) * (mobZ - shipZ));

		if(distance < shipCollisionRay + mobCollisionRay) {
			if(debugMode){
				System.out.println("! COLLISION DETECTED @ " + System.currentTimeMillis());
			}
			if(explosionPlayer != null ){
				explosionPlayer.play();
			}
			setCollided(true);
		}
	}

	//rimuove i mob che sono fuori dai confini specificati (da bounds)
	private void removeOutOfBoundsMobs(Mob mob) {
		if(	mob.getCoordinate().getY() > bounds.getY() || 
			mob.getCoordinate().getX() > bounds.getX() || 
			mob.getCoordinate().getX() > bounds.getX()) {
			if(debugMode){
				System.out.println("X Mob " + mob.toString() + " has to be killed");
			}
			mobsManager.removeMob(mob);
		}
	}

	/**
	 * Abilita la visualizzazione su console di informazioni sull'andamento.
	 * @param debugMode
	 */
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	/**
	 * Ritorna true se � stata rilevata una collisione.
	 * @return
	 */
	public boolean isCollided() {
		return collided;
	}

	/**
	 * Imposta il flag che indica il rilevamento di una collisione.
	 * @param collided
	 */
	public void setCollided(boolean collided) {
		this.collided = collided;
		setChanged();
		notifyObservers();
	}

	/**
	 * Imposta il flag che indica se il thread � da terminare.
	 */
	public void setToKill(boolean toKill) {
		this.toKill = toKill;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	
}
