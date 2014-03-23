package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.WorldRenderer;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.tilesSearcher.TilesSearcher;

import com.badlogic.gdx.Gdx;

/**
 * World class desc.
 * 
 * @author adamsko
 * 
 */
public class World {
	
	private WorldRenderer renderer;	
	private List <WorldObjectsMaster> worldObjectsMasters;
	private PickMaster pickMaster;
	
	private TilesMaster tilesMaster;
	private OrdersMaster ordersMaster;
	private RoundsMaster roundsMaster;
	
	TerrainObjectsMaster terrainObjectsMaster;
	HeroesMaster heroesMaster;
	EnemiesMaster enemiesMaster;
	
	public World(WorldRenderer renderer) {
		this.renderer = renderer;
		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		pickMaster = new PickMaster();
		
		tilesMaster = new TilesMaster(100);
		TilesSearcher.setTilesMaster(tilesMaster);
				
		terrainObjectsMaster = new TerrainObjectsMaster(tilesMaster, "terrain-atlas-medium", 42, 50);
		heroesMaster = new HeroesMaster(tilesMaster, "heroes-atlas-medium", 30, 35);
		enemiesMaster = new EnemiesMaster(tilesMaster, "enemies-atlas-medium", 30, 35);
				
		ordersMaster = new OrdersMaster(tilesMaster, heroesMaster);
		ordersMaster.tempSetTerrainObjectsMaster(terrainObjectsMaster);
		
		initRoundsMaster();

		addWorldObjectsMaster(tilesMaster.getTilesContainer(), true);
		addWorldObjectsMaster(terrainObjectsMaster, true);
		addWorldObjectsMaster(heroesMaster, true);
		addWorldObjectsMaster(enemiesMaster, true);
		
		pickMaster.addClient(tilesMaster);
		tilesMaster.addClient(roundsMaster);

	}
	
	private void initRoundsMaster() {
		roundsMaster = new RoundsMaster();
		
		PhaseHeroes phaseHeroes = new PhaseHeroes(heroesMaster, ordersMaster);
		phaseHeroes.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseHeroes);		
		
		PhaseEnemies phaseEnemies = new PhaseEnemies(enemiesMaster, heroesMaster, ordersMaster);
		phaseEnemies.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseEnemies);
		
		try {
			roundsMaster.nextRound();
		} catch (Exception e) {			
			e.printStackTrace();
		}
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