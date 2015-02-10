package org.adamsko.cubicforest.players.resolver;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.helpTools.ConditionalLog;
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

	private RoundDecisionsAggregate roundDecisionsAggregate;
	private RoundDecisionsIterator roundDecisionsIterator;
	// iterator iterating through elements to find victorious
	private RoundDecisionsIterator victoriousIterator;

	/**
	 * Level solutions without all prizes collected;
	 */
	private final List<OrderDecisionsAggregate> solutionsNoPrizes;
	/**
	 * Level solutions with all prizes collected;
	 */
	private final List<OrderDecisionsAggregate> solutionsPrizes;

	private GuiResolver guiResolver;

	/**
	 * If true, resolver is searching for solutions.
	 */
	private boolean resolverIsWorking;

	MapsResolverDefault(final boolean nullConstructor) {
		solutionsNoPrizes = null;
		solutionsPrizes = null;
		guiResolver = null;
	}

	public MapsResolverDefault() {
		roundDecisionsAggregate = new RoundDecisionsAggregateDefault(12);

		solutionsNoPrizes = new ArrayList<OrderDecisionsAggregate>();

		solutionsPrizes = new ArrayList<OrderDecisionsAggregate>();

		victoriousIterator = new RoundDecisionsIteratorVisiting(
				NullDecisionsComponent.instance());

		resolverIsWorking = false;

		ConditionalLog.addObject(this, "MapsResolverDefault");
		ConditionalLog.setUsage(this, true);
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
	public void victoryConditionsMet(final boolean prizesCollected) {
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
		do {
			victoriousIterator.next();
			victoriousOrderDecisionsAggregate.append(victoriousIterator
					.currentItem().getLatestDecision());
		} while (!victoriousIterator.isLast());

		ConditionalLog.debug(this,
				"victoryConditionsMet " + Boolean.toString(prizesCollected));
		if (ConditionalLog.checkUsage(this)) {
			victoriousOrderDecisionsAggregate.debugPrint();
		}

		solutionsNoPrizes.add(victoriousOrderDecisionsAggregate);
		if (prizesCollected) {
			solutionsPrizes.add(victoriousOrderDecisionsAggregate);
			guiResolver.addNewSolution();
		}
	}

	@Override
	public void startNewResolve() {
		solutionsNoPrizes.clear();
		solutionsPrizes.clear();
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
		return solutionsNoPrizes.size();
	}

	@Override
	public OrderDecisionsAggregate getAggregate(final int index,
			final boolean prizesCollected) {

		final List<OrderDecisionsAggregate> targetSolutions;
		if (prizesCollected) {
			targetSolutions = solutionsPrizes;
		} else {
			targetSolutions = solutionsPrizes;
		}

		if (index + 1 > targetSolutions.size()) {
			Gdx.app.error("MapsResolverDefault::getAggregate()",
					"targetSolutions.size() error");
			return NullOrderDecisionsAggregate.instance();
		}
		return targetSolutions.get(index);
	}

};
