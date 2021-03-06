package org.adamsko.cubicforest.world.objectsMasters;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMementoDefault;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMementoState;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMemento;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.render.world.object.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.NullCollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.terrain.TerrainMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault;

import com.badlogic.gdx.Gdx;

public class WorldObjectsMastersContainerDefault implements
		WorldObjectsMastersContainer {

	private final List<WorldObjectsMaster> worldObjectsMasters;

	private CollisionVisitorsManagerFactory collisionVisitorsManagerFactory;

	private TexturesManager texturesManager;

	private GameRenderer gameRenderer;

	private TilesMaster tilesMaster;
	private TerrainMaster terrainObjectsMaster;
	private HeroesMaster heroesMaster;
	private EnemiesMaster enemiesMaster;
	private GatherCubesMaster gatherCubesMaster;
	private HeroesToolsMaster heroesToolsMaster;
	private PortalsMaster portalsMaster;
	private PrizesMaster prizesMaster;

	public WorldObjectsMastersContainerDefault() {
		worldObjectsMasters = null;
	}

	public WorldObjectsMastersContainerDefault(final GameRenderer renderer,
			final TexturesManager texturesManager) {
		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		this.gameRenderer = renderer;
		this.texturesManager = texturesManager;
		initMasters();
	}

	@Override
	public void initMasters() {
		initTilesMaster();

		this.collisionVisitorsManagerFactory = NullCollisionVisitorsManagerFactory
				.instance();

		terrainObjectsMaster = new TerrainMaster(tilesMaster);
		terrainObjectsMaster.setTexturesManager(texturesManager);

		// texturesManager.loadTextures(WorldObjectType.TREE);
		heroesMaster = new HeroesMaster(tilesMaster);
		heroesMaster.setTexturesManager(texturesManager);
		// texturesManager.loadTextures(WorldObjectType.HERO);
		enemiesMaster = new EnemiesMaster(tilesMaster);
		enemiesMaster.setTexturesManager(texturesManager);
		// texturesManager.loadTextures(WorldObjectType.ENEMY);
		gatherCubesMaster = new GatherCubesMasterDefault(tilesMaster,
				"cubes-atlas-medium", 25, 40);
		gatherCubesMaster.setTexturesManager(texturesManager);
		// texturesManager.loadTextures(WorldObjectType.GATHERCUBE);

		heroesToolsMaster = new HeroesToolsMasterDefault(tilesMaster,
				gatherCubesMaster, heroesMaster);
		heroesToolsMaster.setTexturesManager(texturesManager);

		portalsMaster = new PortalsMaster(tilesMaster, "portals-atlas-medium",
				45, 25, tilesMaster.getTilesContainer());
		portalsMaster.setTexturesManager(texturesManager);
		// texturesManager.loadTextures(WorldObjectType.PORTAL);

		prizesMaster = new PrizesMasterDefault(tilesMaster);
		prizesMaster.setTexturesManager(texturesManager);
		// texturesManager.loadTextures(WorldObjectType.PRIZE);

		// tiles container has to be added first, because objects are
		// removed/added to tiles
		worldObjectsMasters.add(tilesMaster.getTilesContainer());

		gameRenderer.addROMWorld(tilesMaster.getTilesContainer());
		worldObjectsMasters.add(terrainObjectsMaster);
		gameRenderer.addROMWorld(terrainObjectsMaster);
		worldObjectsMasters.add(heroesMaster);
		gameRenderer.addROMWorld(heroesMaster);
		worldObjectsMasters.add(enemiesMaster);
		gameRenderer.addROMWorld(enemiesMaster);
		worldObjectsMasters.add(gatherCubesMaster);
		gameRenderer.addROMWorld(gatherCubesMaster);

		worldObjectsMasters.add(heroesToolsMaster);
		gameRenderer.addROMWorld(heroesToolsMaster);
		heroesToolsMaster.initToolsMasters(this, tilesMaster);

		worldObjectsMasters.add(portalsMaster);
		gameRenderer.addROMWorld(portalsMaster);
		worldObjectsMasters.add(prizesMaster);
		gameRenderer.addROMWorld(prizesMaster);

		gameRenderer.addROMGui(gatherCubesMaster.getGatherCubesCounter());
		gameRenderer.addROMGui(prizesMaster.getGuiPrizes());
	}

	@Override
	public void addWorldObjectsMaster(
			final WorldObjectsMaster worldObjectsMaster) {
		worldObjectsMasters.add(worldObjectsMaster);
	}

	@Override
	public void addRenderableObjectsMaster(
			final RenderableObjectsMaster renderableObjectsMaster) {
		gameRenderer.addROMWorld(renderableObjectsMaster);
	};

	@Override
	public boolean allMastersInitialized() {
		for (final WorldObjectsMaster worldObjectsMaster : worldObjectsMasters) {
			if (worldObjectsMaster.isNull()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void setCollisionVisitorsManagerFactory(
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory) {
		this.collisionVisitorsManagerFactory = collisionVisitorsManagerFactory;
	}

	@Override
	public void initCollisionVisitorsManagers() {
		for (final WorldObjectsMaster worldObjectsMaster : worldObjectsMasters) {
			worldObjectsMaster
					.initCollisionVisitorsManagers(collisionVisitorsManagerFactory);
		}
	}

	@Override
	public List<WorldObjectsMaster> getWorldObjectsMasters() {
		return worldObjectsMasters;
	}

	@Override
	public void loadMasters(final CFMap cfMap) {

		if (collisionVisitorsManagerFactory.isNull()) {
			Gdx.app.error("WorldObjectsMastersContainerDefault::loadMasters()",
					"collisionVisitorsManagerFactory.isNull()");
			return;
		}

		for (final WorldObjectsMaster master : worldObjectsMasters) {
			try {
				master.loadMapObjects(cfMap);

			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		initCollisionVisitorsManagers();

	}

	private void loadMasters(final WMContainerMemento memento) {

		if (collisionVisitorsManagerFactory.isNull()) {
			Gdx.app.error("WorldObjectsMastersContainerDefault::loadMasters()",
					"collisionVisitorsManagerFactory.isNull()");
			return;
		}

		final WMContainerMementoState state = memento.getState();

		final List<WOMMemento> mementos = state.getWOMMementos();

		if (mementos.size() != getWorldObjectsMasters().size()) {
			Gdx.app.error("WorldObjectsMastersContainerDefault::setMemento()",
					"mementos.size() != getWorldObjectsMasters().size()");
		}
		int mementoIndex = 0;
		for (final WorldObjectsMaster worldObjectsMaster : worldObjectsMasters) {
			worldObjectsMaster.setMemento(mementos.get(mementoIndex));
			worldObjectsMaster
					.initCollisionVisitorsManagers(collisionVisitorsManagerFactory);
			mementoIndex++;
		}
	}

	@Override
	public void setMemento(final WMContainerMemento memento) {

		unloadMasters();

		loadMasters(memento);

	}

	@Override
	public WMContainerMemento createMemento() {
		final WMContainerMementoState state = new WMContainerMementoState(this);
		final WMContainerMemento memento = new WMContainerMementoDefault();
		memento.setState(state);
		return memento;
	}

	@Override
	public EnemiesMaster getEnemiesMaster() {
		return enemiesMaster;
	}

	@Override
	public GatherCubesMaster getGatherCubesMaster() {
		return gatherCubesMaster;
	}

	@Override
	public HeroesMaster getHeroesMaster() {
		return heroesMaster;
	}

	@Override
	public HeroesToolsMaster getHeroesToolsMaster() {
		return heroesToolsMaster;
	}

	@Override
	public TilesMaster getTilesMaster() {
		return tilesMaster;
	}

	@Override
	public PortalsMaster getPortalsMaster() {
		return portalsMaster;
	}

	@Override
	public PrizesMaster getPrizesMaster() {
		return prizesMaster;
	}

	private void initTilesMaster() {
		tilesMaster = new TilesMasterDefault(100, texturesManager);
	}

	@Override
	public void unloadMasters() {
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

	}

}
