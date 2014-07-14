package org.adamsko.cubicforest.roundsMaster.phaseEnemies;

import java.util.List;
import java.util.Vector;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathSearcher;
import org.adamsko.cubicforest.world.tilesMaster.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Manages Heroes for PhaseEnemies. All operations connected with Heroes.
 * 
 * @author adamsko
 * 
 */
public class PhaseEnemiesHeroesHelper {

	/**
	 * List of {@link WorldObject} objects (heroes) which will be chased by
	 * enemies.
	 */
	private List<WorldObject> heroes;
	/**
	 * How many heroes are at the start of the level?
	 */
	private int startHeroesNumber;

	public PhaseEnemiesHeroesHelper(OrderableObjectsContainer heroesContainer) {
		heroes = heroesContainer.getOrderableObjects();
		startHeroesNumber = heroes.size();
	}

	public boolean gameOver() {
		return heroes.size() != startHeroesNumber;
	}
	
	/**
	 * Search for the shortest path from given enemy to any hero.
	 * 
	 * @param enemy
	 *            enemy for which shortest path is searched
	 * @return founded {@link TilePath} object
	 */
	public TilePath searchPathShortestHero(WorldObject enemy) {

		TilePath shortestPath = null;
		/*
		 * For every hero: get adjacent tiles, search paths for every tile. Pick
		 * shortest from all paths leading to adjacent tiles of every hero.
		 */
		for (WorldObject hero : heroes) {

//			TilePath pathToHero = TilePathSearcher
//					.searchShortestPathAdjacentTiles(enemy, hero);
			TilePath pathToHero = TilePathSearcher.search(enemy, hero);

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
