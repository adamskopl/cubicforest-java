package org.adamsko.cubicforest.players.resolver;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionsAggregateContainer;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;

public interface MapsResolver extends OrderDecisionsAggregateContainer,
		Nullable {

	public RoundDecisionsIterator createRoundDecisionsIterator(
			final MapsResolverClient client);

	/**
	 * Start new level resolving process.
	 */
	public void startNewResolve();

	/**
	 * Invoked when resolver has finished its work.
	 */
	public void workIsDone();

	/**
	 * Return information if resolver's work is in progress.
	 */
	boolean isResolverWorking();

	/**
	 * Victory conditions for current element are met. Remember victorious
	 * decisions sequence.
	 * 
	 * @param prizesCollected
	 *            true, if player has collected all the prizes
	 */
	public void victoryConditionsMet(boolean prizesCollected);

	public void initGui(GuiResolver guiResolver);

}
