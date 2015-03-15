package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.world.object.RenderableObjectDefault;
import org.adamsko.cubicforest.render.world.object.RenderableObjectType;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
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

	public GuiElementDefault(final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainer pareContainer, final float posX,
			final float posY) {
		super(objectsTextureChanger, tr, texNum);
		this.renderType = RenderableObjectType.TYPE_GUI;

		this.parentContainer = pareContainer;
		elementContainerPos = new Vector2(posX, posY);
	}

	@Override
	public boolean mapValid(final TiledMapProperties tiledMapProperties) {
		return true;
	}

	@Override
	public Vector2 getScreenPos() {
		// take into account position of the container!
		return new Vector2(parentContainer.getContainerScreenPos().add(
				elementContainerPos));
	}

	@Override
	public void setContainerPos(final float x, final float y) {
		elementContainerPos.x = x;
		elementContainerPos.y = y;

	}

	@Override
	public boolean isClicked(final Vector2 clickedScreenPos) {
		final Vector2 screenPos = new Vector2(getScreenPos());
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