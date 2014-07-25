package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class GuiContainer extends RenderableObjectsContainer implements
		RenderableObjectsMaster {

	protected List<GuiElement> guiElements;
	private GuiType_e type;
	// holds actually clicked element 
	private GuiElement clickedElement = null;
	
	public GuiElement getClickedElement() {
		return clickedElement;
	}

	public GuiType_e getType() {
		return type;
	}

	/**
	 * Absolute screen position of the container. Its elements positions are
	 * translated by these coordinates.
	 */
	private Vector2 containerScreenPos;

	public Vector2 getContainerScreenPos() {
		return new Vector2(containerScreenPos);
	}

	public void setContainerScreenPos(Vector2 containerScreenPos) {
		this.containerScreenPos = containerScreenPos;
	}

	public GuiContainer(String name, TilesMaster TM, GuiType_e guiType, String textureName, int tileW,
			int tileH, float posX, float posY) {
		super(name, TM, textureName, tileW, tileH);

		guiElements = new ArrayList<GuiElement>();

		this.containerScreenPos = new Vector2(posX, posY);
		this.type = guiType;
	}

	public void addGuiElement(GuiElement guiElement) {
		guiElements.add(guiElement);
		addRenderableObject(guiElement);
	}
	
	/**
	 * Check if any of the gui elements is clicked.
	 * 
	 * @param screenPos
	 * @return is any of the children clicked?
	 */
	public Boolean handleClick(Vector2 screenPos) {
		Boolean isElementClicked = false;
		for(GuiElement e : guiElements) {
			if(e.isClicked(screenPos)) {
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
	protected void guiElementClicked(GuiElement clickedElement) {
	}
	
}
