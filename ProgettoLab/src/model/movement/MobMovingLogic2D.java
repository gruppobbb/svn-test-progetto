package model.movement;

import model.Coordinate;
import model.mobs.Mob2D;

/**
 * Muove un mob in 2D.
 * @author Max
 *
 */
public class MobMovingLogic2D implements MovingLogic2D {
	
	@Override
	public void move(Mob2D mob) {
		Coordinate coordinate = mob.getCoordinate();
		coordinate.setY(coordinate.getY() + mob.getShiftAmount());
		mob.setCoordinate(coordinate);
	}

}