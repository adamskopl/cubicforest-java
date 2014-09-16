package org.adamsko.cubicforest.world.objectsMasters;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.wmcontainer.WMContainerMemento;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
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

	void initMasters(GameRenderer gameRenderer);

	/**
	 * Check if all contained {@link WorldObjectsMaster} objects are initialized
	 * (not null)
	 * 
	 * @return true if all masters are not null
	 */
	boolean allMastersInitialized();

	List<WorldObjectsMaster> getWorldObjectsMasters();

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
