package org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResult;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterResult;

import com.badlogic.gdx.Gdx;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

public class OrdersMasterResultResolver {

	private HeroesMaster heroesMaster;
	private EnemiesMaster enemiesMaster;

	public OrdersMasterResultResolver(HeroesMaster heroesMaster,
			EnemiesMaster enemiesMaster) {

		this.heroesMaster = heroesMaster;
		this.enemiesMaster = enemiesMaster;

	}

	/**
	 * Remove object from it's master (objects list + render list) and from
	 * active phase)
	 * 
	 * @param object
	 */
	private void removeObject(WorldObject object,
			PhaseOrderableObjects activePhase) {

		activePhase.removeObject(object);
		
		try {
			if (heroesMaster.containsObject(object)) {
				heroesMaster.removeObject(object);
			} else if (enemiesMaster.containsObject(object)) {
				enemiesMaster.removeObject(object);
			}
		} catch (Exception ex) {
			Gdx.app.error("OrderMasterResultResolver::removeObject",
					ex.toString());
		}
	}

	/**
	 * @param ordersMasterResult
	 * @param activeObject
	 *            active object from acthive phase
	 * @param activePhase
	 */
	public void resolve(OrdersMasterResult ordersMasterResult,
			WorldObject activeObject, PhaseOrderableObjects activePhase) {

		InteractionResult interactionResult = ordersMasterResult
				.getInteractionResult();

		switch (interactionResult.getObjectOperation()) {
		case OBJECT_REMOVE:
			// remove object from its master and from activePhase
			removeObject(activeObject, activePhase);
			break;
		default:
			break;
		}

	}

}
