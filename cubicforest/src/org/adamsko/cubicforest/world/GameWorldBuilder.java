package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;

/**
 * Interface for a class responsible for managing all game managers/masters.
 * Initializes game and its objects and letting other managers do the work.
 * 
 * @author adamsko
 * 
 */
public interface GameWorldBuilder {

	/**
	 * Standard 'update' function. Passes deltaTime for interested objects
	 * 
	 * @param deltaTime
	 */
	void update(final float deltaTime);

	/**
	 * Initialize {@link WorldObjectsMastersContainer}, so it's ready for
	 * 'getWorldObjectsMastersContainer()'. All {@link WorldObjectsMaster}
	 * objects should be ready for use.
	 * 
	 * @param renderer
	 * @param roundsMaster
	 */
	void initWorldObjectsMastersContainer(final GameRenderer renderer);

	WorldObjectsMastersContainer getWorldObjectsMastersContainer();

	/**
	 * Initialize {@link OrdersMaster} object, so it's ready for
	 * 'getOrdersMaster()'.
	 * 
	 * @param tilesMaster
	 *            Needed to initialize 'TilePathsMaster' Also needed for some
	 *            public methods.
	 */
	void initOrdersMaster(final TilesMaster tilesMaster);

	OrdersMaster getOrdersMaster();

	void initGuiMaster(GameRenderer renderer, TilesMaster tilesMaster,
			MapsLoader mapsLoader, GatherCubesMaster gatherCubesMaster,
			RoundsMaster roundsMaster);

	GuiMaster getGuiMaster();

	void initPickMaster(GuiMaster guiMaster, RoundsMaster roundsMaster,
			TilesMaster tilesMaster);

	/**
	 * Initialize {@link MapsLoader} to load game objects from
	 * {@link WorldObjectsMaster} objects.
	 */
	void initMapsLoader(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	MapsLoader getMapsLoader();

	/**
	 * FIXME: needef for setting active map in RoundsMaster. RoundsMaster has to
	 * do it other way
	 * 
	 * @param activeMapIndex
	 */
	void setMapActive(final int activeMapIndex);

	/**
	 * Reload world objects with {@link MapsLoader}.
	 */
	void mapsLoaderReloadWorld();

	/**
	 * FIXME: pass {@link WorldObjectsMaster} manager
	 */
	void initRoundsMaster(MapsLoader mapsLoader);

	void initRoundsMasterPhases(final OrdersMaster ordersMaster,
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	RoundsMaster getRoundsMaster();

	void initTilesMasterRoundsMaster(TilesMaster tilesMaster,
			final RoundsMaster roundsMaster,
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	void initCollisionVisitorsManagerFactory(
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster);

	CollisionVisitorsManagerFactory getCollisionVisitorsManagerFactory();

}
