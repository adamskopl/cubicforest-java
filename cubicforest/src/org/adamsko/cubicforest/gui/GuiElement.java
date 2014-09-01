package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.world.RenderableObjectDefault;
import org.adamsko.cubicforest.render.world.RenderableObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class GuiElement extends RenderableObjectDefault {

	/**
	 * Container to which element belongs.
	 */
	GuiContainer parentContainer;

	/**
	 * Absolute position of the element in its container.
	 */
	private final Vector2 elementContainerPos;

	public GuiElement(final TextureRegion tr, final int texNum,
			final GuiContainer pareContainer, final float posX, final float posY) {
		super(tr, texNum);
		this.renderType = RenderableObjectType.TYPE_GUI;

		this.parentContainer = pareContainer;
		elementContainerPos = new Vector2(posX, posY);
	}

	public Vector2 getScreenPos() {
		// take into account position of the container!
		return new Vector2(parentContainer.getContainerScreenPos().add(
				elementContainerPos));
	}

	/**
	 * @param clickedScreenPos
	 *            coordinates of the screen click
	 * @return
	 */
	public Boolean isClicked(final Vector2 clickedScreenPos) {
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