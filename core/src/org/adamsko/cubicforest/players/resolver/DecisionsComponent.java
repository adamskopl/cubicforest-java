package org.adamsko.cubicforest.players.resolver;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.mapsResolver.gameSnapshot.GameMemento;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisions;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;

/**
 * Composite pattern
 * 
 * @author adamsko
 * 
 */
/**
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
	 * resolved next. Used by i.a. {@link RoundDecisionsIterator} (component is
	 * deciding which component is next for iterator).
	 */
	DecisionsComponent nextComponent();

	/**
	 * Check if this component has done its work. E.g. if it's a
	 * {@link RoundDecisions} component, it will be done if it has resolved all
	 * its possible decisions and will not create next components.
	 * 
	 * @return true if component has done its work and will not create new
	 *         components.
	 */
	boolean isDone();

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

	int getTempId();

}
