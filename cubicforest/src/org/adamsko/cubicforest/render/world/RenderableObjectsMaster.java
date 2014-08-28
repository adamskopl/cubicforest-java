package org.adamsko.cubicforest.render.world;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.render.world.renderList.RenderListDefault;

public interface RenderableObjectsMaster {

	/**
	 * @return all {@link RenderableObject} in implementing class.
	 */
	public List<RenderableObject> getRenderableObjects(ROListType_e type);

	/**
	 * @return {@link RenderableObject} objects, which are not added to
	 *         {@link RenderListDefault} yet. Clean objects afterwards.
	 */
	public List<RenderableObject> popRenderableObjects(ROListType_e type);

	public void changeTexture(final RenderableObject object,
			final int atlasRow, final int atlasCol);

}