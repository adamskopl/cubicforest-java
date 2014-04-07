package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

/**
 * Enum describing mode of the PhaseHeroes
 * 
 * @author adamsko
 *
 */
public enum PhaseHeroesMode_e {
	/**
	 * tile is picked for a move order 
	 */
	MODE_CHOICE_MOVEMENT,
	/**
	 * tile is picked for a build tool order
	 */
	MODE_CHOICE_TOOL,
	/**
	 * choses order is being executed
	 */
	MODE_ORDER_EXECUTION
}
