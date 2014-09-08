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
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;

import com.badlogic.gdx.Gdx;

public abstract class PhaseOrderableObjects implements RoundPhase,
		OrdersMasterClient {

	private final String name;
	protected RoundsMaster roundsMaster;
	protected OrdersMaster ordersMaster;
	protected TilesLookController tilesLookController;

	private final List<WorldObject> phaseObjects;
	private final OrderableObjectsContainer objectsContainer;

	/**
	 * Indicates if phase was skipped without doing anything
	 */
	private boolean phaseSkippedLastTime;

	/**
	 * Current object's position on the list.
	 */
	protected int currentObjectPointer;

	protected PhaseOrderableObjects(
			final OrderableObjectsContainer orderableObjectsContainer,
			final OrdersMaster ordersMaster,
			final TilesLookController tilesLookController, final String name) {

		if (orderableObjectsContainer.isNull()) {
			Gdx.app.error("PhaseOrderableObjects()",
					"orderableObjectsContainer.isNull()");
		}

		this.objectsContainer = orderableObjectsContainer;
		this.ordersMaster = ordersMaster;
		this.tilesLookController = tilesLookController;
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
				currentObjectPointer--;
			}
		}
	}

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

		currentObjectPointer++;
		// check if previous object was the last one
		if (currentObjectPointer == phaseObjects.size()) {
			currentObjectPointer = 0;
		}
	}

	protected Boolean isCurrentObjectLast() {
		if (currentObjectPointer + 1 == phaseObjects.size()) {
			return true;
		}
		return false;
	}

	/**
	 * @return WorldObject pointed by currentObjectPointer.
	 */
	protected WorldObject currentObject() {
		if (phaseObjects.size() == 0) {
			return null;
		}
		final WorldObject currentObject = phaseObjects.get(currentObjectPointer);
		return currentObject;
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
		currentObjectPointer = -1;

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