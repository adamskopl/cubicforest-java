package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsMasterDefault;

import com.badlogic.gdx.math.Vector2;

public abstract class GuiElementsContainerDefault extends
		RenderableObjectsMasterDefault implements GuiElementsContainer {

	/**
	 * Absolute screen position of the container. Its elements positions are
	 * translated by these coordinates.
	 */
	private final Vector2 containerScreenPos;

	protected List<GuiElement> guiElements;
	private final GuiType_e type;
	// holds currently clicked element
	private GuiElement clickedElement = null;

	public GuiElementsContainerDefault(final String name,
			final GuiType_e guiType, final String textureName, final int tileW,
			final int tileH, final float posX, final float posY) {
		super(name, "", textureName, tileW, tileH);

		guiElements = new ArrayList<GuiElement>();

		this.containerScreenPos = new Vector2(posX, posY);
		this.type = guiType;
	}

	@Override
	public GuiElement getClickedElement() {
		return clickedElement;
	}

	@Override
	public GuiType_e getType() {
		return type;
	}

	@Override
	public Vector2 getContainerScreenPos() {
		return new Vector2(containerScreenPos);
	}

	@Override
	public void addGuiElement(final GuiElement guiElement) {
		guiElements.add(guiElement);
		addRenderableObject(guiElement);
	}

	@Override
	public void clearGuiElements() {
		for (final GuiElement guiElement : guiElements) {
			removeRenderableObject(guiElement);
		}
		guiElements.clear();
	}

	/**
	 * Check if any of the gui elements is clicked.
	 * 
	 * @param screenPos
	 * @return is any of the children clicked?
	 */
	@Override
	public Boolean handleClick(final Vector2 screenPos) {
		Boolean isElementClicked = false;
		for (final GuiElement e : guiElements) {
			if (e.isClicked(screenPos)) {
				isElementClicked = true;
				clickedElement = e;
				guiElementClicked(e);
				break;
			}
		}
		return isElementClicked;
	}

	/**
	 * Override if container is reacting itself on an element click.
	 * 
	 * @param clickedElement
	 */
	protected void guiElementClicked(final GuiElement clickedElement) {
	}

}
