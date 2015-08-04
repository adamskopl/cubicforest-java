package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.world.object.RenderableObjectDefault;
import org.adamsko.cubicforest.render.world.object.RenderableObjectType;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledMapProperties;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class GuiElementDefault extends RenderableObjectDefault implements
GuiElement {

	/**
	 * Container to which element belongs.
	 */
	GuiElementsContainer parentContainer;

	/**
	 * Absolute position of the element in its container.
	 */
	private final Vector2 elementContainerPos;
	/**
	 * Screen position of the element (parent container + elementContainerPos)
	 */
	private final Vector2 elementScreenPos;

	public GuiElementDefault(final TextureRegion tr, final int texNum,
			final GuiElementsContainer pareContainer, final float posX,
			final float posY) {
		super(tr, texNum);
		this.renderType = RenderableObjectType.TYPE_GUI;

		this.parentContainer = pareContainer;
		elementContainerPos = new Vector2(posX, posY);
		elementScreenPos = new Vector2();
	}

	@Override
	public boolean mapValid(final TiledMapProperties tiledMapProperties) {
		return true;
	}

	@Override
	public Vector2 getScreenPos() {
		// take into account position of the container!
		final Vector2 parentContainerPos = parentContainer
				.getContainerScreenPos();
		elementScreenPos.set(parentContainerPos.x + elementContainerPos.x,
				parentContainerPos.y + elementContainerPos.y);
		return elementScreenPos;
	}

	@Override
	public void setContainerPos(final float x, final float y) {
		elementContainerPos.x = x;
		elementContainerPos.y = y;

	}

	@Override
	public boolean isClicked(final Vector2 clickedScreenPos) {
		final Vector2 screenPos = getScreenPos();
		screenPos.add(getRenderVector());

		// FIXME get "real" y coordinates...
		screenPos.y = -screenPos.y;

		if (clickedScreenPos.x > screenPos.x
				&& clickedScreenPos.x < screenPos.x + getTexWidth()
				&& clickedScreenPos.y < screenPos.y
				&& clickedScreenPos.y > screenPos.y - getTexHeight()) {

			return true;
		}

		return false;
	}
}