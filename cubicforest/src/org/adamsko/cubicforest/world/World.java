package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMaster;
import org.adamsko.cubicforest.render.RenderableObject;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.WorldRenderer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * World class desc.
 * @author adamsko
 *
 */
public class World {
	
	WorldRenderer renderer;	
	List <WorldObjectsMaster> worldObjectsMasters;
	
	PickMaster pickMaster;
	
	public World(WorldRenderer renderer) {
		this.renderer = renderer;
		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		pickMaster = new PickMaster();
		
		TilesMaster tilesMaster = new TilesMaster(64);
		addWorldMaster(tilesMaster, true);
		pickMaster.addClient(tilesMaster);
		
	}

	public void addWorldMaster(WorldObjectsMaster newWorldMaster, Boolean renderable) {
		worldObjectsMasters.add(newWorldMaster);
		if(renderable) {
			renderer.addROM((RenderableObjectsMaster)newWorldMaster);
		}
	}
	
	public void update (float deltaTime) {
		pickMaster.update();
	}
}