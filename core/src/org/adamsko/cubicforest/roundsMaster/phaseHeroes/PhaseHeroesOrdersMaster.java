package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.mapsResolver.OrderDecisionDefault;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Handles actions associated with giving commands to {@link Hero} objects
 * during {@link PhaseHeroes}.
 */
public interface PhaseHeroesOrdersMaster {

	void changePhaseHeroesMode(final PhaseHeroesMode newMode);

	/**
	 * Same as {@link #changePhaseHeroesMode(PhaseHeroesMode)}, but also set
	 * {@link HeroTool} marker type
	 * 
	 * @param newMode
	 * @param heroToolType
	 * @throws Exception
	 */
	void changePhaseHeroesMode(final PhaseHeroesMode newMode,
			final WorldObjectType heroToolType);

	/**
	 * Handle tile picked in {@link PhaseHeroes} phase.
	 * 
	 * @param tilePickedOrder
	 *            tile picked for an order issue
	 * 
	 * @param tileOrderValid
	 *            is picked tile is valid for an order issue
	 */
	void tilePicked(final Tile tilePickedOrder, final Boolean tileOrderValid);

	/**
	 * Helper needs to know which hero is active right now.
	 * 
	 * @param currentHero
	 *            current hero in {@link PhaseHeroes}
	 */
	void setCurrentHero(WorldObject currentHero);

	/**
	 * Returns {@link OrderDecisionDefault} objects defining what can be made in
	 * current {@link PhaseHeroes} state for current hero.
	 */
	List<OrderDecisionDefault> getCurrentPossbileDecisions();

}
