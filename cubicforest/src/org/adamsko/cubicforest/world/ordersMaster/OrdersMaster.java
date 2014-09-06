package org.adamsko.cubicforest.world.ordersMaster;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.roundsMaster.phaseOrderableObjects.PhaseOrderableObjects;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePath;

/**
 * Receives and manages orders (movement for now) for objects from different
 * {@link PhaseOrderableObjects} client objects. Handles only one client for
 * now.
 * 
 * TODO Can be easily upgrade to any number of clients.
 * 
 * @author adamsko
 * 
 */
public interface OrdersMaster extends Nullable {

	/**
	 * Invoked when path is finished. Information should be passed to interested
	 * client passed in
	 * {@link #startOrder(WorldObject, TilePath, OrdersMasterClient)}.
	 */
	void onPathFinished();

	/**
	 * @param wanderer
	 *            object which will follow given path
	 * @param path
	 *            path which wanderer will follow
	 * @param client
	 *            client to be informed about the order's result
	 */
	void startOrder(final WorldObject wanderer, final TilePath path,
			final OrdersMasterClient client);

}
