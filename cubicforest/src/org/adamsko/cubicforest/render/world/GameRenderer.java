package org.adamsko.cubicforest.render.world;

import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Manages {@link RenderableObjectsMaster} objects. Basing on all added
 * {@link RenderableObjectsMaster} objects, game screen is rendered.
 * 
 * @author adamsko
 */
public interface GameRenderer {

	/**
	 * Renders all objects from {@link RenderableObjectsMaster} objects. Setting
	 * camera and other LibGDX operations.
	 * 
	 * @param deltaTime
	 */
	void render(final float deltaTime);

	/**
	 * As seen in Libgdx demos. Invoked in hide() of {@link Screen} implementing
	 * class. Usually {@link SpriteBatch} disposal() is invoked.
	 */
	void dispose();

	/**
	 * Add {@link RenderableObjectsMaster} object managing World
	 * {@link RenderableObject} objects (renderable objects taking action in a
	 * game, usually having their own {@link Tile}). These objects will be
	 * rendered in the end.
	 * 
	 * @param ROM
	 */
	void addROMWorld(final RenderableObjectsMaster ROM);

	/**
	 * Add {@link RenderableObjectsMaster} object managing gui
	 * {@link RenderableObject} objects. These objects will be rendered in the
	 * end.
	 * 
	 * @param ROM
	 */
	void addROMGui(final RenderableObjectsMaster ROM);

}
