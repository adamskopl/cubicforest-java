package org.adamsko.cubicforest.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.world.WorldRenderer;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
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
	private List<WorldObjectsMaster> worldObjectsMasters;
	private PickMaster pickMaster;

	private TilesMaster tilesMaster;
	private OrdersMaster ordersMaster;
	private RoundsMaster roundsMaster;
	private InteractionMaster interactionMaster;

	TerrainObjectsMaster terrainObjectsMaster;
	HeroesMaster heroesMaster;
	EnemiesMaster enemiesMaster;

	GatherCubesMaster gatherCubesMaster;

	HeroesToolsMaster heroesToolsMaster;

	GuiMaster guiMaster;

	public World(WorldRenderer renderer) {
		this.renderer = renderer;
		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		pickMaster = new PickMaster();

		tilesMaster = new TilesMaster(100);
		TilesSearcher.setTilesMaster(tilesMaster);

		terrainObjectsMaster = new TerrainObjectsMaster(tilesMaster,
				"terrain-atlas-medium", 42, 50);
		heroesMaster = new HeroesMaster(tilesMaster, "heroes-atlas-medium", 30,
				35);
		enemiesMaster = new EnemiesMaster(tilesMaster, "enemies-atlas-medium",
				30, 35);
		gatherCubesMaster = new GatherCubesMaster(tilesMaster,
				"cubes-atlas-medium", 25, 40);
		gatherCubesMaster.initGatherCubesCounter(tilesMaster);

		heroesToolsMaster = new HeroesToolsMaster(tilesMaster,
				gatherCubesMaster, "tools-atlas-medium", 40, 45);

		ordersMaster = new OrdersMaster(tilesMaster, heroesMaster);
		ordersMaster.tempSetTerrainObjectsMaster(terrainObjectsMaster);

		interactionMaster = new InteractionMaster();
		interactionMaster.addClient(gatherCubesMaster);
		interactionMaster.addClient(heroesToolsMaster);

		tilesMaster.setInteractionMaster(interactionMaster);

		initRoundsMaster();

		addWorldObjectsMaster(tilesMaster.getTilesContainer());
		addWorldObjectsMaster(terrainObjectsMaster);
		addWorldObjectsMaster(heroesMaster);
		addWorldObjectsMaster(enemiesMaster);
		addWorldObjectsMaster(gatherCubesMaster);
		addWorldObjectsMaster(heroesToolsMaster);

		guiMaster = new GuiMaster(tilesMaster);
		guiMaster.addGui(gatherCubesMaster.getGatherCubesCounter());
		guiMaster.addClient(roundsMaster);

		addGuiObjectsContainer(gatherCubesMaster.getGatherCubesCounter());
		for (GuiContainer GC : guiMaster.getGuiList()) {
			addGuiObjectsContainer(GC);
		}

		pickMaster.addClient(guiMaster);
		pickMaster.addClient(tilesMaster);
		tilesMaster.addClient(roundsMaster);

	}

	private void initRoundsMaster() {
		roundsMaster = new RoundsMaster();

		PhaseHeroes phaseHeroes = new PhaseHeroes(heroesMaster, ordersMaster,
				tilesMaster, heroesToolsMaster, gatherCubesMaster);
		phaseHeroes.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseHeroes);

		PhaseEnemies phaseEnemies = new PhaseEnemies(enemiesMaster,
				heroesMaster, ordersMaster);
		phaseEnemies.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseEnemies);

		try {
			roundsMaster.nextRound();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addGuiObjectsContainer(GuiContainer guiObjectsContainer) {
		renderer.addROMGui(guiObjectsContainer);
	}

	public void addWorldObjectsMaster(WorldObjectsMaster newWorldMaster) {
		worldObjectsMasters.add(newWorldMaster);
		renderer.addROMWorld(newWorldMaster);
	}

	public void update(float deltaTime) {
		pickMaster.update();
	}
}