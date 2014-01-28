package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.HeroesMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMasterClient;

import com.badlogic.gdx.Gdx;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class OrdersMaster implements TilesMasterClient{

	private TilePathsMaster tilePathsMaster;
	private HeroesMaster heroesMaster;
	
	private boolean tempTestStarted = false;
	
	public OrdersMaster(TilesMaster tilesMaster, HeroesMaster heroesMaster) {
		tilePathsMaster = new TilePathsMaster(tilesMaster);
		this.heroesMaster = heroesMaster;
	}

	@Override
	public void onTileEvent(Tile tile, TileEvent_e event) {
		
		tempTestStarted = true;
		if(tempTestStarted)return; // start test once
		
		switch(event) {
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
