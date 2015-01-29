package org.adamsko.cubicforest.players.resolver;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.NullOrderDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionsAggregateDefault;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsAggregate;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsAggregateDefault;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIterator;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIteratorSolving;
import org.adamsko.cubicforest.mapsResolver.roundDecisions.RoundDecisionsIteratorVisiting;

import com.badlogic.gdx.Gdx;

public class MapsResolverDefault implements MapsResolver {

	RoundDecisionsAggregate roundDecisionsAggregate;
	RoundDecisionsIterator roundDecisionsIterator;
	// iterator iterating through elements to find victorious
	RoundDecisionsIterator victoriousIterator;

	private final List<OrderDecisionsAggregate> solutions;

	private GuiResolver guiResolver;

	/**
	 * If true, resolver is searching for solutions.
	 */
	private boolean resolverIsWorking;

	public MapsResolverDefault(final boolean nullConstructor) {
		solutions = null;
		guiResolver = null;
	}

	public MapsResolverDefault() {
		roundDecisionsAggregate = new RoundDecisionsAggregateDefault(12);

		solutions = new ArrayList<OrderDecisionsAggregate>();

		victoriousIterator = new RoundDecisionsIteratorVisiting(
				NullDecisionsComponent.instance());

		resolverIsWorking = false;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void initGui(final GuiResolver guiResolver) {
		this.guiResolver = guiResolver;
	}

	@Override
	public RoundDecisionsIterator createRoundDecisionsIterator(
			final MapsResolverClient client) {
		this.roundDecisionsIterator = new RoundDecisionsIteratorSolving(client,
				roundDecisionsAggregate);
		return roundDecisionsIterator;
	}

	@Override
	public void victoryConditionsMet() {
		/*
		 * Invoked in the moment when victory conditions are met: it means that
		 * going from the first component to the last and by collecting latest
		 * decisions, will give victorious decisions sequence
		 */

		// root element is not holding any decisions, but loop will start from
		// the second one
		final DecisionsComponent first = roundDecisionsIterator.first();
		// use dedicated iterator
		victoriousIterator.set(first);

		final OrderDecisionsAggregate victoriousOrderDecisionsAggregate = new OrderDecisionsAggregateDefault();
		Gdx.app.debug("<++++++++++", "");
		do {
			victoriousIterator.next();
			Gdx.app.debug(
					"appending",
					Integer.toString(victoriousIterator.currentItem()
							.getTempId())
							+ " "
							+ victoriousIterator.currentItem()
									.getLatestDecision().getChosenTilePos()
									.toString());
			victoriousOrderDecisionsAggregate.append(victoriousIterator
					.currentItem().getLatestDecision());
		} while (!victoriousIterator.isLast());
		Gdx.app.debug("++++++++++>", "");

		solutions.add(victoriousOrderDecisionsAggregate);

		guiResolver.addNewSolution();
	}

	@Override
	public void startNewResolve() {
		solutions.clear();
		guiResolver.newResolveStarted();
		resolverIsWorking = true;
	}

	@Override
	public boolean isResolverWorking() {
		return resolverIsWorking;
	}

	@Override
	public void workIsDone() {
		resolverIsWorking = false;
	}

	@Override
	public int countAggregates() {
		return solutions.size();
	}

	@Override
	public OrderDecisionsAggregate getAggregate(final int index) {
		if (index + 1 > solutions.size()) {
			return NullOrderDecisionsAggregate.instance();
		}
		return solutions.get(index);
	};

}
