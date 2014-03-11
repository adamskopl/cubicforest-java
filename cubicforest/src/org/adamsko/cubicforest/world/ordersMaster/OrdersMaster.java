package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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
	
	// TerrainObjectsMaster for testing purposes
	private TerrainObjectsMaster testTerrainObjectsMaster;

	private Boolean tempTestStarted;
	private Vector2 tempTargetPos;

	public void tempSetTerrainObjectsMaster(TerrainObjectsMaster tom) {
		this.testTerrainObjectsMaster = tom;
	}
	
	public OrdersMaster(TilesMaster tilesMaster, HeroesMaster heroesMaster) {
		tilePathsMaster = new TilePathsMaster(tilesMaster);
		this.heroesMaster = heroesMaster;
		tempTestStarted = false;
		tempTargetPos = new Vector2();
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		if (tempTestStarted == true) { 
			/*
			 * INSERT TEST TERRAIN OBJECT
			 */

			if(!tempTargetPos.equals(tile.getTilesPos())) {
				Gdx.app.log(tempTargetPos.toString(), tile.getTilesPos().toString());
				
				try {
					testTerrainObjectsMaster.addTestObject(tile.getTilesPos());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return;
		} // start test once			
		tempTestStarted = true;
		tempTargetPos = tile.getTilesPos();
		
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
