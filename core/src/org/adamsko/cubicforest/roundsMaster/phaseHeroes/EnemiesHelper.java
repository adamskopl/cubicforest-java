package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;

public class EnemiesHelper {

	/**
	 * List of {@link WorldObject} enemies objects. References list from
	 * {@link OrderableObjectsContainer} (list can be changed by that
	 * container).
	 */
	private final List<WorldObject> enemies;

	public EnemiesHelper(final OrderableObjectsContainer enemiesContainer) {
		enemies = enemiesContainer.getOrderableObjects();
	}

	List<WorldObject> getEnemies() {
		return enemies;
	}

}
