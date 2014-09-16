package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.gui.GuiMaster;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalc;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.handler.GameResultOperationHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.pickmaster.PickMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMaster;

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
	 * Initialize {@link CoordCalc} for calculating screen/tiles coordinations.
	 * 
	 * @param tileSize
	 *            tile's size in pixels (see {@link CoordCalc})
	 */
	void initCoordCalc(final float tileSize);

	/**
	 * Get ready {@link CoordCalc} object.
	 */
	CoordCalc getCoordCalc();

	/**
	 * Initialize {@link WorldObjectsMastersContainer}, so it's ready for
	 * 'getWorldObjectsMastersContainer()'. All {@link WorldObjectsMaster}
	 * objects should be ready for use.
	 */
	void initWorldObjectsMastersContainer(final GameRenderer renderer);

	/**
	 * Initialize {@link TilePathSearchersMaster}
	 */
	void initTilePathSearchersMaster(final TilesMaster tilesMaster);

	TilePathSearchersMaster getTilePathSearchersMaster();

	/**
	 * Initialize {@link OrdersMaster} object, so it's ready for
	 * 'getOrdersMaster()'.
	 * 
	 * @param tilesMaster
	 *            Needed to initialize 'TilePathsMaster'. Also for some public
	 *            methods.
	 */
	void initOrdersMaster(final TilesMaster tilesMaster);

	/**
	 * Initialize {@link GuiMaster} object. Its elements are loaded in
	 * {@link #initGuiMasterElements(GameRenderer, MapsLoader, GatherCubesMaster, RoundsMaster)}
	 */
	void initGuiMaster();

	/**
	 * Initialize {@link GuiMaster} object. Initialize its clients.
	 * 
	 * @param guiMaster
	 *            {@link GuiMaster} object to be initialized
	 * @param renderer
	 *            {@link GuiMaster} is adding to it its
	 *            {@link RenderableObjectsMaster}
	 * @param mapsLoader
	 *            needed for GUI responsible for changing levels (number of
	 *            levels and other informations needed)
	 * @param gatherCubesMaster
	 *            needed for GUI showing informations about player's cubes
	 * @param prizesMaster
	 *            needed for GUI showint informations about collected prizes
	 * @param roundsMaster
	 *            {@link RoundsMaster} is passing events from {@link GuiMaster}
	 *            to {@link RoundPhase} objects
	 */
	void initGuiMasterContainers(GuiMaster guiMaster, GameRenderer renderer,
			MapsLoader mapsLoader, GatherCubesMaster gatherCubesMaster,
			PrizesMaster prizesMaster, RoundsMaster roundsMaster);

	/**
	 * Initialize {@link PickMaster} object. Add clients.
	 * 
	 * @param guiMaster
	 *            client receiving input informations from {@link GuiMaster}
	 * @param tilesMaster
	 *            client receiving input informations from {@link GuiMaster}
	 * @param coordCalc
	 *            {@link CoordCalc} object for coordinates calculations in
	 *            {@link PickMaster}
	 */
	void initPickMaster(GuiMaster guiMaster, TilesMaster tilesMaster,
			CoordCalc coordCalc);

	/**
	 * Initialize {@link MapsLoader} to load game objects from
	 * {@link WorldObjectsMaster} objects.
	 * 
	 * @param worldObjectsMastersContainer
	 *            needed for {@link MapsLoader} to receive
	 *            {@link WorldObjectsMaster} objects needed during maps reload
	 *            (reading all {@link WorldObject}
	 * @param collisionVisitorsManagerFactory
	 *            needed to initialize {@link CollisionVisitorsManager} of
	 *            loaded {@link WorldObject} objects during map reload
	 */
	void initMapsLoader(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			GuiMaster guiMaster,
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	/**
	 * Reload world objects with {@link MapsLoader} (invoking reloadWorld())
	 */
	void mapsLoaderReloadWorld();

	/**
	 * Initialize {@link RoundsMaster}.
	 * 
	 * @param mapsLoader
	 *            used when reloading {@link RoundsMaster}. Before reloading
	 *            phases, world objects should be reloaded first.
	 * @param worldObjectsMastersContainer
	 *            used for creating {@link GametMemento} by {@link RoundsMaster}
	 */
	void initRoundsMaster(MapsLoader mapsLoader,
			WorldObjectsMastersContainer worldObjectsMastersContainer);

	/**
	 * Initialize {@link RoundsMaster} {@link RoundPhase} objects.
	 * 
	 * @param ordersMaster
	 *            used in initialized {@link RoundPhase} objects
	 * @param worldObjectsMastersContainer
	 *            used in {@link RoundPhase} objects to access needed
	 *            {@link WorldObject} objects.
	 * @param tilesMaster
	 *            used in some phases for tiles searching
	 * @param tilePathSearchersMaster
	 *            used in initialized phases for path searching
	 * @param tilesLookController
	 *            used in phases to change tiles look
	 */
	void initRoundsMasterPhases(final OrdersMaster ordersMaster,
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			TilesMaster tilesMaster,
			TilePathSearchersMaster tilePathSearchersMaster,
			final TilesLookController tilesLookController);

	/**
	 * Initialize {@link TilesMaster} parts that are needing
	 * {@link RoundsMaster}.
	 * 
	 * @param tilesMaster
	 *            initialized {@link TilesMaster}
	 * @param roundsMaster
	 *            {@link RoundsMaster} needed for complete {@link TilesMaster}
	 *            initialization ( {@link GameResultOperationHandler} object)
	 * @param collisionVisitorsManagerFactory
	 *            needs {@link CollisionsHandler} to being set, and its
	 *            initialization is in {@link TilesEventsHandler}, that belongs
	 *            to {@link TilesMaster}
	 */
	void initTilesMasterRoundsMaster(TilesMaster tilesMaster,
			final RoundsMaster roundsMaster,
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	/**
	 * Initialize {@link CollisionVisitorsManagerFactory}
	 * 
	 * @param gatherCubesMaster
	 *            needed for some {@link WorldObjectVisitor} created by
	 *            {@link CollisionVisitorsManagerFactory}
	 * @param heroesToolsMaster
	 *            needed for some {@link WorldObjectVisitor} created by
	 *            {@link CollisionVisitorsManagerFactory}
	 * @param portalsMaster
	 *            needed for some {@link WorldObjectVisitor} created by
	 *            {@link CollisionVisitorsManagerFactory}
	 * @param prizesMaster
	 *            needed for some {@link WorldObjectVisitor} created by
	 *            {@link CollisionVisitorsManagerFactory}
	 */
	void initCollisionVisitorsManagerFactory(
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final PortalsMaster portalsMaster, final PrizesMaster prizesMaster);

	/**
	 * Get {@link WorldObjectsMastersContainer} needed for
	 * {@link GameWorldBuilder} functions.
	 */
	WorldObjectsMastersContainer getWorldObjectsMastersContainer();

	/**
	 * Get {@link OrdersMaster} needed for {@link GameWorldBuilder} functions.
	 */
	OrdersMaster getOrdersMaster();

	/**
	 * Get {@link GuiMaster} needed for {@link GameWorldBuilder} functions.
	 */
	GuiMaster getGuiMaster();

	/**
	 * Get {@link MapsLoader} needed for {@link GameWorldBuilder} functions.
	 */
	MapsLoader getMapsLoader();

	/**
	 * Get {@link RoundsMaster} needed for {@link GameWorldBuilder} functions.
	 */
	RoundsMaster getRoundsMaster();

	/**
	 * Get {@link CollisionVisitorsManagerFactory} needed for
	 * {@link GameWorldBuilder} functions.
	 */
	CollisionVisitorsManagerFactory getCollisionVisitorsManagerFactory();

}
