package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.gui.NullGuiMaster;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalc;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalcDefault;
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
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;

public class CubicWorldBuilder implements GameWorldBuilder, Nullable {

	private CoordCalc coordCalc;

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
	public void initWorldObjectsMastersContainer(final GameRenderer renderer) {
		worldObjectsMastersContainer = new WorldObjectsMastersContainerDefault(
				renderer);
	}

	@Override
	public WorldObjectsMastersContainer getWorldObjectsMastersContainer() {
		return worldObjectsMastersContainer;
	}

	@Override
	public void initGuiMaster(final GameRenderer renderer,
			final MapsLoader mapsLoader,
			final GatherCubesMaster gatherCubesMaster,
			final RoundsMaster roundsMaster) {

		if (mapsLoader.isNull()) {
			Gdx.app.error("initGuiMaster()", "mapsLoader.isNull()");
			return;
		}

		if (gatherCubesMaster.isNull()) {
			Gdx.app.error("initGuiMaster()", "tilesMaster.isNull()");
			return;
		}

		guiMaster = new GuiMaster(mapsLoader);
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
	public void initMapsLoader(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		mapsLoader = new MapsLoaderTiled(worldObjectsMastersContainer,
				collisionVisitorsManagerFactory);
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
			final TilesMaster tilesMaster, final CoordCalc coordCalc) {
		if (guiMaster.isNull()) {
			Gdx.app.error("initPickMaster()", "guiMaster.isNull()");
			return;
		}
		if (roundsMaster.isNull()) {
			Gdx.app.error("initPickMaster()", "roundsMaster.isNull()");
			return;
		}

		pickMaster = new PickMaster(coordCalc);
		pickMaster.addClient(guiMaster);
		pickMaster.addClient(tilesMaster);
	}

	@Override
	public void initTilesMasterRoundsMaster(
			final TilesMaster tilesMaster,
			final RoundsMaster roundsMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		tilesMaster.initTilesEventsHandler(roundsMaster,
				collisionVisitorsManagerFactory);

		tilesMaster.addClient(roundsMaster);

	}

	@Override
	public void initRoundsMaster(final MapsLoader mapsLoader) {
		roundsMaster = new RoundsMaster(mapsLoader);
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

		final PhaseHeroes phaseHeroes = new PhaseHeroes(heroesMaster,
				ordersMaster, tilesMaster, heroesToolsMaster, gatherCubesMaster);
		phaseHeroes.setRoundsMaster(roundsMaster);

		final PhaseEnemies phaseEnemies = new PhaseEnemies(enemiesMaster,
				heroesMaster, ordersMaster);
		phaseEnemies.setRoundsMaster(roundsMaster);

		roundsMaster.addPhase(phaseHeroes);
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
	public void mapsLoaderReloadWorld() {
		mapsLoader.reloadWorld();
	}

	@Override
	public void update(final float deltaTime) {
		pickMaster.update();
	}

	@Override
	public void initCoordCalc(final float tileSize) {
		coordCalc = new CoordCalcDefault(tileSize);
	}

	@Override
	public CoordCalc getCoordCalc() {
		return coordCalc;
	}

}