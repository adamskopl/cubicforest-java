package org.adamsko.cubicforest.players.resolver;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;

/**
 * Composite pattern
 * 
 * @author adamsko
 * 
 */
public interface DecisionsComponent extends Nullable {

	void makeNextDecision();

	void add(DecisionsComponent decisionsComponent);

	DecisionsComponent getChild();

	DecisionsComponent getParent();

	void remove(DecisionsComponent decisionsComponent);

	// /**
	// * Check if {@link DecisionsComponent} is completely resolved and can be
	// * abandoned
	// */
	// boolean isDone();

	/**
	 * Get height of the element in the resolving tree
	 */
	int getHeight();

	/**
	 * Resolve which {@link DecisionsComponent} in resolve structure should be
	 * resolved next.
	 * 
	 * @param client
	 *            client is needed to set possible decisions for eventual new
	 *            {@link DecisionsComponent}
	 */
	DecisionsComponent nextComponent();

	void setSnapshotAfterDecision(GameMemento snapshotAfterPreviousDecision);

	/**
	 * Get possible {@link OrderDecisionDefault} decisions for this component
	 * (they should be initialized in a constructor)
	 */
	List<OrderDecisionDefault> getPossibleDecisions();

	/**
	 * Return latest order decision. Needed to collect victorious order
	 * decisions, when victory condition is met.
	 */
	OrderDecisionDefault getLatestDecision();

}
