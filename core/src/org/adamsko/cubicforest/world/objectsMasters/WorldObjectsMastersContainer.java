package org.adamsko.cubicforest.world.objectsMasters;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;
import org.adamsko.cubicforest.world.objectsMasters.terrain.TerrainMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;

/**
 * Manages all {@link WorldObjectsMaster} objects used in a game.
 * 
 * @author adamsko
 * 
 */
public interface WorldObjectsMastersContainer {

	void initMasters();

	void addWorldObjectsMaster(WorldObjectsMaster worldObjectsMaster);

	void addRenderableObjectsMaster(
			RenderableObjectsMaster renderableObjectsMaster);

	/**
	 * For every {@link WorldObjectsMaster} initialize their
	 * {@link CollisionVisitorsManager} objects.
	 */
	void initCollisionVisitorsManagers();

	/**
	 * Check if all contained {@link WorldObjectsMaster} objects are initialized
	 * (not null)
	 * 
	 * @return true if all masters are not null
	 */
	boolean allMastersInitialized();

	void setCollisionVisitorsManagerFactory(
			CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	List<WorldObjectsMaster> getWorldObjectsMasters();

	void unloadMasters();

	void loadMasters(CFMap cfMap);

	void setMemento(WMContainerMemento memento);

	WMContainerMemento createMemento();

	TilesMaster getTilesMaster();

	TerrainMaster getTerrainMaster();

	HeroesMaster getHeroesMaster();

	EnemiesMaster getEnemiesMaster();

	GatherCubesMaster getGatherCubesMaster();

	HeroesToolsMaster getHeroesToolsMaster();

	PortalsMaster getPortalsMaster();

	PrizesMaster getPrizesMaster();

}
