package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.WorldObjectsContainer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.HeroesMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

import com.badlogic.gdx.Gdx;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Manages orders (movement for now) for objects from different
 * {@link WorldObjectsMaster} objects.
 * 
 * @author adamsko
 * 
 */
public class OrdersMaster implements TilesMasterClient {

	private TilePathsMaster tilePathsMaster;
	private HeroesMaster heroesMaster;

	private Boolean tempTestStarted;

	public OrdersMaster(TilesMaster tilesMaster, HeroesMaster heroesMaster) {
		tilePathsMaster = new TilePathsMaster(tilesMaster);
		this.heroesMaster = heroesMaster;
		tempTestStarted = false;
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		if (tempTestStarted == true) { return;} // start test once			
		tempTestStarted = true;
		
		switch (event) {
		case TILE_PICKED: {
			WorldObject testObject = heroesMaster.getTestObject();
			tilePathsMaster.startPath(testObject, tile);

			break;
		}
		default: {

		}
		}
	}
}
