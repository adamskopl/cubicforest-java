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
	 * @param deltaTime
	 */
	void update(final float deltaTime);

	void initWorldObjectsMastersContainer(final GameRenderer renderer,
			final RoundsMaster roundsMaster);

	WorldObjectsMastersContainer getWorldObjectsMastersContainer();

	void initGuiMaster(GameRenderer renderer, TilesMaster tilesMaster,
			MapsLoader mapsLoader, GatherCubesMaster gatherCubesMaster,
			RoundsMaster roundsMaster);

	GuiMaster getGuiMaster();

	void initPickMaster(GuiMaster guiMaster, RoundsMaster roundsMaster);

	void initOrdersMaster(final TilesMaster tilesMaster);

	OrdersMaster getOrdersMaster();

	/**
	 * Initialize {@link MapsLoader} to load game objects from
	 * {@link WorldObjectsMaster} objects.
	 */
	void initMapsLoader();

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
	void mapsLoaderReloadWorld(
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	/**
	 * FIXME: pass {@link WorldObjectsMaster} manager
	 */
	void initRoundsMaster();

	void initRoundsMasterPhases(final OrdersMaster ordersMaster,
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	void initRoundsMasterCVMFactory(
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	RoundsMaster getRoundsMaster();

	void initTilesMasterRoundsMaster(final RoundsMaster roundsMaster,
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	void initCollisionVisitorsManagerFactory(
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster);

	CollisionVisitorsManagerFactory getCollisionVisitorsManagerFactory();

}
