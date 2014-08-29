package org.adamsko.cubicforest.world;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.gui.NullGuiMaster;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.roundsMaster.NullRoundsMaster;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.tiled.MapsLoaderTiled;
import org.adamsko.cubicforest.world.mapsLoader.tiled.NullMapsLoaderTiled;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.NullCollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainerDefault;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.ordersMaster.NullOrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.pickmaster.NullPickMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;

/**
 * World class desc.
 * 
 * @author adamsko
 * 
 */
public class CubicWorldBuilder implements GameWorldBuilder, Nullable {

	private WorldObjectsMastersContainer worldObjectsMastersContainer;

	private PickMaster pickMaster;

	private OrdersMaster ordersMaster;
	private RoundsMaster roundsMaster;

	private GuiMaster guiMaster;
	private MapsLoader mapsLoader;

	private CollisionVisitorsManagerFactory collisionVisitorsManagerFactory;

	// for NullCubicWorldBuilder
	CubicWorldBuilder(final int nullConstructor) {
	}

	public CubicWorldBuilder() {
		initNullables();
	}

	private void initNullables() {
		pickMaster = NullPickMaster.instance();
		roundsMaster = NullRoundsMaster.instance();
		guiMaster = NullGuiMaster.instance();
		ordersMaster = NullOrdersMaster.instance();
		mapsLoader = NullMapsLoaderTiled.instance();
		collisionVisitorsManagerFactory = NullCollisionVisitorsManagerFactory
				.instance();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void initWorldObjectsMastersContainer(final GameRenderer renderer,
			final RoundsMaster roundsMaster) {
		worldObjectsMastersContainer = new WorldObjectsMastersContainerDefault(
				renderer, roundsMaster);
	}

	@Override
	public WorldObjectsMastersContainer getWorldObjectsMastersContainer() {
		return worldObjectsMastersContainer;
	}

	@Override
	public void initGuiMaster(final GameRenderer renderer,
			final TilesMaster tilesMaster, final MapsLoader mapsLoader,
			final GatherCubesMaster gatherCubesMaster) {

		if (mapsLoader.isNull()) {
			Gdx.app.error("initGuiMaster()", "mapsLoader.isNull()");
			return;
		}

		if (tilesMaster.isNull()) {
			Gdx.app.error("initGuiMaster()", "tilesMaster.isNull()");
			return;
		}

		if (gatherCubesMaster.isNull()) {
			Gdx.app.error("initGuiMaster()", "tilesMaster.isNull()");
			return;
		}

		guiMaster = new GuiMaster(tilesMaster, mapsLoader);
		guiMaster.addGui(gatherCubesMaster.getGatherCubesCounter());
		guiMaster.addClient(roundsMaster);

		for (final GuiContainer GC : guiMaster.getGuiList()) {
			renderer.addROMGui(GC);
		}
	}

	@Override
	public GuiMaster getGuiMaster() {
		return guiMaster;
	};

	@Override
	public void initMapsLoader() {
		mapsLoader = new MapsLoaderTiled();
		mapsLoader.loadMaps();
		mapsLoader.setMapActive(0);
	}

	@Override
	public MapsLoader getMapsLoader() {
		return mapsLoader;
	}

	@Override
	public void initOrdersMaster(final TilesMaster tilesMaster) {
		ordersMaster = new OrdersMaster(tilesMaster);
	}

	@Override
	public OrdersMaster getOrdersMaster() {
		if (ordersMaster.isNull()) {
			Gdx.app.error("getOrdersMaster()", "ordersMaster.isNull()");
		}
		return ordersMaster;
	}

	@Override
	public void initPickMaster(final GuiMaster guiMaster,
			final RoundsMaster roundsMaster) {
		if (guiMaster.isNull()) {
			Gdx.app.error("initPickMaster()", "guiMaster.isNull()");
			return;
		}
		if (roundsMaster.isNull()) {
			Gdx.app.error("initPickMaster()", "roundsMaster.isNull()");
			return;
		}

		pickMaster = new PickMaster();
		pickMaster.addClient(guiMaster);
		pickMaster.addClient(worldObjectsMastersContainer.getTilesMaster());
	}

	@Override
	public void initTilesMasterRoundsMaster(
			final RoundsMaster roundsMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		worldObjectsMastersContainer.getTilesMaster().initTilesEventsHandler(
				roundsMaster, collisionVisitorsManagerFactory);

		worldObjectsMastersContainer.getTilesMaster().addClient(roundsMaster);

		roundsMaster
				.setCollisionVisitorsManagerFactory(collisionVisitorsManagerFactory);

	}

	@Override
	public void initRoundsMaster() {

		roundsMaster = new RoundsMaster(this);

	}

	@Override
	public void initRoundsMasterPhases(final OrdersMaster ordersMaster,
			final WorldObjectsMastersContainer worldObjectsMastersContainer) {

		final HeroesMaster heroesMaster = worldObjectsMastersContainer
				.getHeroesMaster();
		final EnemiesMaster enemiesMaster = worldObjectsMastersContainer
				.getEnemiesMaster();
		final TilesMaster tilesMaster = worldObjectsMastersContainer
				.getTilesMaster();
		final HeroesToolsMaster heroesToolsMaster = worldObjectsMastersContainer
				.getHeroesToolsMaster();
		final GatherCubesMaster gatherCubesMaster = worldObjectsMastersContainer
				.getGatherCubesMaster();

		if (heroesMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "heroesMaster.isNull()");
			return;
		}
		if (enemiesMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "enemiesMaster.isNull()");
			return;
		}
		if (ordersMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "ordersMaster.isNull()");
			return;
		}
		if (tilesMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "tilesMaster.isNull()");
			return;
		}
		if (heroesToolsMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "heroesToolsMaster.isNull()");
			return;
		}
		if (gatherCubesMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "gatherCubesMaster.isNull()");
			return;
		}

		if (heroesMaster.getOrderableObjects().size() == 0) {
			Gdx.app.debug("getOrderableObjects", "0");
		}

		final PhaseHeroes phaseHeroes = new PhaseHeroes(heroesMaster,
				ordersMaster, tilesMaster, heroesToolsMaster, gatherCubesMaster);
		phaseHeroes.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseHeroes);

		final PhaseEnemies phaseEnemies = new PhaseEnemies(enemiesMaster,
				heroesMaster, ordersMaster);
		phaseEnemies.setRoundsMaster(roundsMaster);
		roundsMaster.addPhase(phaseEnemies);

		try {
			roundsMaster.nextRound();
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public RoundsMaster getRoundsMaster() {
		if (roundsMaster.isNull()) {
			Gdx.app.error("getRoundsMaster()", "roundsMaster.isNull()");
		}
		return roundsMaster;
	}

	@Override
	public void initCollisionVisitorsManagerFactory(
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster) {
		if (gatherCubesMaster.isNull()) {
			Gdx.app.error("initCollisionVisitorsManagerFactory",
					"gatherCubesMaster.isNull()");
			return;
		}
		if (heroesToolsMaster.isNull()) {
			Gdx.app.error("initCollisionVisitorsManagerFactory",
					"heroesToolsMaster.isNull()");
			return;
		}
		collisionVisitorsManagerFactory = new CollisionVisitorsManagerFactory(
				gatherCubesMaster, heroesToolsMaster);
	}

	@Override
	public CollisionVisitorsManagerFactory getCollisionVisitorsManagerFactory() {
		return collisionVisitorsManagerFactory;
	}

	@Override
	public void setMapActive(final int activeMapIndex) {
		mapsLoader.setMapActive(activeMapIndex);
	}

	/**
	 * Unload (clear), than load objects again to their original positions.
	 * Objects from particular WorldObjectsMasters should not be loaded before
	 * all other Masters unload their objects.
	 */
	@Override
	public void mapsLoaderReloadWorld(
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		mapsLoader.reloadMaps();

		final List<WorldObjectsMaster> worldObjectsMasters = worldObjectsMastersContainer
				.getWorldObjectsMasters();

		/*
		 * Unloading has to be done in reverse order, because TilesMaster's
		 * objects (tiles) should be unloaded in the end.
		 */
		for (int i = worldObjectsMasters.size() - 1; i >= 0; i--) {
			final WorldObjectsMaster master = worldObjectsMasters.get(i);
			try {
				master.unloadMapObjects();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		for (final WorldObjectsMaster master : worldObjectsMasters) {
			try {
				master.loadMapObjects(mapsLoader.getMapActive());
				master.initCollisionVisitorsManagers(collisionVisitorsManagerFactory);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		// uncomment to print tiles occupants
		final TilesContainer tc = (TilesContainer) worldObjectsMasters.get(0);
		tc.debugPrintOccupants(false);
	}

	@Override
	public void update(final float deltaTime) {
		pickMaster.update();
	}

}