package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.debug.GuiDebug;
import org.adamsko.cubicforest.gui.heroTools.GuiHeroTools;
import org.adamsko.cubicforest.gui.levels.GuiLevels;
import org.adamsko.cubicforest.gui.orders.GuiOrders;
import org.adamsko.cubicforest.gui.resolver.GuiResolver;
import org.adamsko.cubicforest.gui.resolver.GuiResolverDefault;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GuiMasterDefault implements GuiMaster {

	private List<GuiElementsContainer> guiList;

	@Override
	public List<GuiElementsContainer> getGuiList() {
		return guiList;
	}

	private List<GuiMasterClient> clients;

	private GuiHeroTools guiHeroTools;
	private GuiOrders guiOrders;
	private GuiDebug guiDebug;
	private GuiLevels guiLevels;
	private GuiResolver guiResolver;

	/**
	 * For {@link NullGuiMaster}
	 */
	GuiMasterDefault(final boolean nullConstructor) {
	}

	public GuiMasterDefault() {
		guiList = new ArrayList<GuiElementsContainer>();
		clients = new ArrayList<GuiMasterClient>();
	}

	@Override
	public void initializeContainers(final MapsLoader mapsLoader,
			final TexturesManager texturesManager) {
		this.guiHeroTools = new GuiHeroTools("tools-atlas-medium", 40, 42, 25,
				-50);
		guiHeroTools.setTexturesManager(texturesManager);

		this.guiOrders = new GuiOrders("orders-atlas-medium", 100, 100, 20,
				-340);

		this.guiDebug = new GuiDebug("orders-atlas-medium", 50, 50, 680, -120);

		this.guiLevels = new GuiLevels(mapsLoader, "levels-atlas-medium", 30,
				30, 600, -100);

		this.guiResolver = new GuiResolverDefault("orders-atlas-medium", 30,
				30, 150, -440);

		addGui(guiHeroTools);
		addGui(guiOrders);
		addGui(guiDebug);
		addGui(guiLevels);
		addGui(guiResolver);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void addGui(final GuiElementsContainer newGui) {
		guiList.add(newGui);
	}

	@Override
	public void addClient(final GuiMasterClient newClient) {
		if (newClient.isNull()) {
			Gdx.app.error("GuiMasterClient::addClient", "newClient.isNull()");
		}
		clients.add(newClient);
	}

	@Override
	public void onInput(final Vector2 inputScreenPos,
			final Vector2 inputTilesPos) {

		/*
		 *
		 * 1. search for clicked GUI 2. pass clicked gui to clients
		 */
		for (final GuiElementsContainer guiContainer : guiList) {
			if (guiContainer.handleClick(inputScreenPos)) {
				clientsOnClicked(guiContainer);
			}
		}

	}

	@Override
	public void reload(final CFMap cfMap) {
		for (final GuiElementsContainer guiElementsContainer : guiList) {
			guiElementsContainer.reload(cfMap);
		}
	}

	private void clientsOnClicked(final GuiElementsContainer clickedContainer) {
		for (final GuiMasterClient guiMasterClient : clients) {
			guiMasterClient.onGuiEvent(clickedContainer);
		}
	}

	@Override
	public GuiResolver getGuiResolver() {
		return guiResolver;
	}

}
