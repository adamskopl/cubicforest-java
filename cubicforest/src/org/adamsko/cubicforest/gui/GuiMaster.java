package org.adamsko.cubicforest.gui;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.pickmaster.PickMasterClient;

/**
 * Manages {@link GuiElementsContainer} objects.
 * 
 * @author adamsko
 * 
 */
public interface GuiMaster extends PickMasterClient, Nullable {

	/**
	 * Get list of all contained {@link GuiElementsContainer} objects.
	 */
	List<GuiElementsContainer> getGuiList();

	/**
	 * Receives new GUI elements container. From now this container is managed
	 * by {@link GuiMaster} (e.g. passes input).
	 */
	void addGui(final GuiElementsContainer newGui);

	/**
	 * Receives new {@link GuiMasterClient}, which will be informed about
	 * {@link GuiElementsContainer} events.
	 */
	void addClient(final GuiMasterClient newClient);

}
