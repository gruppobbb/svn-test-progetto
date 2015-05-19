package model.spawning;

import model.MobsManager;
import model.mobs.Mob;
import model2D.MovingLogic2D;

/**
 * Componente che si occupa della gestione dello spawning dei mob.
 * @author Max
 */
public class Spawner implements Runnable {
	
	public int sleepTime = 200;
	private MobsManager manager;
	private MovingLogic2D mobMover;
	private SpawnLogic spawnLogic;
	private boolean toKill;
	
	/**
	 * Crea uno spawner, che inserisce nel {@link MobsManager} il {@link Mob} creato,
	 * a cui viene assegnato {@link MovingLogic2D} per determianre come vuoversi,
	 * il tutto secondo l'algoritmo definito in {@link SpawnLogic}.
	 * @param manager
	 * @param mobMover
	 * @param spawnLogic
	 */
	public Spawner(MobsManager manager, MovingLogic2D mobMover, SpawnLogic spawnLogic) {
		super();
		this.manager = manager;
		this.mobMover = mobMover;
		this.spawnLogic = spawnLogic;
	}
	
	private void spawn() {
		Mob[] mobs = spawnLogic.spawnMob(mobMover);
		for (int i = 0; i < mobs.length; i++) {
			manager.addMob(mobs[i]);
		}		
	}
	
	@Override
	public void run() {
		while(toKill == false) {
			spawn();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Imposta il flag che indica se il thread � da terminare.
	 */
	public void setToKill(boolean toKill) {
		this.toKill = toKill;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	
	
}
