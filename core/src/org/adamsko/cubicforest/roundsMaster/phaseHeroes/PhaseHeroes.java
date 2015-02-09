package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;

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

	public PhaseHeroesOrdersMaster getOrdersMaster();

	HeroesToolsMaster getToolsMaster();

	/**
	 * Check if game is victorious. <br>
	 * Victory conditions: there are no heroes in the heroes phase.
	 */
	boolean victoryConditionsMet();

	/**
	 * Check if there are enough cubes to build given {@link HeroTool} type
	 */
	boolean isHeroToolAffordable(final WorldObjectType heroToolType);

}
