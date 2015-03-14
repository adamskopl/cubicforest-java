package org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.adamsko.cubicforest.players.NullPlayer;
import org.adamsko.cubicforest.players.Player;
import org.adamsko.cubicforest.players.PlayerActionsHandler;
import org.adamsko.cubicforest.roundsMaster.NullPlayerActionsHandlerPhase;
import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.object.NullCubicObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectState;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrdersMasterClient;
import org.adamsko.cubicforest.world.tilePathsMaster.NullTilePath;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

import com.badlogic.gdx.Gdx;

public abstract class PhaseOrderableObjectsDefault implements
		PhaseOrderableObjects, OrdersMasterClient {

	private final String name;
	private final List<WorldObject> phaseObjects;
	private OrderableObjectsContainer objectsContainer;
	private Player activePlayer;
	/**
	 * Indicates if phase was skipped without doing anything
	 */
	private boolean phaseSkippedLastTime;

	/**
	 * Is any of the phaseObjects objects executing an order?
	 */
	private boolean orderInProgress;

	/**
	 * Active path created by picking order valid Tile.
	 */
	private TilePath activePath;

	protected RoundsMaster roundsMaster;
	protected OrdersMaster ordersMaster;
	protected PlayerActionsHandler playerActionsHandler;

	/**
	 * Current object's position on the list.
	 */
	protected int currentObjectPointer;

	@Override
	public void phaseIsOver(final RoundPhase phaseOver) {
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

		phaseObjects.clear();
		for (final WorldObject wo : objectsContainer.getOrderableObjects()) {
			phaseObjects.add(wo);
		}
	}

	@Override
	public void reloadPhase(
			final OrderableObjectsContainer newOrderableObjectsContainer) {

		objectsContainer = newOrderableObjectsContainer;
		reloadPhase();
	}

	@Override
	public boolean phaseSkippedLastTime() {
		return phaseSkippedLastTime;
	}

	@Override
	public void setActivePlayer(final Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	@Override
	public Player getActivePlayer() {
		return this.activePlayer;
	}

	@Override
	public void nextObject() {
		setPhaseSkippedLastTime(false);
		if (noObjects()) {
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

	/**
	 * @return WorldObject pointed by currentObjectPointer.
	 */
	@Override
	public WorldObject getCurrentObject() {
		if (phaseObjects.size() == 0) {
			return NullCubicObject.instance();
		}
		final WorldObject currentObject = phaseObjects
				.get(currentObjectPointer);
		return currentObject;
	}

	@Override
	public int getObjectsNumber() {
		return phaseObjects.size();
	}

	@Override
	public boolean noObjects() {
		if (getObjectsNumber() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getCurrentObjectIndex() {
		return currentObjectPointer;
	}

	@Override
	public void setCurrentObjectIndex(final int newIndex) {
		this.currentObjectPointer = newIndex;
	}

	@Override
	public PlayerActionsHandler getPlayerActionsHandler() {
		return playerActionsHandler;
	}

	protected PhaseOrderableObjectsDefault(
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
		this.orderInProgress = false;
		this.activePath = NullTilePath.instance();

		phaseObjects = new ArrayList<WorldObject>();
		activePlayer = NullPlayer.instance();
		playerActionsHandler = NullPlayerActionsHandlerPhase.instance();

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

	protected Boolean isCurrentObjectLast() {
		if (currentObjectPointer + 1 == phaseObjects.size()) {
			return true;
		}
		return false;
	}

	/**
	 * Return information if there are no phase objects.
	 */
	protected boolean noPhaseObjects() {
		return (phaseObjects.size() == 0);
	}

	/**
	 * Set value for {@link #phaseSkippedLastTime()}
	 */
	private void setPhaseSkippedLastTime(final boolean phaseSkippedLastTime) {
		this.phaseSkippedLastTime = phaseSkippedLastTime;
	}

	@Override
	public boolean isOrderInProgress() {
		return orderInProgress;
	}

	@Override
	public void orderStarted() {
		orderInProgress = true;
	}

	@Override
	public void orderFinished() {
		orderInProgress = false;
	}

	@Override
	public void setTilePathActive(final TilePath activePath) {
		this.activePath = activePath;
	}

	@Override
	public TilePath getTilePathActive() {
		return activePath;
	}

	@Override
	public void resetTilePathActive() {
		this.activePath = NullTilePath.instance();
	}

}
