package org.adamsko.cubicforest.render.world.object;

import java.util.List;

import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.RenderableObjectsMasterDefault.ROListType_e;

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

	void setTexturesManager(TexturesManager texturesManager);

	TexturesManager getTexturesManager();

}