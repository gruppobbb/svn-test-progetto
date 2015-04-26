package model.spawning;

import java.util.Random;

import model.Coordinate;
import model.mobs.Mob;
import model.mobs.Mob2D;
import model.movement.MovingLogic2D;
import view2d.assets.Assets;

/**
 * Logica di spawning 2D basata sul concetto di corsia.
 * @author Max
 *
 */
public class SimpleJans2DSpawner implements SpawnLogic {
	
	private int width;
	
	public SimpleJans2DSpawner(int width) {
		this.width = width;
	}
	
	@Override
	public Mob spawnMob(MovingLogic2D mobMover) {
		Random rand = new Random();
		int randX;
		int N = 0;
		int mobWidth;
		
		mobWidth = Assets.getLoader().getSprite(Assets.SPRITE_MOB).getWidth();
		N = (int)(width/mobWidth);

		randX = rand.nextInt(N+1)*mobWidth + mobWidth/2 ;
		Mob mob = new Mob2D(new Coordinate(randX, -200, 0),	7, mobMover);
		return mob;
	}

}
