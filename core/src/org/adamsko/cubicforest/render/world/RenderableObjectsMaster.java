package org.adamsko.cubicforest.render.world;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsMasterDefault.ROListType_e;

import com.badlogic.gdx.math.Vector2;

/**
 * Manages {@link RenderableObject} objects.
 * 
 * @author adamsko
 * 
 */
public interface RenderableObjectsMaster {

	/**
	 * Get {@link RenderableObject} list of given {@link ROListType_e} type.
	 * 
	 * @param type
	 *            type of {@link RenderableObject} objects list.
	 * @return {@link RenderableObject} objects list of given
	 *         {@link ROListType_e} type.
	 */
	public List<RenderableObject> getRenderableObjects(ROListType_e type);

	/**
	 * Get {@link RenderableObject} list of given {@link ROListType_e} type.
	 * Clear corresponding list in container afterwards.
	 * 
	 * @param type
	 *            type of {@link RenderableObject} objects list.
	 * 
	 * @return Copy of the {@link RenderableObject} objects list of given
	 *         {@link ROListType_e} type.
	 */
	public List<RenderableObject> popRenderableObjects(ROListType_e type);

	/**
	 * Change given object's texture by using texture atlas managed by this
	 * master.
	 * 
	 * @param object
	 *            object for which texture will be changed
	 */
	public void changeTexture(final RenderableObject object,
			final int atlasRow, final int atlasCol);

	/**
	 * Change given object's texture by using texture atlas managed by this
	 * master.
	 * 
	 * @param object
	 *            object for which texture will be changed
	 * @param textureCoordinates
	 *            coordinates of the texture in the atlas
	 */
	public void changeTexture(final RenderableObject object,
			Vector2 textureCoordinates);

}