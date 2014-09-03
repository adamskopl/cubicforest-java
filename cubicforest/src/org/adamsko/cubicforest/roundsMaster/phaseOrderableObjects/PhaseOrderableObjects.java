package org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterClient;

import com.badlogic.gdx.Gdx;

public abstract class PhaseOrderableObjects implements RoundPhase,
		OrdersMasterClient {

	private final String name;
	protected RoundsMaster roundsMaster;
	protected OrdersMaster ordersMaster;

	private final List<WorldObject> phaseObjects;
	private final OrderableObjectsContainer objectsContainer;

	/**
	 * Indicates if phase was skipped without doing anything
	 */
	private boolean phaseSkippedLastTime;

	/**
	 * Active object's position on the list.
	 */
	protected int activeObjectPointer;

	protected PhaseOrderableObjects(
			final OrderableObjectsContainer orderableObjectsContainer,
			final OrdersMaster ordersMaster, final String name) {

		if (orderableObjectsContainer.isNull()) {
			Gdx.app.error("PhaseOrderableObjects()",
					"orderableObjectsContainer.isNull()");
		}

		this.objectsContainer = orderableObjectsContainer;
		this.ordersMaster = ordersMaster;
		this.name = name;

		this.phaseSkippedLastTime = false;

		phaseObjects = new ArrayList<WorldObject>();

		reloadPhase();
	}

	/**
	 * Remove every object that has {@link WorldObjectState#DEAD} state
	 */
	protected void removeDeadObjects() {
		final Iterator<WorldObject> iter = phaseObjects.iterator();
		while (iter.hasNext()) {
			if (iter.next().getState() == WorldObjectState.DEAD) {
				iter.remove();
				activeObjectPointer--;
			}
		}
	}

	/**
	 * @return decision if phase should be continued. E.g. if there are no more
	 *         objects to move, phase should be finished
	 */
	public void nextObject() {
		if (phaseObjects.size() == 0) {
			try {
				// phase is over, indicate that nothing happened during this
				// phase
				setPhaseSkippedLastTime(true);
				phaseIsOver(this);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		activeObjectPointer++;
		// check if previous object was the last one
		if (activeObjectPointer == phaseObjects.size()) {
			activeObjectPointer = 0;
		}
	}

	protected Boolean isActiveObjectLast() {
		if (activeObjectPointer + 1 == phaseObjects.size()) {
			return true;
		}
		return false;
	}

	/**
	 * @return WorldObject pointed by activeObjectPointer.
	 */
	protected WorldObject activeObject() {
		if (phaseObjects.size() == 0) {
			return null;
		}
		final WorldObject activeObject = phaseObjects.get(activeObjectPointer);
		return activeObject;
	}

	@Override
	public void phaseIsOver(final RoundPhase phaseOver) throws Exception {
		removeDeadObjects();
		roundsMaster.phaseIsOver(phaseOver);
	}

	@Override
	public void setRoundsMaster(final RoundsMaster roundsMaster) {
		this.roundsMaster = roundsMaster;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void reloadPhase() {
		activeObjectPointer = -1;

		if (objectsContainer.getOrderableObjects().size() == 0) {
			Gdx.app.error("reloadPhase()", "0");
		}

		phaseObjects.clear();
		for (final WorldObject wo : objectsContainer.getOrderableObjects()) {
			phaseObjects.add(wo);
		}
	}

	@Override
	public boolean phaseSkippedLastTime() {
		return phaseSkippedLastTime;
	}

	/**
	 * Set value for {@link #phaseSkippedLastTime()}
	 */
	private void setPhaseSkippedLastTime(final boolean phaseSkippedLastTime) {
		this.phaseSkippedLastTime = phaseSkippedLastTime;
	}
}
