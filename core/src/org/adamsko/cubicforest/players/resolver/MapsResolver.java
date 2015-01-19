package org.adamsko.cubicforest.players.resolver;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;

public interface MapsResolver extends Nullable {

	public RoundDecisionsIterator createRoundDecisionsIterator(
			final MapsResolverClient client);

	/**
	 * Start new level resolving process.
	 */
	public void startNewResolve();

	/**
	 * Return information if resolver's work is in progress.
	 */
	boolean isResolverWorking();

	/**
	 * Victory conditions for current element are met. Remember victorious
	 * decisions sequence.
	 */
	public void victoryConditionsMet();

	public void initGui(GuiResolver guiResolver);

}
