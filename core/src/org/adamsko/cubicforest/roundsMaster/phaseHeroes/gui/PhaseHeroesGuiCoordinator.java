package org.adamsko.cubicforest.roundsMaster.phaseHeroes.gui;

import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.gui.GuiMasterClient;
import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.debug.GuiElementDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiElementHeroTool;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.levels.GuiElementLevel;
import org.adamsko.cubicforest.gui.levels.GuiLevels;
import org.adamsko.cubicforest.gui.orders.GuiElementOrder;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.gui.resolver.GuiElementResolver;
import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.mapsResolver.MapsResolver;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroesMode;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroesOrdersMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;

/**
 * Class managing {@link PhaseHeroes} clicks.
 * 
 * @author adamsko
 * 
 */
public class PhaseHeroesGuiCoordinator implements GuiMasterClient {

	private final PhaseHeroes phaseHeroes;
	private final RoundsMaster roundsMaster;
	private final MapsResolver mapsResolver;
	private final GatherCubesMaster gatherCubesMaster;
	PhaseHeroesOrdersMaster phaseHeroesOrdersMaster;

	public PhaseHeroesGuiCoordinator(final PhaseHeroes phaseHeroes,
			final RoundsMaster roundsMaster, final MapsResolver mapsResolver,
			final GatherCubesMaster gatherCubesMaster,
			final PhaseHeroesOrdersMaster phaseHeroesOrdersMaster) {
		this.phaseHeroes = phaseHeroes;
		this.roundsMaster = roundsMaster;
		this.mapsResolver = mapsResolver;
		this.gatherCubesMaster = gatherCubesMaster;
		this.phaseHeroesOrdersMaster = phaseHeroesOrdersMaster;
	}

	@Override
	public void onGuiEvent(final GuiElementsContainer eventGui) {
		switch (eventGui.getType()) {
		case GUI_ORDERS:
			guiOrdersClicked((GuiOrders) eventGui);
			break;
		case GUI_HERO_TOOLS:
			guiHeroToolsClicked((GuiHeroTools) eventGui);
			break;
		case GUI_DEBUG:
			guiDebugClicked((GuiDebug) eventGui);
			break;

		case GUI_LEVELS:
			guiLevelsClicked((GuiLevels) eventGui);
			break;

		case GUI_RESOLVER:
			guiResolverClicked((GuiResolver) eventGui);
			break;

		default:
			break;
		}
	}

	private void guiLevelsClicked(final GuiLevels guiLevels) {
		final GuiElementLevel clickedElementLevel = (GuiElementLevel) guiLevels
				.getClickedElement();

		roundsMaster.setMapActive(clickedElementLevel.getLevelIndex());
		roundsMaster.reload();
	}

	private void guiResolverClicked(final GuiResolver eventGui) {
		final GuiElementResolver clickedElementResolver = (GuiElementResolver) eventGui
				.getClickedElement();

		switch (clickedElementResolver.getGuiResolverType()) {
		case RESOLVER_START:
			mapsResolver.startNewResolve();
			phaseHeroes.solverIter();
			break;

		case RESOLVER_SOLUTION:
			break;

		default:
			break;
		}

	}

	private void guiDebugClicked(final GuiDebug guiDebug) {
		final GuiElementDebug clickedElementDebug = (GuiElementDebug) guiDebug
				.getClickedElement();

		switch (clickedElementDebug.getDebugType()) {
		case DEBUG_RELOAD:
			roundsMaster.reload();
			break;

		default:
			break;
		}
	}

	private void guiHeroToolsClicked(final GuiHeroTools guiHeroTools) {
		try {
			final GuiElementHeroTool clickedElement = (GuiElementHeroTool) guiHeroTools
					.getClickedElement();
			final WorldObjectType heroToolType = clickedElement.getType();

			if (gatherCubesMaster.getGatherCubesCounter().isToolAffordable(
					heroToolType)) {
				// change mode and also set marker's type
				phaseHeroesOrdersMaster.changePhaseHeroesMode(
						PhaseHeroesMode.MODE_CHOICE_TOOL, heroToolType);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void guiOrdersClicked(final GuiOrders guiOrders) {
		final GuiElementOrder clickedElement = (GuiElementOrder) guiOrders
				.getClickedElement();

		switch (clickedElement.getOrderType()) {
		case ORDER_ACCEPT:
			phaseHeroes.startOrderClicked();
			break;
		case ORDER_CANCEL:
			try {
				// cancel tool adding
				phaseHeroesOrdersMaster
						.changePhaseHeroesMode(PhaseHeroesMode.MODE_CHOICE_MOVEMENT);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
