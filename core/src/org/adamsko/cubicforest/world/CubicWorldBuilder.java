package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.gui.GuiMasterDefault;
import org.adamsko.cubicforest.gui.NullGuiMasterDefault;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalc;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalcDefault;
import org.adamsko.cubicforest.roundsMaster.NullRoundsMaster;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.RoundsMasterDefault;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.tiled.MapsLoaderTiled;
import org.adamsko.cubicforest.world.mapsLoader.tiled.NullMapsLoaderTiled;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.NullCollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainerDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;
import org.adamsko.cubicforest.world.ordersMaster.NullOrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterDefault;
import org.adamsko.cubicforest.world.pickmaster.NullPickMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMaster;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMasterDefault;

import com.badlogic.gdx.Gdx;

public class CubicWorldBuilder implements GameWorldBuilder, Nullable {

	private CoordCalc coordCalc;

	private WorldObjectsMastersContainer worldObjectsMastersContainer;

	private TilePathSearchersMaster tilePathSearchersMaster;
	// private TilePathSearcher tilePathSearcher;

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
		guiMaster = NullGuiMasterDefault.instance();
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
	public void initGuiMaster() {
		guiMaster = new GuiMasterDefault();
	}

	@Override
	public void initGuiMasterContainers(final GuiMaster guiMaster,
			final GameRenderer renderer, final MapsLoader mapsLoader,
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

		guiMaster.initializeContainers(mapsLoader);

		guiMaster.addGui(gatherCubesMaster.getGatherCubesCounter());
		guiMaster.addClient(roundsMaster);

		for (final GuiElementsContainer GC : guiMaster.getGuiList()) {
			renderer.addROMGui(GC);
		}

		guiMaster.reload(mapsLoader.getMapActive());

	}

	@Override
	public GuiMaster getGuiMaster() {
		return guiMaster;
	};

	@Override
	public void initMapsLoader(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final GuiMaster guiMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {

		mapsLoader = new MapsLoaderTiled(worldObjectsMastersContainer,
				guiMaster, collisionVisitorsManagerFactory);
		mapsLoader.loadMaps();
		mapsLoader.setMapActive(0);
	}

	@Override
	public MapsLoader getMapsLoader() {
		return mapsLoader;
	}

	@Override
	public void initOrdersMaster(final TilesMaster tilesMaster) {
		ordersMaster = new OrdersMasterDefault(tilesMaster);
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

		tilesMaster.addPickClient(roundsMaster);
	}

	@Override
	public void initRoundsMaster(final MapsLoader mapsLoader) {
		roundsMaster = new RoundsMasterDefault(mapsLoader);
	}

	@Override
	public void initRoundsMasterPhases(final OrdersMaster ordersMaster,
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController) {

		if (ordersMaster.isNull()) {
			Gdx.app.error("initRoundsMaster()", "ordersMaster.isNull()");
			return;
		}

		if (!worldObjectsMastersContainer.allMastersInitialized()) {
			Gdx.app.error("initRoundsMaster()",
					"!worldObjectsMastersContainer.allMastersInitialized()");
		}

		final PhaseHeroes phaseHeroes = new PhaseHeroes(
				worldObjectsMastersContainer, ordersMaster,
				tilePathSearchersMaster, tilesLookController);
		phaseHeroes.setRoundsMaster(roundsMaster);

		final PhaseEnemies phaseEnemies = new PhaseEnemies(
				worldObjectsMastersContainer, ordersMaster,
				tilePathSearchersMaster, tilesLookController);
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
			final HeroesToolsMaster heroesToolsMaster,
			final PortalsMaster portalsMaster, final PrizesMaster prizesMaster) {
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
				gatherCubesMaster, heroesToolsMaster, portalsMaster,
				prizesMaster);
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

	@Override
	public void initTilePathSearchersMaster(final TilesMaster tilesMaster) {
		tilePathSearchersMaster = new TilePathSearchersMasterDefault(
				tilesMaster);
	}

	@Override
	public TilePathSearchersMaster getTilePathSearchersMaster() {
		return tilePathSearchersMaster;
	};

}