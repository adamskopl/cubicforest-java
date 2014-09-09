package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.searcher.TilePathSearchersMaster;

/**
 * Manages Heroes for PhaseEnemies. All operations connected with Heroes.
 * 
 * @author adamsko
 * 
 */
public class HeroesHelper {

	/**
	 * List of {@link WorldObject} objects (heroes) which will be chased by
	 * enemies.
	 */
	private final List<WorldObject> heroes;

	private final TilePathSearchersMaster tilePathSearchersMaster;

	public HeroesHelper(final OrderableObjectsContainer heroesContainer,
			final TilePathSearchersMaster tilePathSearchersMaster) {
		heroes = heroesContainer.getOrderableObjects();
		this.tilePathSearchersMaster = tilePathSearchersMaster;
	}

	/**
	 * Search for the shortest path from given enemy to any hero.
	 * 
	 * @param enemy
	 *            enemy for which shortest path is searched
	 * @return founded {@link TilePath} object
	 */
	public TilePath searchPathShortestHero(final WorldObject enemy) {

		TilePath shortestPath = null;
		/*
		 * For every hero: get adjacent tiles, search paths for every tile. Pick
		 * shortest from all paths leading to adjacent tiles of every hero.
		 */
		for (final WorldObject hero : heroes) {

			final TilePath pathToHero = tilePathSearchersMaster
					.getTilePathSearcherValidPath().search(enemy, hero);

			if (pathToHero == null || pathToHero.length() == 0) {
				continue;
			}

			if (shortestPath == null) {
				shortestPath = pathToHero;
			}
			if (pathToHero.length() < shortestPath.length()) {
				shortestPath = pathToHero;
			}
		}
		return shortestPath;
	}
}
