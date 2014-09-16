package org.adamsko.cubicforest.mapsResolver;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GametMemento;

/**
 * Composite pattern
 * 
 * @author adamsko
 * 
 */
public interface DecisionsComponent extends Nullable {

	// take next possible decision and perform it
	void makeDecision();

	void add(DecisionsComponent decisionsComponent);

	DecisionsComponent getChild();

	DecisionsComponent getParent();

	void remove(DecisionsComponent decisionsComponent);

	/**
	 * Check if {@link DecisionsComponent} is completely resolved and can be
	 * abandoned
	 */
	boolean isDone();

	/**
	 * Resolve which {@link DecisionsComponent} in resolve structure should be
	 * resolved next.
	 */
	DecisionsComponent nextComponent();

	GametMemento getSnapshotAfterChoice();

}
