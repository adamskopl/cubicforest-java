package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

/**
 * Enum describing mode of the PhaseHeroes
 * 
 * @author adamsko
 * 
 */
public enum PhaseHeroesMode {
	/**
	 * tile is picked for a move order
	 */
	MODE_CHOICE_MOVEMENT,
	/**
	 * tile is picked for a build tool order
	 */
	MODE_CHOICE_TOOL,
	/**
	 * chooses order is being executed
	 */
	MODE_ORDER_EXECUTION
}
