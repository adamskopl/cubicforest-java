package org.adamsko.cubicforest.world;

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
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverFactory;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResolverType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.terrain.TerrainMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.tilesSearcher.TilesSearcher;

/**
 * World class desc.
 * 
 * @author adamsko
 * 
 */
public class World {

	private final WorldRenderer renderer;
	MapsLoader mapsLoader;

	private final List<WorldObjectsMaster> worldObjectsMasters;
	private final PickMaster pickMaster;

	private final TilesMaster tilesMaster;
	private final OrdersMaster ordersMaster;
	private final RoundsMaster roundsMaster;
	private final InteractionMaster interactionMaster;

	TerrainMaster terrainObjectsMaster;
	HeroesMaster heroesMaster;
	EnemiesMaster enemiesMaster;

	GatherCubesMaster gatherCubesMaster;

	HeroesToolsMaster heroesToolsMaster;

	GuiMaster guiMaster;

	public World(final WorldRenderer renderer) {
		this.renderer = renderer;
		mapsLoader = new MapsLoaderTiled();
		mapsLoader.loadMaps();
		mapsLoader.setMapActive(0);

		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		pickMaster = new PickMaster();

		tilesMaster = new TilesMaster(100);
		TilesSearcher.setTilesMaster(tilesMaster);

		roundsMaster = new RoundsMaster(this);

		terrainObjectsMaster = new TerrainMaster(tilesMaster,
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

		initWorldObjectsMasters();

		reloadWorld();

		initRoundsMaster();

		guiMaster = new GuiMaster(tilesMaster, mapsLoader);
		guiMaster.addGui(gatherCubesMaster.getGatherCubesCounter());
		guiMaster.addClient(roundsMaster);

		addGuiObjectsContainer(gatherCubesMaster.getGatherCubesCounter());
		for (final GuiContainer GC : guiMaster.getGuiList()) {
			addGuiObjectsContainer(GC);
		}

		pickMaster.addClient(guiMaster);
		pickMaster.addClient(tilesMaster);
		tilesMaster.addClient(roundsMaster);

	}

	private void initWorldObjectsMasters() {
		// tiles container has to be added first, because objects are
		// removed/added to tiles
		addWorldObjectsMaster(tilesMaster.getTilesContainer());
		addWorldObjectsMaster(terrainObjectsMaster);
		addWorldObjectsMaster(heroesMaster);
		addWorldObjectsMaster(enemiesMaster);
		addWorldObjectsMaster(gatherCubesMaster);
		addWorldObjectsMaster(heroesToolsMaster);
	}

	private void initRoundsMaster() {

		final PhaseHeroes phaseHeroes = new PhaseHeroes(heroesMaster,
				ordersMaster, tilesMaster, heroesToolsMaster, gatherCubesMaster);
		phaseHeroes.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseHeroes);

		final PhaseEnemies phaseEnemies = new PhaseEnemies(enemiesMaster,
				heroesMaster, ordersMaster);
		phaseEnemies.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseEnemies);

		tilesMaster.initInteractionResultProcessor(heroesMaster, enemiesMaster,
				heroesToolsMaster, gatherCubesMaster, roundsMaster,
				phaseEnemies, phaseHeroes);

		try {
			roundsMaster.nextRound();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void initInteractionResolvers() {
		final InteractionResolverFactory interactionResolverFactory = new InteractionResolverFactory(
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

	public void setMapActive(final int activeMapIndex) {
		mapsLoader.setMapActive(activeMapIndex);
	}

	/**
	 * Unload (clear), than load objects again to their original positions.
	 * Objects from particular WorldObjectsMasters should not be loaded before
	 * all other Masters unload their objects.
	 */
	public void reloadWorld() {

		mapsLoader.reloadMaps();

		for (final WorldObjectsMaster wo : worldObjectsMasters) {
			try {
				wo.unloadMapObjects();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		for (final WorldObjectsMaster wo : worldObjectsMasters) {
			try {
				wo.loadMapObjects(mapsLoader.getMapActive());
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void addGuiObjectsContainer(final GuiContainer guiObjectsContainer) {
		renderer.addROMGui(guiObjectsContainer);
	}

	public void addWorldObjectsMaster(final WorldObjectsMaster newWorldMaster) {
		worldObjectsMasters.add(newWorldMaster);
		renderer.addROMWorld(newWorldMaster);
	}

	public void update(final float deltaTime) {
		pickMaster.update();
	}
}