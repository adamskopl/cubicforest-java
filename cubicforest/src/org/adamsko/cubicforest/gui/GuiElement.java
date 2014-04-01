package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.text.LabelsContainer;
import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectType_e;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class GuiElement extends RenderableObject {

	/**
	 * Container to which element belongs.
	 */
	GuiElementsContainer parentContainer;

	/**
	 * Absolute position of the element in its container.
	 */
	private Vector2 elementContainerPos;

	public GuiElement(TextureRegion tr, int texNum,
			GuiElementsContainer pareContainer, float posX, float posY) {
		super(tr, texNum);
		this.renderType = RenderableObjectType_e.TYPE_GUI;
		
		this.parentContainer = pareContainer;
		elementContainerPos = new Vector2(posX, posY);
	
		altLabelLast(Color.BLUE, 1.0f, 10.0f, -30.0f);
	}

	public Vector2 getScreenPos() {
		// take into account position of the container!
		return parentContainer.getContainerScreenPos().add(elementContainerPos);
	}
}