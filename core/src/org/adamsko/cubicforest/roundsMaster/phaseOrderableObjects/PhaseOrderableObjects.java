package org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects;

import org.adamsko.cubicforest.roundsMaster.RoundPhase;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroesOrdersMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

public interface PhaseOrderableObjects extends RoundPhase {

	void reloadPhase(
			final OrderableObjectsContainer newOrderableObjectsContainer);

	public void nextObject();

	WorldObject getCurrentObject();

	int getObjectsNumber();

	/**
	 * Check if there are any phase objects.
	 * 
	 * @return true if there is at least one object
	 */
	boolean noObjects();

	int getCurrentObjectIndex();

	void setCurrentObjectIndex(final int newIndex);

	void orderStarted();

	void orderFinished();

	/**
	 * Is order issued for one of the phase's objects in progress?
	 */
	boolean isOrderInProgress();

	/**
	 * Search for a {@link TilePath} for a given object.
	 */
	TilePath searchTilePath(final WorldObject phaseObject, final Tile tile);

	/**
	 * Check if given TilePath is a valid order path for given object.
	 */
	boolean isPathOrderValidObject(final WorldObject phaseObject,
			final Tile tile, final TilePath pathToTile);

	/**
	 * {@link PhaseHeroesOrdersMaster#highlightTilesOrder(Tile, Boolean)}
	 */
	void highlightTilesOrder(final Tile tilePickedOrder,
			final Boolean tileOrderValid);

	/**
	 * If a valid {@link TilePath} is chosen, set it as an active on. Object
	 * will follow it, when order is accepted.
	 */
	void setTilePathActive(TilePath activePath);

	/**
	 * Get {@link TilePath} which is set as an active one. It it's not null,
	 * order can be issued.
	 */
	TilePath getTilePathActive();

	/**
	 * Set active tile path to null.
	 */
	void resetTilePathActive();

	/**
	 * Issue order for given phase object and for given TilePath.
	 */
	void issueOrder(final WorldObject phaseObject, final TilePath tilePath);

}
