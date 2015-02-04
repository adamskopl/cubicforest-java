package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Interface for phase where Player is making decisions.
 * 
 * @author adamsko
 * 
 */
public interface PhaseHeroes extends PhaseOrderableObjects {

	/**
	 * Set next Hero as an active one.
	 */
	public void nextHero();

	/**
	 * {@link PhaseHeroesOrdersMaster#getCurrentPossbileDecisions()}
	 */
	List<OrderDecisionDefault> getCurrentPossbileDecisions();

	/**
	 * Check if game is victorious. <br>
	 * Victory conditions: there are no heroes in the heroes phase.
	 */
	boolean victoryConditionsMet();

	/**
	 * Has player chosen a tool for an order?
	 */
	boolean isHeroToolChosen();

	/**
	 * Check if there are enough cubes to build given {@link HeroTool} type
	 */
	boolean isHeroToolAffordable(final WorldObjectType heroToolType);

	/**
	 * Add {@link HeroTool} to the next order decision.
	 */
	void chooseHeroTool(WorldObjectType heroToolType);

	/**
	 * Insert HeroTool marker (tool building intention marker).
	 * 
	 * @param tileTool
	 *            tile to which marker should be added
	 */
	void addHeroToolMarker(final Tile tileTool);

	/**
	 * Remove HeroTool marker (tool building intention marker).
	 */
	void removeHeroToolMarker();

}
