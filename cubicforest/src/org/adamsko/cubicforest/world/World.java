package org.adamsko.cubicforest.world;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.render.world.WorldRenderer;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.tiled.MapsLoaderTiled;
import org.adamsko.cubicforest.world.objectsMasters.TerrainObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverFactory;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
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
	MapsLoader mapsLoader;

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
		mapsLoader = new MapsLoaderTiled();
		mapsLoader.loadMaps();
		mapsLoader.setMapActive(0);		

		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		pickMaster = new PickMaster();

		tilesMaster = new TilesMaster(100);
		TilesSearcher.setTilesMaster(tilesMaster);

		roundsMaster = new RoundsMaster(this);

		terrainObjectsMaster = new TerrainObjectsMaster(tilesMaster,
				"terrain-atlas-medium", 42, 50);
		heroesMaster = new HeroesMaster(tilesMaster, roundsMaster,
				"heroes-atlas-medium", 30, 35);
		enemiesMaster = new EnemiesMaster(tilesMaster, roundsMaster,
				"enemies-atlas-medium", 30, 35);
		gatherCubesMaster = new GatherCubesMaster(tilesMaster,
				"cubes-atlas-medium", 25, 40);
		gatherCubesMaster.initGatherCubesCounter(tilesMaster);

		heroesToolsMaster = new HeroesToolsMaster(tilesMaster,
				gatherCubesMaster, heroesMaster, "tools-atlas-medium", 40, 45);

		ordersMaster = new OrdersMaster(tilesMaster, heroesMaster,
				enemiesMaster, heroesToolsMaster, gatherCubesMaster);

		interactionMaster = new InteractionMaster();
		interactionMaster.addClient(gatherCubesMaster);
		interactionMaster.addClient(heroesToolsMaster);
		interactionMaster.addClient(heroesMaster);
		interactionMaster.addClient(enemiesMaster);

		tilesMaster.setInteractionMaster(interactionMaster);

		initInteractionResolvers();

		// tiles container has to be added first, because objects are
		// removed/added to tiles
		addWorldObjectsMaster(tilesMaster.getTilesContainer());
		addWorldObjectsMaster(terrainObjectsMaster);
		addWorldObjectsMaster(heroesMaster);
		addWorldObjectsMaster(enemiesMaster);
		addWorldObjectsMaster(gatherCubesMaster);
		addWorldObjectsMaster(heroesToolsMaster);

		reloadWorld();

		initRoundsMaster();

		guiMaster = new GuiMaster(tilesMaster, mapsLoader);
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

		PhaseHeroes phaseHeroes = new PhaseHeroes(heroesMaster, ordersMaster,
				tilesMaster, heroesToolsMaster, gatherCubesMaster);
		phaseHeroes.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseHeroes);

		PhaseEnemies phaseEnemies = new PhaseEnemies(enemiesMaster,
				heroesMaster, ordersMaster);
		phaseEnemies.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseEnemies);

		tilesMaster.initInteractionResultProcessor(heroesMaster, enemiesMaster,
				heroesToolsMaster, gatherCubesMaster, roundsMaster,
				phaseEnemies, phaseHeroes);

		try {
			roundsMaster.nextRound();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initInteractionResolvers() {
		InteractionResolverFactory interactionResolverFactory = new InteractionResolverFactory(
				heroesMaster, enemiesMaster, heroesToolsMaster,
				gatherCubesMaster);

		heroesMaster.setInteractionResolver(interactionResolverFactory
				.createFactory(InteractionResolverType_e.RESOLVER_HEROES));
		enemiesMaster.setInteractionResolver(interactionResolverFactory
				.createFactory(InteractionResolverType_e.RESOLVER_ENEMIES));
		gatherCubesMaster
				.setInteractionResolver(interactionResolverFactory
						.createFactory(InteractionResolverType_e.RESOLVER_GATHER_CUBES));
		heroesToolsMaster.setInteractionResolver(interactionResolverFactory
				.createFactory(InteractionResolverType_e.RESOLVER_HERO_TOOLS));
	}

	public void setMapActive(int activeMapIndex) {
		mapsLoader.setMapActive(activeMapIndex);
	}

	/**
	 * Unload (clear), than load objects again to their original positions.
	 * Objects from particular WorldObjectsMasters should not be loaded before
	 * all other Masters unload their objects.
	 */
	public void reloadWorld() {
		
		mapsLoader.reloadMaps();
		
		for (WorldObjectsMaster wo : worldObjectsMasters) {
			try {
				wo.unloadMapObjects();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (WorldObjectsMaster wo : worldObjectsMasters) {
			try {
				wo.loadMapObjects(mapsLoader.getMapActive());
			} catch (Exception e) {
				e.printStackTrace();
			}
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