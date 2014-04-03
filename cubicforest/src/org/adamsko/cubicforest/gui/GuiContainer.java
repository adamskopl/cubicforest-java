package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public abstract class GuiContainer extends RenderableObjectsContainer implements
		RenderableObjectsMaster {

	protected List<GuiElement> guiElements;
	private GuiType_e type;

	/**
	 * Absolute screen position of the container. Its elements positions are
	 * translated by these coordinates.
	 */
	private Vector2 containerScreenPos;

	public Vector2 getContainerScreenPos() {
		return containerScreenPos;
	}

	public void setContainerScreenPos(Vector2 containerScreenPos) {
		this.containerScreenPos = containerScreenPos;
	}

	public GuiContainer(TilesMaster TM, GuiType_e guiType, String textureName, int tileW,
			int tileH, float posX, float posY) {
		super(TM, textureName, tileW, tileH);

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
	 * @return
	 */
	public Boolean isClicked(Vector2 screenPos) {
		Boolean elementClicked = false;
		for(GuiElement e : guiElements) {
			if(e.isClicked(screenPos))
				elementClicked = true;
		}
		return elementClicked;
	}

}
