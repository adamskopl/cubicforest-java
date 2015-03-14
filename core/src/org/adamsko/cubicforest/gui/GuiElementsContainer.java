package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.world.object.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for class managing group of {@link GuiElement} objects. Responsible
 * for adding new elements, indicating their screen positions, handling GUI
 * inputs.
 * 
 * @author adamsko
 * 
 */
public interface GuiElementsContainer extends RenderableObjectsMaster {

	/**
	 * Receive input coordinates and perform actions, if one of the
	 * {@link GuiElement} objects is clicked.
	 * 
	 * @param screenPos
	 *            input screen position
	 * @return true of one of the gui elements is clicked
	 */
	Boolean handleClick(final Vector2 screenPos);

	/**
	 * Add new {@link GuiElement}.
	 */
	void addGuiElement(final GuiElement guiElement);

	/**
	 * Remove all added {@link GuiElement} objects
	 */
	void clearGuiElements();

	/**
	 * Get screen position of the container. This position indicates screen
	 * positions of contained {@link GuiElement} objects, which have relative
	 * positions to their container.
	 */
	Vector2 getContainerScreenPos();

	/**
	 * The assumption is, that only one {@link GuiElement} object could be
	 * clicked. Return element, that was previously clicked.
	 */
	GuiElement getClickedElement();

	/**
	 * FIXME: replace with inheritance
	 */
	GuiType_e getType();

	/**
	 * Reload method. Some gui containers need to reload their
	 * {@link GuiElement} elements, which depend on {@link CFMap}
	 */
	void reload(CFMap cfMap);

}
