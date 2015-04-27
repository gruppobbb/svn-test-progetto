package model.spawning;

import java.util.Random;

import assetsPc.Assets;
import model.Coordinate;
import model.mobs.Mob;
import model.mobs.Mob2D;
import model.movement.MovingLogic2D;

/**
 * Logica di spawning 2D basata sul concetto di corsia.
 * @author Max
 */
public class SimpleLanes2DSpawner implements SpawnLogic {
	
	private int width;
	private Random rand;
	
	/**
	 * Spawner che crea un {@link Mob} all'interno di width, un una corsia casuale.
	 * @param width
	 */
	public SimpleLanes2DSpawner(int width) {
		this.width = width;
		rand = new Random();
	}
	
	@Override
	public Mob spawnMob(MovingLogic2D mobMover) {
		
		int randX;
		float N = 0;
		float mobWidth;
		
		mobWidth = Assets.getLoader().getSprite(Assets.SPRITE_MOB).getWidth();
		N = (width/mobWidth);

		randX =(int)(mobWidth/2  + rand.nextFloat()*N*mobWidth) ;
		Mob mob = new Mob2D(new Coordinate(randX, -200, 0),	7, mobMover);
		return mob;
	}
}