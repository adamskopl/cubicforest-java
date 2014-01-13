package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.pickmaster.PickMaster;
import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.WorldRenderer;
import org.adamsko.cubicforest.world.objectsMasters.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

/**
 * World class desc.
 * 
 * @author adamsko
 * 
 */
public class World {
	
	WorldRenderer renderer;	
	List <WorldObjectsMaster> worldObjectsMasters;
	PickMaster pickMaster;
	
	TilesMaster tilesMaster;
	TerrainObjectsMaster terrainObjectsMaster;
	HeroesMaster heroesMaster;
	
	public World(WorldRenderer renderer) {
		this.renderer = renderer;
		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		pickMaster = new PickMaster();
		
		tilesMaster = new TilesMaster(64);
		terrainObjectsMaster = new TerrainObjectsMaster(tilesMaster, "terrain-atlas-medium", 42, 50);
		heroesMaster = new HeroesMaster(tilesMaster, "heroes-atlas-medium", 30, 35);

		addWorldObjectsMaster(tilesMaster.getTilesContainer(), true);
		addWorldObjectsMaster(terrainObjectsMaster, true);
		addWorldObjectsMaster(heroesMaster, true);
		
		pickMaster.addClient(tilesMaster);		
		
	}

	public void addWorldObjectsMaster(WorldObjectsMaster newWorldMaster, Boolean renderable) {
		worldObjectsMasters.add(newWorldMaster);
		if(renderable) {
			renderer.addROM((RenderableObjectsMaster)newWorldMaster);
		}
	}
	
	public void update (float deltaTime) {
		pickMaster.update();
	}
}