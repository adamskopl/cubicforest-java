package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

public class InteractionMaster {

	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) throws Exception {

		switch (evenType) {
		case OCCUPANT_ENTERS: {

			WorldObject occupant = eventTile.getOccupant();
			if (occupant.getType() == WorldObjectType_e.OBJECT_GATHER_CUBE) {
				if (eventObject.getType() == WorldObjectType_e.OBJECT_ENEMY) {
					Gdx.app.debug("interaction", "enemy->cube");
				}
				if (eventObject.getType() == WorldObjectType_e.OBJECT_HERO) {
					Gdx.app.debug("interaction", "hero->cube");
				}
			}
			
			break;
		}			
		case OCCUPANT_LEAVES: {
			break;			
		}
		default: {
			throw new Exception("tileEvent: unsupported event type");
		}
		}
	}
	
}
