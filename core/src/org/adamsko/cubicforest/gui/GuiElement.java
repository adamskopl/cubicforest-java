package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledMapProperties;

import com.badlogic.gdx.math.Vector2;

public interface GuiElement extends RenderableObject {

	public Vector2 getScreenPos();

	/**
	 * Set position relative to the position of parent container
	 */
	public void setContainerPos(float x, float y);

	/**
	 * @param clickedScreenPos
	 *            coordinates of the screen click
	 */
	public boolean isClicked(final Vector2 clickedScreenPos);

	/**
	 * Check if given element should be displayed for given
	 * {@link TiledMapProperties}
	 */
	public boolean mapValid(final TiledMapProperties tiledMapProperties);
}
